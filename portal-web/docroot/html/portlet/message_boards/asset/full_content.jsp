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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

request.setAttribute("edit_message.jsp-category", message.getCategory());
request.setAttribute("edit_message.jsp-className", message.getClassName());
request.setAttribute("edit_message.jsp-depth", 0);
request.setAttribute("edit_message.jsp-editable", Boolean.FALSE);
request.setAttribute("edit_message.jsp-message", message);
request.setAttribute("edit-message.jsp-showPermanentLink", Boolean.FALSE);
request.setAttribute("edit_message.jsp-thread", message.getThread());
%>

<liferay-util:include page="/html/portlet/message_boards/view_thread_message.jsp" />

<c:if test="<%= portletName.equals(PortletKeys.TRASH) %>">

	<%
	MBThread thread = message.getThread();

	PortletURL viewContentURL = renderResponse.createRenderURL();

	viewContentURL.setParameter("struts_action", "/trash/view_content");
	viewContentURL.setParameter("redirect", currentURL);
	viewContentURL.setParameter("className", MBThread.class.getName());
	viewContentURL.setParameter("classPK", String.valueOf(thread.getPrimaryKey()));
	viewContentURL.setParameter("showActions", Boolean.FALSE.toString());
	viewContentURL.setParameter("showAssetMetadata", Boolean.TRUE.toString());
	viewContentURL.setParameter("showEditURL", Boolean.FALSE.toString());
	%>

	<div class="asset-more">
		<a href="<%= viewContentURL.toString() %>"><liferay-ui:message key="view-in-context" /> &raquo;</a>
	</div>
</c:if>