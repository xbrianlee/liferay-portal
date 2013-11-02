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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}

boolean groupTrashEnabled = PropertiesParamUtil.getBoolean(groupTypeSettings, request, "trashEnabled", true);

double trashEntriesMaxAge = PropertiesParamUtil.getInteger(groupTypeSettings, request, "trashEntriesMaxAge", PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.TRASH_ENTRIES_MAX_AGE)) / 1440.0;
%>

<h3><liferay-ui:message key="recycle-bin" /></h3>

<aui:fieldset>
	<aui:input id="trashEnabled" label="enable-recycle-bin" name="trashEnabled" type="checkbox" value="<%= groupTrashEnabled %>" />

	<div class="trash-entries-max-age">
		<aui:input disabled="<%= !groupTrashEnabled %>" helpMessage="trash-entries-max-age-help" label="trash-entries-max-age" name="trashEntriesMaxAge" type="text" value="<%= (trashEntriesMaxAge % 1 == 0) ? GetterUtil.getInteger(trashEntriesMaxAge) : String.valueOf(trashEntriesMaxAge) %>">
			<aui:validator name="min"><%= PropsValues.TRASH_ENTRY_CHECK_INTERVAL / 1440.0 %></aui:validator>
		</aui:input>
	</div>

</aui:fieldset>

<aui:script use="aui-base">
	var trashEnabledCheckbox = A.one('#<portlet:namespace />trashEnabledCheckbox');

	var trashEnabledDefault = trashEnabledCheckbox.attr('checked');

	trashEnabledCheckbox.on(
		'change',
		function(event) {
			var currentTarget = event.currentTarget;

			var trashEnabled = currentTarget.attr('checked');

			if (!trashEnabled && trashEnabledDefault) {
				if (!confirm('<%= HtmlUtil.escapeJS(LanguageUtil.get(pageContext, "disabling-the-recycle-bin-will-prevent-the-restoring-of-content-that-has-been-moved-to-the-recycle-bin")) %>')) {
					currentTarget.attr('checked', true);

					trashEnabled = true;
				}
			}

			var trashEntriesMaxAge = A.one('#<portlet:namespace />trashEntriesMaxAge');

			if (trashEntriesMaxAge) {
				trashEntriesMaxAge.attr('disabled', !trashEnabled);

				trashEntriesMaxAge.ancestor('.field').toggleClass('field-disabled', !trashEnabled);
			}
		}
	);
</aui:script>