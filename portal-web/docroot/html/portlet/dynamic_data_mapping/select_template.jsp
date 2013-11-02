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

<%@ include file="/html/portlet/dynamic_data_mapping/init.jsp" %>

<%
long templateId = ParamUtil.getLong(request, "templateId");

long classNameId = ParamUtil.getLong(request, "classNameId");
long classPK = ParamUtil.getLong(request, "classPK");
String eventName = ParamUtil.getString(request, "eventName", "selectStructure");

DDMStructure structure = null;

long structureClassNameId = PortalUtil.getClassNameId(DDMStructure.class);

if ((classPK > 0) && (structureClassNameId == classNameId)) {
	structure = DDMStructureServiceUtil.getStructure(classPK);
}

String title = ddmDisplay.getViewTemplatesTitle(structure, locale);
%>

<c:if test="<%= showToolbar %>">
	<liferay-util:include page="/html/portlet/dynamic_data_mapping/template_toolbar.jsp">
		<liferay-util:param name="redirect" value="<%= currentURL %>" />
		<liferay-util:param name="classNameId" value="<%= String.valueOf(classNameId) %>" />
		<liferay-util:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</liferay-util:include>
</c:if>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="struts_action" value="/dynamic_data_mapping/select_template" />
	<portlet:param name="classNameId" value="<%= String.valueOf(classNameId) %>" />
	<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	<portlet:param name="eventName" value="<%= eventName %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL.toString() %>" method="post" name="selectTemplateFm">
	<c:if test="<%= !showToolbar %>">
		<liferay-ui:header
			localizeTitle="<%= false %>"
			title="<%= title %>"
		/>
	</c:if>

	<div class="separator"><!-- --></div>

	<liferay-ui:search-container
		searchContainer="<%= new TemplateSearch(renderRequest, portletURL) %>"
	>
		<liferay-ui:search-container-results>
			<%@ include file="/html/portlet/dynamic_data_mapping/template_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.dynamicdatamapping.model.DDMTemplate"
			keyProperty="templateId"
			modelVar="template"
		>

			<liferay-ui:search-container-column-text
				name="id"
				value="<%= String.valueOf(template.getTemplateId()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="name"
				value="<%= HtmlUtil.escape(template.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				value="<%= HtmlUtil.escape(template.getDescription(locale)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				value="<%= template.getModifiedDate() %>"
			/>

			<liferay-ui:search-container-column-text>
				<c:if test="<%= template.getTemplateId() != templateId %>">

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("ddmtemplateid", template.getTemplateId());
					data.put("ddmtemplatekey", template.getTemplateKey());
					data.put("name", HtmlUtil.escapeAttribute(template.getName(locale)));
					%>

					<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />selectTemplateFm.<portlet:namespace />toggle_id_ddm_template_searchkeywords);
</aui:script>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectTemplateFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>