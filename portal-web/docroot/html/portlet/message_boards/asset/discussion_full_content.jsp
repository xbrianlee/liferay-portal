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
String randomNamespace = PortalUtil.generateRandomKey(request, "discussion_full_content") + StringPool.UNDERLINE;

MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

MBThread thread = message.getThread();

MBMessage rootMessage = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());
MBMessage parentMessage = MBMessageLocalServiceUtil.getMessage(message.getParentMessageId());
%>

<table class="lfr-grid lfr-table">
<tr>
	<td colspan="2" id="<%= randomNamespace %>messageScroll<%= message.getMessageId() %>">
		<a name="<%= randomNamespace %>message_<%= message.getMessageId() %>"></a>
	</td>
</tr>
<tr>
	<td class="lfr-center lfr-top">
		<liferay-ui:user-display
			displayStyle="<%= 2 %>"
			userId="<%= message.getUserId() %>"
			userName="<%= HtmlUtil.escape(message.getUserName()) %>"
		/>
	</td>
	<td class="lfr-top stretch">
		<c:if test="<%= (message != null) && !message.isApproved() %>">
			<aui:model-context bean="<%= message %>" model="<%= MBMessage.class %>" />

			<div>
				<aui:workflow-status status="<%= message.getStatus() %>" />
			</div>
		</c:if>

		<div>
			<%= message.getBody() %>
		</div>

		<br />

		<div>
			<c:if test="<%= message.getParentMessageId() == rootMessage.getMessageId() %>">
				<%= LanguageUtil.format(pageContext, "posted-on-x", dateFormatDateTime.format(message.getModifiedDate())) %>
			</c:if>
		</div>
	</td>
</tr>
</table>

<c:if test="<%= (parentMessage != null) && !parentMessage.isRoot() %>">
	<h3><liferay-ui:message key="replying-to" />:</h3>

	<%
	request.setAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE, parentMessage);
	%>

	<liferay-util:include page="/html/portlet/message_boards/asset/discussion_full_content.jsp" />
</c:if>