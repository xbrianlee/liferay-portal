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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

MBMessage message = (MBMessage)objArray[0];
%>

<liferay-portlet:renderURL varImpl="viewMessage">
	<portlet:param name="struts_action" value="/message_boards/view_message" />
	<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
</liferay-portlet:renderURL>

<div class="question-subject">
	<a class="question-subject" href="<%= viewMessage.toString() %>"><%= HtmlUtil.escape(message.getSubject()) %></a>
</div>

<div class="summary">

	<%
	String msgBody = message.getBody();

	if (message.isFormatBBCode()) {
		msgBody = BBCodeTranslatorUtil.getHTML(msgBody);
		msgBody = StringUtil.replace(msgBody, "@theme_images_path@/emoticons", themeDisplay.getPathThemeImages() + "/emoticons");
	}
	%>

	<%= StringUtil.shorten(msgBody, 250) %>
</div>

<div class="tags">
	<liferay-ui:asset-tags-summary
		className="<%= MBMessage.class.getName() %>"
		classPK="<%= message.getMessageId() %>"
	/>
</div>