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

<%@ include file="/html/portlet/asset_category_admin/init.jsp" %>

<aui:form name="fm">
	<aui:nav-bar>
		<aui:nav>
			<c:if test="<%= AssetPermission.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.ADD_VOCABULARY) %>">
				<aui:nav-item id="addVocabularyButton" label="add-vocabulary" />
			</c:if>

			<c:if test="<%= AssetPermission.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.ADD_CATEGORY) %>">
				<aui:nav-item disabled="<%= true %>" id="addCategoryButton" label="add-category" />
			</c:if>

			<c:if test="<%= AssetPermission.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="com.liferay.portlet.asset"
					modelResourceDescription="<%= themeDisplay.getScopeGroupName() %>"
					resourcePrimKey="<%= String.valueOf(themeDisplay.getSiteGroupId()) %>"
					var="permissionsURL"
					windowState="<%= LiferayWindowState.POP_UP.toString() %>"
				/>

				<aui:nav-item data-url="<%= permissionsURL %>" id="categoryPermissionsButton" label="permissions" />
			</c:if>

			<aui:nav-item dropdown="<%= true %>" label="actions">
				<aui:nav-item iconCssClass="icon-remove" id="deleteSelectedItems" label="delete" />
			</aui:nav-item>
		</aui:nav>

		<aui:nav-bar-search cssClass="pull-right">
			<aui:select cssClass="categories-admin-select-search" label="" name="categoriesAdminSelectSearch">
				<aui:option label="categories" />
				<aui:option label="vocabularies" selected="<%= true %>" />
			</aui:select>

			<liferay-ui:input-search cssClass="form-search" id="categoriesAdminSearchInput" name="tagsAdminSearchInput" showButton="<%= false %>" />
		</aui:nav-bar-search>
	</aui:nav-bar>

	<aui:row cssClass="categories-admin-content">
		<aui:col cssClass="vocabulary-list-container" width="<%= 25 %>">
			<span class="select-vocabularies-container">
				<aui:input cssClass="select-vocabularies" inline="<%= true %>" label="" name="checkAllVocabularies" title='<%= LanguageUtil.get(pageContext, "check-all-vocabularies") %>' type="checkbox" />
			</span>

			<h3 class="vocabularies-header"><%= LanguageUtil.get(pageContext, "vocabularies") %></h3>

					<div class="unstyled vocabulary-message"></div>

					<div class="unstyled vocabulary-list"></div>

			<div class="vocabularies-pagination"></div>
		</aui:col>

		<aui:col cssClass="vocabulary-categories-container" width="<%= 40 %>">
			<span class="select-vocabularies-container">
				<aui:input cssClass="select-categories" inline="<%= true %>" label="" name="checkAllCategories" title='<%= LanguageUtil.get(pageContext, "check-all-categories") %>' type="checkbox" />
			</span>

			<h3 class="categories-header"><%= LanguageUtil.get(pageContext, "categories") %></h3>

			<div class="vocabulary-categories"></div>
		</aui:col>

		<aui:col cssClass="hide vocabulary-edit-category" width="<%= 35 %>">
			<h3><%= LanguageUtil.get(pageContext, "category-details") %></h3>

			<aui:button cssClass="category-view-close close" icon="icon-remove" />

			<div class="category-view"></div>
		</aui:col>
	</aui:row>
</aui:form>

<aui:script use="liferay-category-admin">
	new Liferay.Portlet.AssetCategoryAdmin(
		{
			baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
			baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
			itemsPerPage: <%= SearchContainer.DEFAULT_DELTA %>,
			portletId: '<%= portletDisplay.getId() %>'
		}
	);
</aui:script>