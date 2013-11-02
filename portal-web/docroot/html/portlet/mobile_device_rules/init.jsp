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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.mobile.device.DeviceDetectionUtil" %><%@
page import="com.liferay.portal.kernel.mobile.device.VersionableName" %><%@
page import="com.liferay.portal.kernel.mobile.device.rulegroup.ActionHandlerManagerUtil" %><%@
page import="com.liferay.portal.kernel.mobile.device.rulegroup.RuleGroupProcessorUtil" %><%@
page import="com.liferay.portal.kernel.mobile.device.rulegroup.action.ActionHandler" %><%@
page import="com.liferay.portal.kernel.mobile.device.rulegroup.rule.UnknownRuleHandlerException" %><%@
page import="com.liferay.portal.mobile.device.rulegroup.rule.impl.SimpleRuleHandler" %><%@
page import="com.liferay.portal.plugin.PluginPackageUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.ActionTypeException" %><%@
page import="com.liferay.portlet.mobiledevicerules.NoSuchActionException" %><%@
page import="com.liferay.portlet.mobiledevicerules.NoSuchRuleException" %><%@
page import="com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupException" %><%@
page import="com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException" %><%@
page import="com.liferay.portlet.mobiledevicerules.model.MDRAction" %><%@
page import="com.liferay.portlet.mobiledevicerules.model.MDRRule" %><%@
page import="com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup" %><%@
page import="com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance" %><%@
page import="com.liferay.portlet.mobiledevicerules.search.RuleGroupChecker" %><%@
page import="com.liferay.portlet.mobiledevicerules.search.RuleGroupDisplayTerms" %><%@
page import="com.liferay.portlet.mobiledevicerules.search.RuleGroupSearch" %><%@
page import="com.liferay.portlet.mobiledevicerules.search.RuleGroupSearchTerms" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceServiceUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.MDRRuleLocalServiceUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.permission.MDRPermissionUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.permission.MDRRuleGroupInstancePermissionUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.service.permission.MDRRuleGroupPermissionUtil" %><%@
page import="com.liferay.portlet.mobiledevicerules.util.RuleGroupInstancePriorityComparator" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");

if (groupId == 0) {
	groupId = themeDisplay.getSiteGroupId();
}
%>

<%@ include file="/html/portlet/mobile_device_rules/init-ext.jsp" %>