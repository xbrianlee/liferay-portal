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

<%@ include file="/html/portlet/admin/init.jsp" %>

<%
DLFileEntry dlFileEntry = null;

List<String> keys = null;
List<String> expandoBridgeAttributeNames = null;

List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getExtraSettingsFileEntries(0, 1);

if (!dlFileEntries.isEmpty()) {
	dlFileEntry = dlFileEntries.get(0);

	List<DLFileVersion> dlFileVersions = dlFileEntry.getFileVersions(WorkflowConstants.STATUS_ANY);

	for (DLFileVersion dlFileVersion : dlFileVersions) {
		UnicodeProperties extraSettingsProperties = dlFileVersion.getExtraSettingsProperties();

		if (extraSettingsProperties.isEmpty()) {
			continue;
		}

		keys = new ArrayList<String>(extraSettingsProperties.size());
		expandoBridgeAttributeNames = new ArrayList<String>(extraSettingsProperties.size());

		ExpandoBridge expandoBridge = dlFileEntry.getExpandoBridge();

		for (String key : extraSettingsProperties.keySet()) {
			if (expandoBridge.hasAttribute(key)) {
				expandoBridgeAttributeNames.add(key);
			}
			else {
				keys.add(key);
			}
		}

		break;
	}
}
%>

<c:choose>
	<c:when test="<%= dlFileEntry == null %>">
			<div class="alert alert-success">
				<liferay-ui:message key="there-are-no-longer-any-documents-and-media-files-with-extra-settings" />
			</div>
	</c:when>
	<c:otherwise>
		<c:if test="<%= (expandoBridgeAttributeNames != null) && !expandoBridgeAttributeNames.isEmpty() %>">
			<div class="alert alert-error">
				<%= LanguageUtil.format(pageContext, "custom-fields-already-exist-for-these-extra-settings-x", StringUtil.merge(expandoBridgeAttributeNames)) %>
			</div>
		</c:if>

		<portlet:actionURL var="convertDocumentLibraryExtraSettingsURL">
			<portlet:param name="struts_action" value="/admin_server/edit_document_library_extra_settings" />
		</portlet:actionURL>

		<aui:form action="<%= convertDocumentLibraryExtraSettingsURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "convertDocumentLibraryExtraSettings();" %>'>
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="keys" type="hidden" value="<%= StringUtil.merge(keys) %>" />

			<%
			for (String key : keys) {
				String selectName = "type_" + key;
			%>

				<aui:fieldset>
					<%= LanguageUtil.format(pageContext, "convert-extra-settings-key-from-x-to", key) %>

					<br />

					<aui:select helpMessage="custom-field-type-help" label="type" name="<%= selectName %>">
						<optgroup label="<liferay-ui:message key="presets" />">
							<aui:option label="selection-of-integer-values" value="PresetSelectionIntegerArray()" />
							<aui:option label="selection-of-decimal-values" value="PresetSelectionDoubleArray()" />
							<aui:option label="selection-of-text-values" value="PresetSelectionStringArray()" />
							<aui:option label="text-box" value="PresetTextBox()" />
							<aui:option label="text-box-indexed" value="PresetTextBoxIndexed()" />
							<aui:option label="text-field-secret" value="PresetTextFieldSecret()" />
							<aui:option label="text-field-indexed" selected="<%= true %>" value="PresetTextFieldIndexed()" />
						</optgroup>
						<optgroup label="<liferay-ui:message key="primitives" />">

							<%
							for (int curType : ExpandoColumnConstants.TYPES) {
								if ((curType == ExpandoColumnConstants.BOOLEAN_ARRAY) || (curType == ExpandoColumnConstants.DATE_ARRAY)) {
									continue;
								}
							%>

								<aui:option label="<%= ExpandoColumnConstants.getTypeLabel(curType) %>" value="<%= curType %>" />

							<%
							}
							%>

						</optgroup>
					</aui:select>
				</aui:fieldset>

			<%
			}
			%>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />convertDocumentLibraryExtraSettings(options) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "convert";

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>