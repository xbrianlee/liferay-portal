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

<%@ page import="com.liferay.portal.LayoutBranchNameException" %><%@
page import="com.liferay.portal.LayoutSetBranchNameException" %><%@
page import="com.liferay.portal.kernel.staging.StagingUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTask" %><%@
page import="com.liferay.portal.lar.backgroundtask.LayoutStagingBackgroundTaskExecutor" %><%@
page import="com.liferay.portal.service.LayoutSetBranchLocalServiceUtil" %><%@
page import="com.liferay.portal.service.permission.LayoutBranchPermissionUtil" %><%@
page import="com.liferay.portal.service.permission.LayoutSetBranchPermissionUtil" %><%@
page import="com.liferay.portal.util.comparator.LayoutRevisionCreateDateComparator" %><%@
page import="com.liferay.portal.util.comparator.LayoutRevisionIdComparator" %>

<%
Layout selLayout = layout;

long selPlid = ParamUtil.getLong(request, "selPlid");

if (selPlid > 0) {
	selLayout = LayoutLocalServiceUtil.getLayout(selPlid);
}

Group group = null;
Group liveGroup = null;
Group stagingGroup = null;

long groupId = ParamUtil.getLong(request, "groupId");
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");

if (groupId > 0) {
	group = GroupLocalServiceUtil.getGroup(groupId);
}
else if (selLayout != null) {
	group = selLayout.getGroup();

	privateLayout = selLayout.isPrivateLayout();
}

if (group != null) {
	if (group.isStagingGroup()) {
		liveGroup = group.getLiveGroup();
		stagingGroup = group;
	}
	else if (group.isStaged()) {
		if (group.isStagedRemotely()) {
			liveGroup = group;
			stagingGroup = group;
		}
		else {
			liveGroup = group;
			stagingGroup = group.getStagingGroup();
		}
	}
}

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/html/portlet/staging_bar/init-ext.jsp" %>