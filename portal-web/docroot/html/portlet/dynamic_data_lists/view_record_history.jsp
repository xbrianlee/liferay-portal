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

DDLRecord record = (DDLRecord)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD);

DDLRecordSet recordSet = record.getRecordSet();

DDMStructure ddmStructure = recordSet.getDDMStructure();

long formDDMTemplateId = ParamUtil.getLong(request, "formDDMTemplateId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/dynamic_data_lists/view_record_history");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("recordId", String.valueOf(record.getRecordId()));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= LanguageUtil.format(pageContext, "x-history", ddmStructure.getName(locale)) %>'
/>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">

	<%
	SearchContainer searchContainer = new SearchContainer(renderRequest, portletURL, new ArrayList(), null);

	List headerNames = searchContainer.getHeaderNames();

	headerNames.add("id");
	headerNames.add("version");
	headerNames.add("status");
	headerNames.add("author");
	headerNames.add(StringPool.BLANK);

	int total = DDLRecordLocalServiceUtil.getRecordVersionsCount(record.getRecordId());

	searchContainer.setTotal(total);

	List<DDLRecordVersion> results = DDLRecordLocalServiceUtil.getRecordVersions(record.getRecordId(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		DDLRecordVersion recordVersion = results.get(i);

		recordVersion = recordVersion.toEscapedModel();

		ResultRow row = new ResultRow(recordVersion, recordVersion.getRecordVersionId(), i);

		row.setParameter("formDDMTemplateId", String.valueOf(formDDMTemplateId));

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/dynamic_data_lists/view_record");
		rowURL.setParameter("redirect", currentURL);
		rowURL.setParameter("recordId", String.valueOf(recordVersion.getRecordId()));
		rowURL.setParameter("version", recordVersion.getVersion());
		rowURL.setParameter("formDDMTemplateId", String.valueOf(formDDMTemplateId));

		// Record version id

		row.addText(String.valueOf(recordVersion.getRecordVersionId()), rowURL);

		// Version

		row.addText(recordVersion.getVersion(), rowURL);

		// Status

		row.addStatus(recordVersion.getStatus(), recordVersion.getStatusByUserId(), recordVersion.getStatusDate(), rowURL);

		// Author

		row.addText(PortalUtil.getUserName(recordVersion), rowURL);

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/dynamic_data_lists/record_version_action.jsp");

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>