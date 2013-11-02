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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
SearchEntry entry = (SearchEntry)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW_ENTRY);

AnnouncementsDelivery delivery = (AnnouncementsDelivery)row.getObject();

boolean defaultValue = false;
boolean disabled = false;
String messageKey = StringPool.BLANK;
String param = "announcementsType" + delivery.getType();

int index = entry.getIndex();

if (index == 1) {
	defaultValue = delivery.isEmail();
	messageKey = "receive-x-announcements-via-email";
	param += "Email";
}
else if (index == 2) {
	defaultValue = delivery.isSms();
	messageKey = "receive-x-announcements-via-sms";
	param += "Sms";
}
else if (index == 3) {
	defaultValue = delivery.isWebsite();
	disabled = true;
	messageKey = "receive-x-announcements-via-website";
	param += "Website";
}
%>

<aui:input
	disabled="<%= disabled %>"
	label=""
	name="<%= param %>"
	title="<%= LanguageUtil.format(pageContext, messageKey, delivery.getType(), true) %>"
	type="checkbox"
	value="<%= defaultValue %>"
/>