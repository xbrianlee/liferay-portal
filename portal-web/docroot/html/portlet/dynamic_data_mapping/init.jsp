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

<%@ page import="com.liferay.portal.kernel.editor.EditorUtil" %><%@
page import="com.liferay.portal.kernel.template.TemplateConstants" %><%@
page import="com.liferay.portal.kernel.template.TemplateVariableDefinition" %><%@
page import="com.liferay.portal.kernel.template.TemplateVariableGroup" %><%@
page import="com.liferay.portal.kernel.template.comparator.TemplateHandlerComparator" %><%@
page import="com.liferay.portal.template.TemplateContextHelper" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata" %><%@
page import="com.liferay.portlet.dynamicdatalists.model.DDLRecordSet" %><%@
page import="com.liferay.portlet.dynamicdatamapping.RequiredStructureException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.RequiredTemplateException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureDuplicateElementException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureFieldException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureNameException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureXsdException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateNameException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateScriptException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateSmallImageNameException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateSmallImageSizeException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureDisplayTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureSearch" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureSearchTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateDisplayTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateSearch" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateSearchTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMPermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMStructurePermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.storage.StorageType" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMDisplay" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMDisplayRegistryUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMTemplateHelperUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMXSDUtil" %>

<%@ page import="java.util.StringTokenizer" %>

<%
PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

String refererPortletName = ParamUtil.getString(request, "refererPortletName", portletName);
String refererWebDAVToken = ParamUtil.getString(request, "refererWebDAVToken", portletConfig.getInitParameter("refererWebDAVToken"));
String scopeTitle = ParamUtil.getString(request, "scopeTitle");
boolean showGlobalScope = ParamUtil.getBoolean(request, "showGlobalScope");
boolean showManageTemplates = ParamUtil.getBoolean(request, "showManageTemplates", true);
boolean showToolbar = ParamUtil.getBoolean(request, "showToolbar", true);

DDMDisplay ddmDisplay = DDMDisplayRegistryUtil.getDDMDisplay(refererPortletName);

long scopeClassNameId = PortalUtil.getClassNameId(ddmDisplay.getStructureType());

String scopeAvailableFields = ddmDisplay.getAvailableFields();
String scopeStorageType = ddmDisplay.getStorageType();
String scopeTemplateType = ddmDisplay.getTemplateType();

String storageTypeValue = StringPool.BLANK;

if (scopeStorageType.equals("expando")) {
	storageTypeValue = StorageType.EXPANDO.getValue();
}
else if (scopeStorageType.equals("xml")) {
	storageTypeValue = StorageType.XML.getValue();
}

String templateTypeValue = StringPool.BLANK;

if (scopeTemplateType.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
}
else if (scopeTemplateType.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_FORM;
}
%>

<%@ include file="/html/portlet/dynamic_data_mapping/init-ext.jsp" %>