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
String redirect = ParamUtil.getString(request, "redirect");

long categoryId = ParamUtil.getLong(request, "categoryId", ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
%>

<portlet:actionURL var="addBookItemsURL">
	<portlet:param name="struts_action" value="/shopping/add_book_items" />
</portlet:actionURL>

<aui:form action="<%= addBookItemsURL %>" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "saveBookItem();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="categoryId" type="hidden" value="<%= categoryId %>" />

	<liferay-util:include page="/html/portlet/shopping/tabs1.jsp">
		<liferay-util:param name="tabs1" value="categories" />
	</liferay-util:include>

	<liferay-ui:error exception="<%= AmazonException.class %>" />

	<div class="breadcrumbs">
		<%= ShoppingUtil.getBreadcrumbs(categoryId, pageContext, renderRequest, renderResponse) %>
	</div>

	<aui:fieldset>
		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" cssClass="lfr-textarea-container" label="add-all-isbn-numbers-separated-by-spaces" name="isbns" type="textarea" wrap="soft" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveBookItem() {
		alert('<%= UnicodeLanguageUtil.get(pageContext, "please-be-patient") %>');

		submitForm(this);
	}
</aui:script>