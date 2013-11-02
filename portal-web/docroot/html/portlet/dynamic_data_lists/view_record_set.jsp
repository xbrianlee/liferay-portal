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

<%@ include file="/html/portlet/dynamic_data_lists/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

DDLRecordSet recordSet = (DDLRecordSet)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD_SET);

long displayDDMTemplateId = ParamUtil.getLong(request, "displayDDMTemplateId");

boolean spreadsheet = ParamUtil.getBoolean(request, "spreadsheet");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= false %>"
	title="<%= recordSet.getName(locale) %>"
/>

<c:choose>
	<c:when test="<%= displayDDMTemplateId > 0 %>">
		<liferay-util:include page="/html/portlet/dynamic_data_lists/view_template_records.jsp" />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= spreadsheet %>">
				<liferay-util:include page="/html/portlet/dynamic_data_lists/view_spreadsheet_records.jsp" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/html/portlet/dynamic_data_lists/view_records.jsp" />
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<%
if (portletName.equals(PortletKeys.DYNAMIC_DATA_LISTS)) {
	PortalUtil.setPageSubtitle(recordSet.getName(locale), request);
	PortalUtil.setPageDescription(recordSet.getDescription(locale), request);
}

PortalUtil.addPortletBreadcrumbEntry(request, recordSet.getName(locale), currentURL);
%>