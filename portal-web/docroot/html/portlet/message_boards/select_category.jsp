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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectCategory");

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

String categoryName = null;

if (category != null) {
	MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);

	categoryName = category.getName();
}
else {
	categoryName = LanguageUtil.get(pageContext, "message-boards-home");
}
%>

<aui:form method="post" name="selectCategoryFm">
	<liferay-ui:header
		title="message-boards-home"
	/>

	<liferay-ui:breadcrumb showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/message_boards/select_category");
	portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));
	%>

	<liferay-ui:search-container
		headerNames="category[message-board],num-of-categories,num-of-threads,num-of-posts,"
		iteratorURL="<%= portletURL %>"
		total="<%= MBCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED) %>"
	>
		<liferay-ui:search-container-results
			results="<%= MBCategoryServiceUtil.getCategories(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.messageboards.model.MBCategory"
			escapedModel="<%= true %>"
			keyProperty="categoryId"
			modelVar="curCategory"
		>

			<portlet:renderURL var="rowURL">
				<portlet:param name="struts_action" value="/message_boards/select_category" />
				<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				buffer="buffer"
				href="<%= rowURL %>"
				name="category[message-board]"
			>

				<%
				buffer.append(curCategory.getName());

				if (Validator.isNotNull(curCategory.getDescription())) {
					buffer.append("<br />");
					buffer.append(curCategory.getDescription());
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-categories"
				value="<%= String.valueOf(categoryDisplay.getSubcategoriesCount(curCategory)) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-threads"
				value="<%= String.valueOf(categoryDisplay.getSubcategoriesThreadsCount(curCategory)) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-posts"
				value="<%= String.valueOf(categoryDisplay.getSubcategoriesMessagesCount(curCategory)) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("categoryId", curCategory.getCategoryId());
				data.put("name", HtmlUtil.escapeAttribute(curCategory.getName()));
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button-row>

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("categoryId", categoryId);
			data.put("name", HtmlUtil.escapeAttribute(categoryName));
			%>

			<aui:button cssClass="selector-button"  data="<%= data %>" value="choose-this-category" />
		</aui:button-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectCategoryFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>