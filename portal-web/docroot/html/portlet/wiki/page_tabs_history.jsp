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
WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter("struts_action", "/wiki/view");
viewPageURL.setParameter("nodeName", node.getName());
viewPageURL.setParameter("title", wikiPage.getTitle());

PortletURL viewPageHistoryURL = renderResponse.createRenderURL();

viewPageHistoryURL.setParameter("struts_action", "/wiki/view_page_history");
viewPageHistoryURL.setParameter("redirect", viewPageURL.toString());
viewPageHistoryURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
viewPageHistoryURL.setParameter("title", wikiPage.getTitle());

PortletURL viewPageActivitiesURL = PortletURLUtil.clone(viewPageHistoryURL, renderResponse);

viewPageActivitiesURL.setParameter("struts_action", "/wiki/view_page_activities");
%>

<liferay-ui:tabs
	names="activities,versions"
	param="tabs3"
	url0="<%= viewPageActivitiesURL.toString() %>"
	url1="<%= viewPageHistoryURL.toString() %>"
/>