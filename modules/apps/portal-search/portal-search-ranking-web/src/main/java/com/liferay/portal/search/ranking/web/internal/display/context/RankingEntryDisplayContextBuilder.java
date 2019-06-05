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

package com.liferay.portal.search.ranking.web.internal.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.ranking.web.internal.index.Ranking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bryan Engler
 */
public class RankingEntryDisplayContextBuilder {

	public static final String __INACTIVE__INDICATOR = "X";

	public RankingEntryDisplayContextBuilder(Ranking ranking) {
		_ranking = ranking;
	}

	public RankingEntryDisplayContext build() {
		RankingEntryDisplayContext rankingEntryDisplayContext =
			new RankingEntryDisplayContext();

		_setDisplayDate(rankingEntryDisplayContext);
		_setHiddenResultsCount(rankingEntryDisplayContext);
		_setIndex(rankingEntryDisplayContext);
		_setModifiedDate(rankingEntryDisplayContext);
		_setName(rankingEntryDisplayContext);
		_setPinnedResultsCount(rankingEntryDisplayContext);
		_setQueryStrings(rankingEntryDisplayContext);
		_setStatus(rankingEntryDisplayContext);
		_setUid(rankingEntryDisplayContext);

		return rankingEntryDisplayContext;
	}

	protected static String getSizeString(List<?> list) {
		return String.valueOf(list.size());
	}

	private List<String> _getQueryStrings() {
		List<String> queryStrings = new ArrayList<>(_ranking.getQueryStrings());

		if (_ranking.isInactive()) {
			queryStrings.add(__INACTIVE__INDICATOR);
		}

		return queryStrings;
	}

	private int _getStatus() {
		if (_ranking.isInactive()) {
			return WorkflowConstants.STATUS_INACTIVE;
		}

		return WorkflowConstants.STATUS_APPROVED;
	}

	private void _setDisplayDate(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setDisplayDate(_ranking.getDisplayDate());
	}

	private void _setHiddenResultsCount(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setHiddenResultsCount(
			getSizeString(_ranking.getBlockIds()));
	}

	private void _setIndex(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setIndex(_ranking.getIndex());
	}

	private void _setModifiedDate(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setModifiedDate(_ranking.getModifiedDate());
	}

	private void _setName(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setKeywords(_ranking.getName());
	}

	private void _setPinnedResultsCount(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setPinnedResultsCount(
			getSizeString(_ranking.getPins()));
	}

	private void _setQueryStrings(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setAliases(
			StringUtil.merge(_getQueryStrings(), StringPool.COMMA_AND_SPACE));
	}

	private void _setStatus(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setStatus(String.valueOf(_getStatus()));
	}

	private void _setUid(
		RankingEntryDisplayContext rankingEntryDisplayContext) {

		rankingEntryDisplayContext.setUid(_ranking.getId());
	}

	private final Ranking _ranking;

}