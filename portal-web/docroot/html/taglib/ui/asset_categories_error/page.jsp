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

<%@ include file="/html/taglib/ui/asset_categories_error/init.jsp" %>

<liferay-ui:error exception="<%= AssetCategoryException.class %>">

	<%
	AssetCategoryException ace = (AssetCategoryException)errorException;

	AssetVocabulary vocabulary = ace.getVocabulary();

	String vocabularyTitle = StringPool.BLANK;

	if (vocabulary != null) {
		vocabularyTitle = vocabulary.getTitle(locale);
	}
	%>

	<c:choose>
		<c:when test="<%= ace.getType() == AssetCategoryException.AT_LEAST_ONE_CATEGORY %>">
			<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "please-select-at-least-one-category-for-x", vocabularyTitle, false) %>' />
		</c:when>
		<c:when test="<%= ace.getType() == AssetCategoryException.TOO_MANY_CATEGORIES %>">
			<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "you-cannot-select-more-than-one-category-for-x", vocabularyTitle, false) %>' />
		</c:when>
	</c:choose>
</liferay-ui:error>