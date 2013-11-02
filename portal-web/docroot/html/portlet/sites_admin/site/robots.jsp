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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
Long liveGroupId = (Long)request.getAttribute("site.liveGroupId");

LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroupId, false);

String publicVirtualHostName = publicLayoutSet.getVirtualHostname();

if (Validator.isNull(publicVirtualHostName) && Validator.isNotNull(PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME) ) {
	Group defaultGroup = GroupLocalServiceUtil.getGroup(company.getCompanyId(), PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

	if (publicLayoutSet.getGroupId() == defaultGroup.getGroupId()) {
		publicVirtualHostName = company.getVirtualHostname();
	}
}

String defaultPublicRobots = RobotsUtil.getRobots(publicLayoutSet);

String publicRobots = ParamUtil.getString(request, "robots", defaultPublicRobots);

LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroupId, true);

String defaultPrivateRobots = RobotsUtil.getRobots(privateLayoutSet);

String privateRobots = ParamUtil.getString(request, "robots", defaultPrivateRobots);
%>

<liferay-ui:error-marker key="errorSection" value="robots" />

<h3><liferay-ui:message key="robots" /></h3>

<aui:fieldset label="public-pages">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(publicVirtualHostName) %>">
			<aui:input cols="60" name="publicRobots" rows="15" type="textarea" value="<%= publicRobots %>" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="please-set-the-virtual-host-before-you-set-the-robots-txt" />
			</div>
		</c:otherwise>
	</c:choose>
</aui:fieldset>

<aui:fieldset label="private-pages">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(privateLayoutSet.getVirtualHostname()) %>">
			<aui:input cols="60" name="privateRobots" rows="15" type="textarea" value="<%= privateRobots %>" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="please-set-the-virtual-host-before-you-set-the-robots-txt" />
			</div>
		</c:otherwise>
	</c:choose>
</aui:fieldset>