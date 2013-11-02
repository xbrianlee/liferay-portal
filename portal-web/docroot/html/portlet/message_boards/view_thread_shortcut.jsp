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
boolean editable = true;

MBTreeWalker treeWalker = (MBTreeWalker)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER);
MBMessage selMessage = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE);
MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE);
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY);
MBThread thread = (MBThread)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD);
MBThreadFlag threadFlag = (MBThreadFlag)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD_FLAG);
boolean lastNode = ((Boolean)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE)).booleanValue();
int depth = ((Integer)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH)).intValue();

long threadFlagModifiedTime = 0;

if (threadFlag != null) {
	Date threadFlagModifiedDate = threadFlag.getModifiedDate();

	threadFlagModifiedTime = threadFlagModifiedDate.getTime();
}
%>

<c:if test="<%= (message.getMessageId() != selMessage.getMessageId()) || MBUtil.isViewableMessage(themeDisplay, message) %>">
	<tr>
		<td class="table-cell" style="padding-left: <%= depth > 0 ? depth * 10 : 5 %>px; width: 90%;" valign="middle">
			<c:if test="<%= !message.isRoot() %>">
				<c:choose>
					<c:when test="<%= !lastNode %>">
						<img alt="" src="<%= themeDisplay.getPathThemeImages() %>/message_boards/t.png" />
					</c:when>
					<c:otherwise>
						<img alt="" src="<%= themeDisplay.getPathThemeImages() %>/message_boards/l.png" />
					</c:otherwise>
				</c:choose>
			</c:if>

			<%
			String layoutFullURL = PortalUtil.getLayoutFullURL(themeDisplay);

			String messageURL = layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR + "message_boards/view_message/" + selMessage.getMessageId();

			if (windowState.equals(WindowState.MAXIMIZED)) {
				messageURL += "/maximized";
			}

			String rowHREF = "#" + renderResponse.getNamespace() + "message_" + message.getMessageId();

			if (!themeDisplay.isFacebook()) {
				rowHREF = messageURL + rowHREF;
			}

			boolean readThread = true;

			if (themeDisplay.isSignedIn()) {
				Date messageModifiedDate = message.getModifiedDate();

				if (threadFlagModifiedTime < messageModifiedDate.getTime()) {
					readThread = false;
				}
			}
			%>

			<a href="<%= rowHREF %>">
				<c:if test="<%= !readThread %>">
					<strong>
				</c:if>

				<%= HtmlUtil.escape(message.getSubject()) %>

				<c:if test="<%= !readThread %>">
					</strong>
				</c:if>
			</a>
		</td>
		<td class="table-cell" style="white-space: nowrap;">
			<a href="<%= rowHREF %>">
				<c:if test="<%= !readThread %>">
					<strong>
				</c:if>

				<c:choose>
					<c:when test="<%= message.isAnonymous() %>">
						<liferay-ui:message key="anonymous" />
					</c:when>
					<c:otherwise>
						<%= HtmlUtil.escape(PortalUtil.getUserName(message)) %>
					</c:otherwise>
				</c:choose>

				<c:if test="<%= !readThread %>">
					</strong>
				</c:if>
			</a>
		</td>
		<td class="table-cell" style="white-space: nowrap;">
			<a href="<%= rowHREF %>"><%= dateFormatDateTime.format(message.getModifiedDate()) %></a>
		</td>
	</tr>
</c:if>

<%
List messages = treeWalker.getMessages();
int[] range = treeWalker.getChildrenRange(message);

depth++;

for (int i = range[0]; i < range[1]; i++) {
	MBMessage curMessage = (MBMessage)messages.get(i);

	if (!MBUtil.isViewableMessage(themeDisplay, curMessage, message)) {
		continue;
	}

	boolean lastChildNode = false;

	if ((i + 1) == range[1]) {
		lastChildNode = true;
	}

	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER, treeWalker);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY, category);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE, curMessage);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH, new Integer(depth));
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE, selMessage);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE, Boolean.valueOf(lastChildNode));
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD, thread);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD_FLAG, threadFlag);
%>

	<liferay-util:include page="/html/portlet/message_boards/view_thread_shortcut.jsp" />

<%
}
%>