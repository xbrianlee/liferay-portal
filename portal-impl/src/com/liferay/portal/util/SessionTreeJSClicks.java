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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class SessionTreeJSClicks {

	public static void closeLayoutNodes(
		HttpServletRequest request, String treeId, boolean privateLayout,
		long layoutId, boolean recursive) {

		try {
			List<String> layoutIds = new ArrayList<String>();

			layoutIds.add(String.valueOf(layoutId));

			if (recursive) {
				getLayoutIds(request, privateLayout, layoutId, layoutIds);
			}

			closeNodes(
				request, treeId,
				layoutIds.toArray(new String[layoutIds.size()]));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void closeNode(
		HttpServletRequest request, String treeId, String nodeId) {

		try {
			String openNodesString = get(request, treeId);

			openNodesString = StringUtil.remove(openNodesString, nodeId);

			put(request, treeId, openNodesString);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void closeNodes(HttpServletRequest request, String treeId) {
		try {
			String openNodesString = StringPool.BLANK;

			put(request, treeId, openNodesString);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void closeNodes(
		HttpServletRequest request, String treeId, String[] nodeIds) {

		try {
			String openNodesString = get(request, treeId);

			for (String nodeId : nodeIds) {
				openNodesString = StringUtil.remove(openNodesString, nodeId);
			}

			put(request, treeId, openNodesString);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static String getOpenNodes(
		HttpServletRequest request, String treeId) {

		try {
			return get(request, treeId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}
	}

	public static void openLayoutNodes(
		HttpServletRequest request, String treeId, boolean privateLayout,
		long layoutId, boolean recursive) {

		try {
			List<String> layoutIds = new ArrayList<String>();

			layoutIds.add(String.valueOf(layoutId));

			if (recursive) {
				getLayoutIds(request, privateLayout, layoutId, layoutIds);
			}

			openNodes(
				request, treeId,
				layoutIds.toArray(new String[layoutIds.size()]));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void openNode(
		HttpServletRequest request, String treeId, String nodeId) {

		try {
			String openNodesString = get(request, treeId);

			openNodesString = StringUtil.add(openNodesString, nodeId);

			put(request, treeId, openNodesString);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void openNodes(
		HttpServletRequest request, String treeId, String[] nodeIds) {

		try {
			String openNodesString = get(request, treeId);

			for (String nodeId : nodeIds) {
				openNodesString = StringUtil.add(openNodesString, nodeId);
			}

			put(request, treeId, openNodesString);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected static String get(HttpServletRequest request, String key) {
		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(request);

			return preferences.getValue(
				SessionTreeJSClicks.class.getName(), key);
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}
	}

	protected static List<String> getLayoutIds(
			HttpServletRequest request, boolean privateLayout,
			long parentLayoutId, List<String> layoutIds)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, privateLayout, parentLayoutId);

		for (Layout layout : layouts) {
			layoutIds.add(String.valueOf(layout.getLayoutId()));

			getLayoutIds(
				request, privateLayout, layout.getLayoutId(), layoutIds);
		}

		return layoutIds;
	}

	protected static void put(
		HttpServletRequest request, String key, String value) {

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(request);

			preferences.setValue(
				SessionTreeJSClicks.class.getName(), key, value);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SessionTreeJSClicks.class);

}