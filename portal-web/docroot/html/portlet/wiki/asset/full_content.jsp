<%--
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
--%>

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

PortletURL viewPageURL = new PortletURLImpl(request, PortletKeys.WIKI, plid, PortletRequest.ACTION_PHASE);

viewPageURL.setParameter("struts_action", "/wiki/view");
viewPageURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
viewPageURL.setPortletMode(PortletMode.VIEW);
viewPageURL.setWindowState(WindowState.MAXIMIZED);

PortletURL editPageURL = new PortletURLImpl(request, PortletKeys.WIKI, plid, PortletRequest.ACTION_PHASE);

editPageURL.setParameter("struts_action", "/wiki/edit_page");
editPageURL.setParameter("redirect", currentURL);
editPageURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
editPageURL.setPortletMode(PortletMode.VIEW);
editPageURL.setWindowState(WindowState.MAXIMIZED);

String attachmentURLPrefix = themeDisplay.getPathMain() + "/wiki/get_page_attachment?p_l_id=" + themeDisplay.getPlid() + "&nodeId=" + wikiPage.getNodeId() + "&title=" + HttpUtil.encodeURL(wikiPage.getTitle()) + "&fileName=";

boolean workflowAssetPreview = ParamUtil.getBoolean(request, "workflowAssetPreview");

WikiPageDisplay pageDisplay = null;

if (!workflowAssetPreview && wikiPage.isApproved()) {
	pageDisplay = WikiCacheUtil.getDisplay(wikiPage.getNodeId(), wikiPage.getTitle(), viewPageURL, editPageURL, attachmentURLPrefix);
}
else {
	pageDisplay = WikiPageLocalServiceUtil.getPageDisplay(wikiPage, viewPageURL, editPageURL, attachmentURLPrefix);
}
%>

<%= pageDisplay.getFormattedContent() %>

<liferay-ui:custom-attributes-available className="<%= WikiPage.class.getName() %>">
	<liferay-ui:custom-attribute-list
		className="<%= WikiPage.class.getName() %>"
		classPK="<%= (wikiPage != null) ? wikiPage.getPrimaryKey() : 0 %>"
		editable="<%= false %>"
		label="<%= true %>"
	/>
</liferay-ui:custom-attributes-available>