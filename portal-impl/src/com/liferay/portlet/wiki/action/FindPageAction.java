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

package com.liferay.portlet.wiki.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Kong
 */
public class FindPageAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(primaryKey);

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		return node.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "pageResourcePrimKey";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		if (portletId.equals(PortletKeys.WIKI_ADMIN)) {
			return "/wiki_admin/view";
		}
		else if (portletId.equals(PortletKeys.WIKI)) {
			return "/wiki/view";
		}
		else {
			return "/wiki_display/view";
		}
	}

	@Override
	protected String[] initPortletIds() {

		// Order is important. See LPS-23770.

		return new String[] {
			PortletKeys.WIKI_ADMIN, PortletKeys.WIKI, PortletKeys.WIKI_DISPLAY
		};
	}

	@Override
	protected PortletURL processPortletURL(
			HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		long pageResourcePrimKey = ParamUtil.getLong(
			request, getPrimaryKeyParameterName());

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				pageResourcePrimKey);

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		portletURL.setParameter("nodeName", node.getName());
		portletURL.setParameter("title", pageResource.getTitle());

		return portletURL;
	}

}