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
String redirect = ParamUtil.getString(request, "redirect");

WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

String title = wikiPage.getTitle();

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter("struts_action", "/wiki/view");
viewPageURL.setParameter("nodeName", node.getName());
viewPageURL.setParameter("title", wikiPage.getTitle());

PortletURL editPageURL = renderResponse.createRenderURL();

editPageURL.setParameter("struts_action", "/wiki/edit_page");
editPageURL.setParameter("redirect", viewPageURL.toString());
editPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
editPageURL.setParameter("title", title);

PortletURL viewPageDetailsURL = renderResponse.createRenderURL();

viewPageDetailsURL.setParameter("struts_action", "/wiki/view_page_details");
viewPageDetailsURL.setParameter("redirect", viewPageURL.toString());
viewPageDetailsURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
viewPageDetailsURL.setParameter("title", wikiPage.getTitle());

PortletURL viewPageHistoryURL = PortletURLUtil.clone(viewPageDetailsURL, renderResponse);

viewPageHistoryURL.setParameter("struts_action", "/wiki/view_page_activities");

PortletURL viewPageIncomingLinksURL = PortletURLUtil.clone(viewPageDetailsURL, renderResponse);

viewPageIncomingLinksURL.setParameter("struts_action", "/wiki/view_page_incoming_links");

PortletURL viewPageOutgoingLinksURL = PortletURLUtil.clone(viewPageDetailsURL, renderResponse);

viewPageOutgoingLinksURL.setParameter("struts_action", "/wiki/view_page_outgoing_links");

PortletURL viewPageAttachmentsURL = PortletURLUtil.clone(viewPageDetailsURL, renderResponse);

viewPageAttachmentsURL.setParameter("struts_action", "/wiki/view_page_attachments");

PortletURL viewPageActivitiesURL = PortletURLUtil.clone(viewPageDetailsURL, renderResponse);

viewPageActivitiesURL.setParameter("struts_action", "/wiki/view_page_activities");
%>

<%@ include file="/html/portlet/wiki/page_name.jspf" %>

<c:choose>
	<c:when test="<%= WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) %>">
		<liferay-ui:tabs
			names="content,details,history,incoming-links,outgoing-links,attachments"
			url0="<%= editPageURL.toString() %>"
			url1="<%= viewPageDetailsURL.toString() %>"
			url2="<%= viewPageHistoryURL.toString() %>"
			url3="<%= viewPageIncomingLinksURL.toString() %>"
			url4="<%= viewPageOutgoingLinksURL.toString() %>"
			url5="<%= viewPageAttachmentsURL.toString() %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-ui:tabs
			names="details,history,incoming-links,outgoing-links,attachments"
			url0="<%= viewPageDetailsURL.toString() %>"
			url1="<%= viewPageHistoryURL.toString() %>"
			url2="<%= viewPageIncomingLinksURL.toString() %>"
			url3="<%= viewPageOutgoingLinksURL.toString() %>"
			url4="<%= viewPageAttachmentsURL.toString() %>"
		/>
	</c:otherwise>
</c:choose>