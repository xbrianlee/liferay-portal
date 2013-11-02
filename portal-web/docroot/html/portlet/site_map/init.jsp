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

<%
String rootLayoutUuid = GetterUtil.getString(portletPreferences.getValue("rootLayoutUuid", StringPool.BLANK));
int displayDepth = GetterUtil.getInteger(portletPreferences.getValue("displayDepth", StringPool.BLANK));
String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), themeDisplay.getScopeGroupId());
boolean includeRootInTree = GetterUtil.getBoolean(portletPreferences.getValue("includeRootInTree", StringPool.BLANK));
boolean showCurrentPage = GetterUtil.getBoolean(portletPreferences.getValue("showCurrentPage", StringPool.BLANK));
boolean useHtmlTitle = GetterUtil.getBoolean(portletPreferences.getValue("useHtmlTitle", StringPool.BLANK));
boolean showHiddenPages = GetterUtil.getBoolean(portletPreferences.getValue("showHiddenPages", StringPool.BLANK));

Layout rootLayout = null;

long rootLayoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

if (Validator.isNotNull(rootLayoutUuid)) {
	rootLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(rootLayoutUuid, scopeGroupId, layout.isPrivateLayout());

	if (rootLayout != null) {
		rootLayoutId = rootLayout.getLayoutId();
	}
}
else {
	includeRootInTree = false;
}

if (rootLayoutId == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
	includeRootInTree = false;
}
%>

<%@ include file="/html/portlet/site_map/init-ext.jsp" %>