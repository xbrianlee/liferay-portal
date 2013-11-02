/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portlet.wiki.lar;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.permission.WikiPermission;
import com.liferay.portlet.wiki.service.persistence.WikiNodeUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 * @author Zsolt Berentey
 */
public class WikiDisplayPortletDataHandler extends WikiPortletDataHandler {

	public WikiDisplayPortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
		setDataPortletPreferences("title", "nodeId");
		setExportControls(new PortletDataHandlerControl[0]);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletPreferences == null) {
			return portletPreferences;
		}

		portletPreferences.setValue("title", StringPool.BLANK);
		portletPreferences.setValue("nodeId", StringPool.BLANK);

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long nodeId = GetterUtil.getLong(
			portletPreferences.getValue("nodeId", StringPool.BLANK));

		if (nodeId <= 0) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No node id found in preferences of portlet " + portletId);
			}

			return portletPreferences;
		}

		String title = portletPreferences.getValue("title", null);

		if (title == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No title found in preferences of portlet " + portletId);
			}

			return portletPreferences;
		}

		WikiNode node = WikiNodeUtil.fetchByPrimaryKey(nodeId);

		if (node == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to find wiki node");
			}

			return portletPreferences;
		}

		portletDataContext.addPortletPermissions(WikiPermission.RESOURCE_NAME);

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, portletId, node);

		List<WikiPage> pages = WikiPageLocalServiceUtil.getPages(
			node.getNodeId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WikiPage page : pages) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, portletId, page);
		}

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.importPortletPermissions(
			WikiPermission.RESOURCE_NAME);

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, WikiNode.class);

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, WikiPage.class);

		long nodeId = GetterUtil.getLong(
			portletPreferences.getValue("nodeId", StringPool.BLANK));

		if (nodeId > 0) {
			Map<Long, Long> nodeIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					WikiNode.class);

			nodeId = MapUtil.getLong(nodeIds, nodeId, nodeId);

			portletPreferences.setValue("nodeId", String.valueOf(nodeId));
		}

		return portletPreferences;
	}

	private static Log _log = LogFactoryUtil.getLog(
		WikiDisplayPortletDataHandler.class);

}