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

<%@ include file="/html/portlet/search/facets/init.jsp" %>

<%
String[] assetCategoryIdsOrNames = StringUtil.split(fieldParam);

long assetVocabularyId = dataJSONObject.getLong("assetVocabularyId");
boolean matchByName = dataJSONObject.getBoolean("matchByName");

List<AssetVocabulary> assetVocabularies = new ArrayList<AssetVocabulary>();

if (assetVocabularyId > 0) {
	AssetVocabulary assetVocabulary = AssetVocabularyServiceUtil.getVocabulary(assetVocabularyId);

	assetVocabularies.add(assetVocabulary);
}
else {
	assetVocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {themeDisplay.getScopeGroupId(), themeDisplay.getSiteGroupId()});
}

if (assetVocabularies.isEmpty()) {
	return;
}
%>

<div class="asset-vocabulary <%= cssClass %>" data-facetFieldName="<%= facet.getFieldId() %>" id="<%= randomNamespace %>facet">
	<aui:input name="<%= facet.getFieldId() %>" type="hidden" value="<%= StringUtil.merge(assetCategoryIdsOrNames) %>" />

	<%
	for (AssetVocabulary assetVocabulary : assetVocabularies) {
		List<AssetCategory> assetCategories = AssetCategoryServiceUtil.getVocabularyRootCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (assetCategories.isEmpty()) {
			continue;
		}
	%>

		<div class="search-asset-vocabulary-list-container">
			<ul class="nav nav-pills nav-stacked search-asset-vocabulary-list">

				<%
				StringBundler sb = new StringBundler();

				String clearFields = renderResponse.getNamespace() + facet.getFieldId();

				_buildCategoriesNavigation(assetCategoryIdsOrNames, matchByName, facetCollector, assetCategories, clearFields, pageContext, sb);
				%>

				<%= sb.toString() %>

			</ul>
		</div>

	<%
	}
	%>

</div>

<%!
private void _buildCategoriesNavigation(String[] assetCategoryIdsOrNames, boolean matchByName, FacetCollector facetCollector, List<AssetCategory> assetCategories, String clearFields, PageContext pageContext, StringBundler sb) throws Exception {
	for (AssetCategory assetCategory : assetCategories) {
		String term = String.valueOf(assetCategory.getCategoryId());

		String assetCategoryName = HtmlUtil.escape(assetCategory.getName());

		if (matchByName) {
			term = assetCategoryName;
		}

		int frequency = 0;

		TermCollector termCollector = facetCollector.getTermCollector(term);

		if (termCollector != null) {
			frequency = termCollector.getFrequency();
		}

		sb.append("<li class=\"facet-value");

		if (ArrayUtil.contains(assetCategoryIdsOrNames, term)) {
			sb.append(" active");

			ScriptTag.doTag(null, "liferay-token-list", "Liferay.Search.tokenList.add({clearFields: '" + clearFields + "', text: '" + HtmlUtil.escapeJS(assetCategoryName) + "'});", null, pageContext);
		}

		sb.append("\"><a href=\"#\" data-value=\"");
		sb.append(HtmlUtil.escapeAttribute(term));
		sb.append("\">");
		sb.append(assetCategoryName);
		sb.append("</a> <span class=\"frequency\">(");
		sb.append(frequency);
		sb.append(")</span>");

		List<AssetCategory> childAssetCategories = AssetCategoryServiceUtil.getChildCategories(assetCategory.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (!childAssetCategories.isEmpty()) {
			sb.append("<ul>");

			_buildCategoriesNavigation(assetCategoryIdsOrNames, matchByName, facetCollector, childAssetCategories, clearFields, pageContext, sb);

			sb.append("</ul>");
		}

		sb.append("</li>");
	}
}
%>