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

<%@ include file="/html/portlet/mobile_device_rules/init.jsp" %>

<%
MDRAction action = (MDRAction)request.getAttribute(WebKeys.MOBILE_DEVICE_RULES_RULE_GROUP_ACTION);

UnicodeProperties typeSettingsProperties = null;

if (action != null) {
	typeSettingsProperties = action.getTypeSettingsProperties();
}
else {
	typeSettingsProperties = new UnicodeProperties();
}
%>