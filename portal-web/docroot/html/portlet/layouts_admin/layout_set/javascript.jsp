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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
LayoutSet selLayoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));

UnicodeProperties layoutSetTypeSettings = selLayoutSet.getSettingsProperties();
%>

<liferay-ui:error-marker key="errorSection" value="javascript" />

<h3><liferay-ui:message key="javascript" /></h3>

<aui:fieldset>
	<aui:input cssClass="lfr-textarea-container" label="paste-javascript-code-that-will-be-executed-at-the-bottom-of-every-page" name="TypeSettingsProperties--javascript--" style="width: 300px; height: 300px" type="textarea" value='<%= layoutSetTypeSettings.getProperty("javascript") %>' wrap="soft" />
</aui:fieldset>