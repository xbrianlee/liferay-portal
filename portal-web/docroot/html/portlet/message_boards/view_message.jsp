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
MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

MBMessage message = messageDisplay.getMessage();

MBCategory category = messageDisplay.getCategory();

String displayStyle = BeanPropertiesUtil.getString(category, "displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);

if (Validator.isNull(displayStyle)) {
	displayStyle = MBCategoryConstants.DEFAULT_DISPLAY_STYLE;
}

if ((message != null) && layout.isTypeControlPanel()) {
	MBUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
}
%>

<liferay-util:include page="/html/portlet/message_boards/top_links.jsp" />

<div class="displayStyle-<%= displayStyle %>">
	<liferay-util:include page='<%= "/html/portlet/message_boards/view_message_" + displayStyle + ".jsp" %>' />
</div>