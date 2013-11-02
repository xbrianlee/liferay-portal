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
Group group = (Group)request.getAttribute("edit_pages.jsp-group");
Layout selLayout = (Layout)request.getAttribute("edit_pages.jsp-selLayout");

UnicodeProperties layoutTypeSettings = selLayout.getTypeSettingsProperties();
%>

<liferay-ui:error-marker key="errorSection" value="advanced" />

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<h3><liferay-ui:message key="advanced" /></h3>

<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />

<aui:fieldset>
	<c:if test="<%= !group.isLayoutPrototype() %>">

		<%
		String queryString = GetterUtil.getString(layoutTypeSettings.getProperty("query-string"));
		%>

		<aui:input helpMessage="query-string-help" label="query-string" name="TypeSettingsProperties--query-string--" size="30" type="text" value="<%= queryString %>" />
	</c:if>

	<%
	String curTarget = GetterUtil.getString(layoutTypeSettings.getProperty("target"));
	%>

	<aui:input label="target" name="TypeSettingsProperties--target--" size="15" type="text" value="<%= HtmlUtil.escapeAttribute(curTarget) %>" />

	<aui:input name="iconImage" type="hidden" value="<%= selLayout.isIconImage() %>" />

	<aui:field-wrapper helpMessage="this-icon-will-be-shown-in-the-navigation-menu" label="icon" name="iconFileName">
		<aui:input inlineField="<%= true %>" label="" name="iconFileName" type="file" />

		<c:if test="<%= selLayout.getIconImage() %>">
			<liferay-ui:icon
				cssClass="modify-link"
				id="deleteIconLink"
				image="delete"
				label="<%= true %>"
				url="javascript:;"
			/>

			<div id="<portlet:namespace />layoutIconContainer">
				<liferay-theme:layout-icon layout="<%= selLayout %>" />
			</div>
		</c:if>
	</aui:field-wrapper>
</aui:fieldset>

<aui:script use="aui-base">
	var panel = A.one('#<portlet:namespace />advanced');

	var deleteLogoLink = panel.one('#<portlet:namespace />deleteIconLink');
	var iconFileNameInput = panel.one('#<portlet:namespace />iconFileName');
	var iconImageInput = panel.one('#<portlet:namespace />iconImage');
	var layoutIconContainer = panel.one('#<portlet:namespace />layoutIconContainer');

	var changeLogo = function(event) {
		var changeLogo = (event.type == 'change');

		iconImageInput.val(changeLogo);

		if (layoutIconContainer) {
			layoutIconContainer.hide();
		}
	};

	if (deleteLogoLink) {
		deleteLogoLink.on('click', changeLogo);
	}

	iconFileNameInput.on('change', changeLogo);
</aui:script>