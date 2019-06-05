/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.ranking.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.ranking.web.internal.constants.SearchTuningPortletKeys;
import com.liferay.portal.search.ranking.web.internal.display.context.RankingEntryDisplayContextBuilder;
import com.liferay.portal.search.ranking.web.internal.index.Ranking;
import com.liferay.portal.search.ranking.web.internal.index.RankingCriteriaBuilderFactory;
import com.liferay.portal.search.ranking.web.internal.index.RankingIndexReader;
import com.liferay.portal.search.ranking.web.internal.index.RankingIndexWriter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kevin Tan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SearchTuningPortletKeys.SEARCH_TUNING,
		"mvc.command.name=/results_ranking/edit"
	},
	service = MVCActionCommand.class
)
public class EditRankingMVCActionCommand extends BaseMVCActionCommand {

	@Deprecated
	public static final String PARAM_ALIASES = "aliases";

	@Deprecated
	public static final String PARAM_KEYWORDS = "keywords";

	protected static List<String> update(
		List<String> strings, String[] addStrings, String[] removeStrings) {

		List<String> newStrings;

		if (ListUtil.isEmpty(strings)) {
			newStrings = Arrays.asList(addStrings);
		}
		else {
			newStrings = new ArrayList<>(strings);

			Collections.addAll(newStrings, addStrings);
		}

		newStrings.removeAll(Arrays.asList(removeStrings));

		return newStrings;
	}

	protected void add(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Action action)
		throws Exception {

		if (false) {
			if (rankingExistsForKeyword(actionRequest, action)) {
				SessionErrors.add(actionRequest, Exception.class);

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");

				return;
			}
		}

		Ranking ranking = addRanking(
			actionRequest, getIndexName(actionRequest, action._indexParam));

		String redirect = getSaveAndContinueRedirect(
			actionRequest, ranking, action._redirect);

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	protected Ranking addRanking(ActionRequest actionRequest, String index) {
		Ranking.RankingBuilder rankingBuilder = new Ranking.RankingBuilder();

		String resultActionCmd = ParamUtil.getString(
			actionRequest, "resultActionCmd");
		String resultActionUid = ParamUtil.getString(
			actionRequest, "resultActionUid");

		if (!resultActionCmd.isEmpty() && !resultActionUid.isEmpty()) {
			if (resultActionCmd.equals(SearchRankingConstants.PIN)) {
				rankingBuilder.pins(
					Arrays.asList(new Ranking.Pin(0, resultActionUid)));
			}
			else {
				rankingBuilder.blocks(ListUtil.fromString(resultActionUid));
			}
		}

		String name = _getName(actionRequest);

		rankingBuilder.index(
			index
		).name(
			name
		).queryStrings(
			Arrays.asList(name)
		).status(
			WorkflowConstants.STATUS_DRAFT
		);

		String id = rankingIndexWriter.create(rankingBuilder.build());

		Optional<Ranking> optional = rankingIndexReader.fetchOptional(id);

		return optional.get();
	}

	protected void delete(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Action action)
		throws IOException {

		deleteRanking(action._resultsRankingUid);

		sendRedirect(actionRequest, actionResponse, action._redirect);
	}

	protected void deleteRanking(String resultsRankingUid) {
		rankingIndexWriter.remove(resultsRankingUid);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Action action = new Action(actionRequest);

		if (action.isCmd(Constants.ADD)) {
			add(actionRequest, actionResponse, action);
		}
		else if (action.isCmd(Constants.UPDATE)) {
			update(actionRequest, actionResponse, action);
		}
		else if (action.isCmd(Constants.DELETE)) {
			delete(actionRequest, actionResponse, action);
		}
	}

	protected String getIndexName(
		ActionRequest actionRequest, String indexParam) {

		String index;

		if (Validator.isBlank(indexParam)) {
			long companyId = portal.getCompanyId(actionRequest);

			index = "liferay-" + companyId;
		}
		else {
			index = indexParam;
		}

		return index;
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, Ranking ranking, String redirect)
		throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, portletConfig.getPortletName(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editResultsRankingEntry");
		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("resultsRankingUid", ranking.getId(), false);
		portletURL.setParameter(
			PARAM_ALIASES,
			StringUtil.merge(ranking.getQueryStrings(), StringPool.COMMA),
			false);
		portletURL.setParameter(PARAM_KEYWORDS, ranking.getName(), false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}

	@Deprecated
	protected boolean rankingExistsForAliases(ActionRequest actionRequest) {
		String index = ParamUtil.getString(actionRequest, "index-name");

		if (Validator.isBlank(index)) {
			long companyId = portal.getCompanyId(actionRequest);

			index = "liferay-" + companyId;
		}

		List<String> aliases = _getQueryStrings(actionRequest);

		if (aliases.isEmpty()) {
			return false;
		}

		String resultsRankingUid = ParamUtil.getString(
			actionRequest, "resultsRankingUid");

		return rankingIndexReader.exists(
			rankingCriteriaBuilderFactory.builder(
			).aliases(
				ArrayUtil.toStringArray(aliases)
			).index(
				index
			).id(
				resultsRankingUid
			).build());
	}

	@Deprecated
	protected boolean rankingExistsForKeyword(
		ActionRequest actionRequest, Action action) {

		String index = action._indexParam;

		if (Validator.isBlank(index)) {
			long companyId = portal.getCompanyId(actionRequest);

			index = "liferay-" + companyId;
		}

		return rankingIndexReader.exists(
			rankingCriteriaBuilderFactory.builder(
			).index(
				index
			).queryString(
				action._queryString
			).build());
	}

	protected void update(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Action action)
		throws IOException {

		if (false) {
			if (rankingExistsForAliases(actionRequest)) {
				SessionErrors.add(actionRequest, Exception.class);

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");

				return;
			}
		}

		updateRanking(actionRequest);

		sendRedirect(actionRequest, actionResponse, action._redirect);

		return;
	}

	protected void updateRanking(ActionRequest actionRequest) {
		String id = ParamUtil.getString(actionRequest, "resultsRankingUid");

		int pinnedIdsEndIndex = ParamUtil.getInteger(
			actionRequest, "pinnedIdsEndIndex");
		int pinnedIdsStartIndex = ParamUtil.getInteger(
			actionRequest, "pinnedIdsStartIndex");

		Optional<Ranking> optional = rankingIndexReader.fetchOptional(id);

		optional.ifPresent(ranking -> updateRanking(actionRequest, ranking));
	}

	protected void updateRanking(ActionRequest actionRequest, Ranking ranking) {
		Ranking.RankingBuilder rankingBuilder = new Ranking.RankingBuilder(
			ranking);

		String[] hiddenAdded = ParamUtil.getStringValues(
			actionRequest, "hiddenIdsAdded");
		String[] hiddenRemoved = ParamUtil.getStringValues(
			actionRequest, "hiddenIdsRemoved");

		rankingBuilder.blocks(
			update(ranking.getBlockIds(), hiddenAdded, hiddenRemoved)
		).inactive(
			_isInactive(actionRequest)
		).name(
			_getNameForUpdate(actionRequest, ranking.getName())
		).queryStrings(
			_getQueryStrings(actionRequest)
		);

		List<Ranking.Pin> originalPinnedDocuments = ranking.getPins();

		List<Ranking.Pin> newPinnedDocuments = new ArrayList<>();

		String[] pinnedIds = ParamUtil.getStringValues(
			actionRequest, "pinnedIds");

		for (int i = 0; i < pinnedIds.length; i++) {
			newPinnedDocuments.add(new Ranking.Pin(i, pinnedIds[i]));
		}

		if (ListUtil.isNotEmpty(newPinnedDocuments)) {
			rankingBuilder.pins(newPinnedDocuments);
		}
		else {
			rankingBuilder.pins(null);
		}

		_setStatus(actionRequest, rankingBuilder);

		rankingIndexWriter.update(rankingBuilder.build());
	}

	@Reference
	protected Portal portal;

	@Reference
	protected RankingCriteriaBuilderFactory rankingCriteriaBuilderFactory;

	@Reference
	protected RankingIndexReader rankingIndexReader;

	@Reference
	protected RankingIndexWriter rankingIndexWriter;

	protected static class Action {

		public boolean isCmd(String cmd) {
			return Objects.equals(cmd, _cmd);
		}

		private final String _cmd;
		private final String _indexParam;
		private final String _queryString;
		private final String _redirect;

		Action(ActionRequest actionRequest) {
			_cmd = ParamUtil.getString(actionRequest, Constants.CMD);
			_redirect = ParamUtil.getString(actionRequest, "redirect");
			_indexParam = ParamUtil.getString(actionRequest, "index-name");
			_queryString = ParamUtil.getString(
				actionRequest, EditRankingMVCActionCommand.PARAM_KEYWORDS);
			_resultsRankingUid = ParamUtil.getString(
				actionRequest, "resultsRankingUid");
		}

		private final String _resultsRankingUid;

	}

	private String _asNameUpdate(String string) {
		return string.replace(__UPDATE_NAME__INDICATOR, StringPool.BLANK);
	}

	@Deprecated
	private List<String> _getAliasesParamValues(ActionRequest actionRequest) {
		return new ArrayList<>(
			Arrays.asList(
				ParamUtil.getStringValues(
					actionRequest, EditRankingMVCActionCommand.PARAM_ALIASES)));
	}

	private String _getName(ActionRequest actionRequest) {
		return ParamUtil.getString(
			actionRequest, EditRankingMVCActionCommand.PARAM_KEYWORDS);
	}

	private String _getNameForUpdate(
		ActionRequest actionRequest, String oldName) {

		List<String> strings = _getAliasesParamValues(actionRequest);

		return strings.stream(
		).filter(
			this::_isNameUpdate
		).map(
			this::_asNameUpdate
		).findAny(
		).orElse(
			oldName
		);
	}

	private List<String> _getQueryStrings(ActionRequest actionRequest) {
		List<String> strings = _getAliasesParamValues(actionRequest);

		strings.remove(RankingEntryDisplayContextBuilder.__INACTIVE__INDICATOR);

		Predicate<String> predicate = this::_isNameUpdate;

		return strings.stream(
		).filter(
			predicate.negate()
		).collect(
			Collectors.toList()
		);
	}

	private boolean _isInactive(ActionRequest actionRequest) {
		List<String> strings = _getAliasesParamValues(actionRequest);

		return strings.contains(
			RankingEntryDisplayContextBuilder.__INACTIVE__INDICATOR);
	}

	private boolean _isNameUpdate(String string) {
		return string.startsWith(__UPDATE_NAME__INDICATOR);
	}

	@Deprecated
	private void _setStatus(
		ActionRequest actionRequest, Ranking.RankingBuilder rankingBuilder) {

		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {

			// @TODO Save draft action

			rankingBuilder.status(WorkflowConstants.STATUS_DRAFT);
		}
		else {

			// @TODO Publish action

			rankingBuilder.status(WorkflowConstants.STATUS_APPROVED);
		}
	}

	private static final String __UPDATE_NAME__INDICATOR = StringPool.UNDERLINE;

}