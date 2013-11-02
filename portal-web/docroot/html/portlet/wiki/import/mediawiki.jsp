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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<aui:input name="filesCount" type="hidden" value="3" />

<liferay-ui:error exception="<%= PortalException.class %>" message="please-provide-all-mandatory-files-and-make-sure-the-file-types-are-valid" />

<aui:fieldset>
	<aui:input helpMessage="import-wiki-pages-help" label="pages-file" name="file0" type="file" />

	<aui:input helpMessage="import-wiki-users-help" label='<%= LanguageUtil.get(pageContext, "users-file") + "(" + LanguageUtil.get(pageContext, "optional") + ")" %>' name="file1" type="file" />

	<aui:input helpMessage="import-wiki-images-help" label='<%= LanguageUtil.get(pageContext, "images-file") + "(" + LanguageUtil.get(pageContext, "optional") + ")" %>' name="file2" type="file" />

	<aui:input label='<%= WikiPageConstants.FRONT_PAGE + "(" + LanguageUtil.get(pageContext, "optional") + ")" %>' name="<%= WikiImporterKeys.OPTIONS_FRONT_PAGE %>" size="40" type="text" value="Main Page" />

	<aui:input checked="<%= true %>" label="import-only-the-latest-version-and-not-the-full-history" name="<%= WikiImporterKeys.OPTIONS_IMPORT_LATEST_VERSION %>" type="checkbox" />

	<aui:input checked="<%= true %>" helpMessage="import-wiki-strict-mode-help" label="strict-mode" name="<%= WikiImporterKeys.OPTIONS_STRICT_IMPORT_MODE %>" type="checkbox" />
</aui:fieldset>