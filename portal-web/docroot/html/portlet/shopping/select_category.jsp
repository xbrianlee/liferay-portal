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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
ShoppingCategory category = (ShoppingCategory)request.getAttribute(WebKeys.SHOPPING_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
%>

<aui:form method="post" name="fm">
	<liferay-ui:header
		title="categories"
	/>

	<c:if test="<%= category != null %>">
		<div class="breadcrumbs">
			<%= ShoppingUtil.getBreadcrumbs(category, pageContext, renderRequest, renderResponse) %>
		</div>
	</c:if>

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/shopping/select_category");
	portletURL.setParameter("categoryId", String.valueOf(categoryId));

	List<String> headerNames = new ArrayList<String>();

	headerNames.add("category");
	headerNames.add("num-of-categories");
	headerNames.add("num-of-items");
	headerNames.add(StringPool.BLANK);

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

	int total = ShoppingCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId);

	searchContainer.setTotal(total);

	List results = ShoppingCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd());

	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		ShoppingCategory curCategory = (ShoppingCategory)results.get(i);

		curCategory = curCategory.toEscapedModel();

		ResultRow row = new ResultRow(curCategory, curCategory.getCategoryId(), i);

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/shopping/select_category");
		rowURL.setParameter("categoryId", String.valueOf(curCategory.getCategoryId()));

		// Name and description

		if (Validator.isNotNull(curCategory.getDescription())) {
			row.addText(curCategory.getName().concat("<br />").concat(curCategory.getDescription()), rowURL);
		}
		else {
			row.addText(curCategory.getName(), rowURL);
		}

		// Statistics

		List subcategoryIds = new ArrayList();

		subcategoryIds.add(new Long(curCategory.getCategoryId()));

		ShoppingCategoryServiceUtil.getSubcategoryIds(subcategoryIds, scopeGroupId, curCategory.getCategoryId());

		int categoriesCount = subcategoryIds.size() - 1;
		int itemsCount = ShoppingItemServiceUtil.getCategoriesItemsCount(scopeGroupId, subcategoryIds);

		row.addText(String.valueOf(categoriesCount), rowURL);
		row.addText(String.valueOf(itemsCount), rowURL);

		// Action

		StringBundler sb = new StringBundler(7);

		sb.append("opener.");
		sb.append(renderResponse.getNamespace());
		sb.append("selectCategory('");
		sb.append(curCategory.getCategoryId());
		sb.append("', '");
		sb.append(UnicodeFormatter.toString(curCategory.getName()));
		sb.append("'); window.close();");

		row.addButton("right", SearchEntry.DEFAULT_VALIGN, LanguageUtil.get(pageContext, "choose"), sb.toString());

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>