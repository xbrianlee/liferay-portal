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

<%@ include file="/html/taglib/ui/ddm_template_selector/init.jsp" %>

<%
long classNameId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:ddm-template-select:classNameId"));
String displayStyle = (String)request.getAttribute("liferay-ui:ddm-template-select:displayStyle");
long displayStyleGroupId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:ddm-template-select:displayStyleGroupId"));
List<String> displayStyles = (List<String>)request.getAttribute("liferay-ui:ddm-template-select:displayStyles");
String icon = GetterUtil.getString((String)request.getAttribute("liferay-ui:ddm-template-select:icon"), "configuration");
String label = (String)request.getAttribute("liferay-ui:ddm-template-select:label");
String refreshURL = (String)request.getAttribute("liferay-ui:ddm-template-select:refreshURL");
boolean showEmptyOption = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:ddm-template-select:showEmptyOption"));

long ddmTemplateGroupId = PortletDisplayTemplateUtil.getDDMTemplateGroupId(themeDisplay.getScopeGroupId());

Group ddmTemplateGroup = GroupLocalServiceUtil.getGroup(ddmTemplateGroupId);

DDMTemplate ddmTemplate = null;
%>

<aui:input id="displayStyleGroupId" name="preferences--displayStyleGroupId--" type="hidden" value="<%= String.valueOf(displayStyleGroupId) %>" />

<aui:select id="displayStyle" inlineField="<%= true %>" label="<%= label %>" name="preferences--displayStyle--">
	<c:if test="<%= showEmptyOption %>">
		<aui:option label="default" selected="<%= Validator.isNull(displayStyle) %>" />
	</c:if>

	<c:if test="<%= (displayStyles != null) && !displayStyles.isEmpty() %>">
		<optgroup label="<liferay-ui:message key="default" />">

			<%
			for (String curDisplayStyle : displayStyles) {
			%>

				<aui:option label="<%= HtmlUtil.escape(curDisplayStyle) %>" selected="<%= displayStyle.equals(curDisplayStyle) %>" />

			<%
			}
			%>

		</optgroup>
	</c:if>

	<%
	Map<String,Object> data = new HashMap<String,Object>();

	if (displayStyle.startsWith(PortletDisplayTemplate.DISPLAY_STYLE_PREFIX)) {
		ddmTemplate = PortletDisplayTemplateUtil.fetchDDMTemplate(displayStyleGroupId, displayStyle);
	}

	List<DDMTemplate> companyPortletDDMTemplates = DDMTemplateLocalServiceUtil.getTemplates(themeDisplay.getCompanyGroupId(), classNameId, 0);
	%>

	<c:if test="<%= (companyPortletDDMTemplates != null) && !companyPortletDDMTemplates.isEmpty() %>">
		<optgroup label="<liferay-ui:message key="global" />">

			<%
			data.put("displaystylegroupid", themeDisplay.getCompanyGroupId());

			for (DDMTemplate companyPortletDDMTemplate : companyPortletDDMTemplates) {
				if (!DDMTemplatePermission.contains(permissionChecker, companyPortletDDMTemplate, PortletKeys.PORTLET_DISPLAY_TEMPLATES, ActionKeys.VIEW)) {
					continue;
				}
			%>

				<aui:option data="<%= data %>" label="<%= HtmlUtil.escape(companyPortletDDMTemplate.getName(locale)) %>" selected="<%= (ddmTemplate != null) && (companyPortletDDMTemplate.getTemplateId() == ddmTemplate.getTemplateId()) %>" value="<%= PortletDisplayTemplate.DISPLAY_STYLE_PREFIX + companyPortletDDMTemplate.getUuid() %>" />

			<%
			}
			%>

		</optgroup>
	</c:if>

	<%
	List<DDMTemplate> groupPortletDDMTemplates = null;

	if (ddmTemplateGroupId != themeDisplay.getCompanyGroupId()) {
		groupPortletDDMTemplates = DDMTemplateLocalServiceUtil.getTemplates(ddmTemplateGroupId, classNameId, 0);

		data.put("displaystylegroupid", ddmTemplateGroupId);
	}
	%>

	<c:if test="<%= (groupPortletDDMTemplates != null) && !groupPortletDDMTemplates.isEmpty() %>">
		<optgroup label="<%= HtmlUtil.escape(ddmTemplateGroup.getDescriptiveName(locale)) %>">

		<%
		for (DDMTemplate groupPortletDDMTemplate : groupPortletDDMTemplates) {
			if (!DDMTemplatePermission.contains(permissionChecker, groupPortletDDMTemplate, PortletKeys.PORTLET_DISPLAY_TEMPLATES, ActionKeys.VIEW)) {
				continue;
			}
		%>

			<aui:option data="<%= data %>" label="<%= HtmlUtil.escape(groupPortletDDMTemplate.getName(locale)) %>" selected="<%= (ddmTemplate != null) && (groupPortletDDMTemplate.getTemplateId() == ddmTemplate.getTemplateId()) %>" value="<%= PortletDisplayTemplate.DISPLAY_STYLE_PREFIX + groupPortletDDMTemplate.getUuid() %>" />

		<%
		}
		%>

		</optgroup>
	</c:if>
</aui:select>

<liferay-ui:icon
	id="selectDDMTemplate"
	image="<%= icon %>"
	label="<%= true %>"
	message='<%= LanguageUtil.format(pageContext, "manage-display-templates-for-x", HtmlUtil.escape(ddmTemplateGroup.getDescriptiveName(locale)), false) %>'
	url="javascript:;"
/>

<liferay-portlet:renderURL plid="<%= themeDisplay.getPlid() %>" portletName="<%= PortletKeys.DYNAMIC_DATA_MAPPING %>" var="basePortletURL">
	<portlet:param name="showHeader" value="<%= Boolean.FALSE.toString() %>" />
</liferay-portlet:renderURL>

<aui:script use="aui-base">
	var selectDDMTemplate = A.one('#<portlet:namespace />selectDDMTemplate');

	if (selectDDMTemplate) {
		var windowId = A.guid();

		selectDDMTemplate.on(
			'click',
			function(event) {
				Liferay.Util.openDDMPortlet(
					{
						basePortletURL: '<%= basePortletURL %>',
						classNameId: '<%= classNameId %>',
						dialog: {
							width: 1024
						},
						groupId: <%= ddmTemplateGroupId %>,
						refererPortletName: '<%= PortletKeys.PORTLET_DISPLAY_TEMPLATES %>',
						struts_action: '/dynamic_data_mapping/view_template',
						title: '<%= UnicodeLanguageUtil.get(pageContext, "application-display-templates") %>'
					},
					function(event) {
						if (!event.newVal) {
							submitForm(document.<portlet:namespace />fm, '<%= refreshURL %>');
						}
					}
				);
			}
		);
	}

	var displayStyleGroupIdInput = A.one('#<portlet:namespace />displayStyleGroupId');

	var displayStyleSelect = A.one('#<portlet:namespace />displayStyle');

	displayStyleSelect.on(
		'change',
		function(event) {
			var selectedIndex = event.currentTarget.get('selectedIndex');

			if (selectedIndex >= 0) {
				var selectedOption = event.currentTarget.get('options').item(selectedIndex);

				var displayStyleGroupId = selectedOption.attr('data-displaystylegroupid');

				if (displayStyleGroupId) {
					displayStyleGroupIdInput.set('value', displayStyleGroupId);
				}
			}
		}
	);
</aui:script>