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
DDLRecordVersion recordVersion = (DDLRecordVersion)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD_VERSION);

DDLRecord record = (DDLRecord)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD);

DDLRecordSet recordSet = record.getRecordSet();

DDMStructure ddmStructure = recordSet.getDDMStructure();

Fields fields = StorageEngineUtil.getFields(recordVersion.getDDMStorageId());
%>

<liferay-ddm:html
	classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
	classPK="<%= ddmStructure.getPrimaryKey() %>"
	fields="<%= fields %>"
	readOnly="<%= true %>"
	requestedLocale="<%= locale %>"
/>