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

<%@ include file="/html/portlet/expando/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

List<String> headerNames = new ArrayList<String>();

headerNames.add("resource");
headerNames.add("custom-fields");

List<CustomAttributesDisplay> customAttributesDisplays = PortletLocalServiceUtil.getCustomAttributesDisplays();

Collections.sort(customAttributesDisplays, new CustomAttributesDisplayComparator(locale));
%>

<liferay-ui:search-container
	emptyResultsMessage='<%= LanguageUtil.get(pageContext, "custom-fields-are-not-enabled-for-any-resource") %>'
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= customAttributesDisplays %>"
		total="<%= customAttributesDisplays.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.expando.model.CustomAttributesDisplay"
		modelVar="customAttributesDisplay"
		stringKey="<%= true %>"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="struts_action" value="/expando/view_attributes" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="modelResource" value="<%= customAttributesDisplay.getClassName() %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-row-parameter
			name="customAttributesDisplay"
			value="<%= customAttributesDisplay %>"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="resource"
		>

			<%
			buffer.append("<img align=\"left\" border=\"0\" src=\"");
			buffer.append(customAttributesDisplay.getIconPath(themeDisplay));
			buffer.append("\" style=\"margin-right: 5px;\">");
			buffer.append(ResourceActionsUtil.getModelResource(locale, customAttributesDisplay.getClassName()));
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="custom-fields"
		>

			<%
			ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(company.getCompanyId(), customAttributesDisplay.getClassName());

			List<String> attributeNames = Collections.list(expandoBridge.getAttributeNames());

			for (int i = 0; i < attributeNames.size(); i++) {
				if (i > 0) {
					buffer.append(", ");
				}

				String name = attributeNames.get(i);

				String localizedName = LanguageUtil.get(pageContext, name);

				if (name.equals(localizedName)) {
					localizedName = TextFormatter.format(name, TextFormatter.J);
				}

				buffer.append(HtmlUtil.escape(localizedName));
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/expando/resource_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>