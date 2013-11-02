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
boolean trashEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.TRASH_ENABLED);
%>

<h3><liferay-ui:message key="recycle-bin" /></h3>

<aui:fieldset>
	<aui:input helpMessage="enable-recycle-bin-default" id="trashEnabled" label="enable-recycle-bin" name='<%= "settings--" + PropsKeys.TRASH_ENABLED + "--" %>' type="checkbox" value="<%= trashEnabled %>" />
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
		}
	);
</aui:script>