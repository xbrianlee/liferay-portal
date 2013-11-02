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

<%@ include file="/html/portlet/mobile_device_rules/action/init.jsp" %>

<%
String url = GetterUtil.getString(typeSettingsProperties.get("url"));
boolean ajax = GetterUtil.getBoolean(request.getParameter("ajax"));
%>

<aui:input cssClass="lfr-input-text-container" name="url" type="text" value="<%= url %>">
	<aui:validator name="required" />
</aui:input>

<c:if test="<%= ajax %>">
	<aui:script use="liferay-form">
		var form = Liferay.Form.get('<portlet:namespace />fm');

		if (form) {
			var rules = form.formValidator.get('rules');

			var fieldName = '<portlet:namespace />url';

			if (!(fieldName in rules)) {
				rules[fieldName] = {
					custom: false,
					required: true
				};
			}
		}
	</aui:script>
</c:if>