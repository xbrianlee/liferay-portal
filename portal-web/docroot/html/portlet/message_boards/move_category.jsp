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
String redirect = ParamUtil.getString(request, "redirect");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId", MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= (category == null) %>"
	title='<%= LanguageUtil.format(pageContext, "move-x", category.getName()) %>'
/>

<portlet:actionURL var="moveCategoryURL">
	<portlet:param name="struts_action" value="/message_boards/move_category" />
</portlet:actionURL>

<aui:form action="<%= moveCategoryURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="mbCategoryId" type="hidden" value="<%= categoryId %>" />
	<aui:input name="parentCategoryId" type="hidden" value="<%= parentCategoryId %>" />

	<aui:model-context bean="<%= category %>" model="<%= MBCategory.class %>" />

	<aui:fieldset>
		<aui:field-wrapper label="parent-category[message-board]">

			<%
			String parentCategoryName = StringPool.BLANK;

			try {
				MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

				parentCategoryName = parentCategory.getName();
			}
			catch (NoSuchCategoryException nsce) {
			}
			%>

			<div class="input-append">
				<liferay-ui:input-resource id="parentCategoryName" url="<%= parentCategoryName %>" />

				<aui:button name="selectCategoryButton" value="select" />

				<aui:button disabled="<%= (parentCategoryId <= 0) %>" name="removeCategoryButton" onClick='<%= renderResponse.getNamespace() + "removeCategory();" %>' value="remove" />
			</div>

			<aui:input label="merge-with-parent-category" name="mergeWithParentCategory" type="checkbox" />
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="move" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />removeCategory() {
		document.<portlet:namespace />fm.<portlet:namespace />parentCategoryId.value = "<%= MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>";

		document.getElementById('<portlet:namespace />parentCategoryName').value = "";

		Liferay.Util.toggleDisabled('#<portlet:namespace />removeCategoryButton', true);
	}
</aui:script>

<%
if (category != null) {
	MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "move"), currentURL);
}
%>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />selectCategoryButton').on(
		'click',
		function(event) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						modal: true,
						width: 680
					},
					id: '<portlet:namespace />selectCategory',
					title: '<liferay-ui:message arguments="category" key="select-x" />',
					uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/message_boards/select_category" /><portlet:param name="mbCategoryId" value="<%= String.valueOf((category == null) ? MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID : category.getParentCategoryId()) %>" /></portlet:renderURL>'
				},
				function(event) {
					document.<portlet:namespace />fm.<portlet:namespace />parentCategoryId.value = event.categoryid;

					document.getElementById('<portlet:namespace />parentCategoryName').value = event.name;

					Liferay.Util.toggleDisabled('#<portlet:namespace />removeCategoryButton', false);
				}
			);
		}
	);
</aui:script>