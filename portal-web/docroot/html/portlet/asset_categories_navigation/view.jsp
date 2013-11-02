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

<%@ include file="/html/portlet/asset_categories_navigation/init.jsp" %>

<%
long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<c:choose>
	<c:when test="<%= portletDisplayDDMTemplateId > 0 %>">

		<%
		List<AssetVocabulary> ddmTemplateAssetVocabularies = new ArrayList<AssetVocabulary>();

		if (allAssetVocabularies) {
			ddmTemplateAssetVocabularies = assetVocabularies;
		}
		else {
			for (long assetVocabularyId : assetVocabularyIds) {
				try {
					ddmTemplateAssetVocabularies.add(AssetVocabularyServiceUtil.getVocabulary(assetVocabularyId));
				}
				catch (NoSuchVocabularyException nsve) {
				}
			}
		}
		%>

		<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, ddmTemplateAssetVocabularies) %>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= allAssetVocabularies %>">
				<liferay-ui:asset-categories-navigation
					hidePortletWhenEmpty="<%= true %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-ui:asset-categories-navigation
					hidePortletWhenEmpty="<%= true %>"
					vocabularyIds="<%= assetVocabularyIds %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>