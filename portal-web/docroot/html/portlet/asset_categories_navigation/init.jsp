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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.asset.NoSuchVocabularyException" %>

<%
List<AssetVocabulary> assetVocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {scopeGroupId, themeDisplay.getCompanyGroupId()});

long[] availableAssetVocabularyIds = new long[assetVocabularies.size()];

for (int i = 0; i < assetVocabularies.size(); i++) {
	AssetVocabulary assetVocabulary = assetVocabularies.get(i);

	availableAssetVocabularyIds[i] = assetVocabulary.getVocabularyId();
}

boolean allAssetVocabularies = GetterUtil.getBoolean(portletPreferences.getValue("allAssetVocabularies", Boolean.TRUE.toString()));

long[] assetVocabularyIds = availableAssetVocabularyIds;

if (!allAssetVocabularies && (portletPreferences.getValues("assetVocabularyIds", null) != null)) {
	assetVocabularyIds = StringUtil.split(portletPreferences.getValue("assetVocabularyIds", null), 0L);
}

String displayStyle = portletPreferences.getValue("displayStyle", StringPool.BLANK);
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), themeDisplay.getScopeGroupId());
%>

<%@ include file="/html/portlet/asset_categories_navigation/init-ext.jsp" %>