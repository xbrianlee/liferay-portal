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

<%@ page import="com.liferay.portlet.journal.NoSuchArticleException" %><%@
page import="com.liferay.portlet.journal.util.JournalUtil" %>

<%
long groupId = GetterUtil.getLong(portletPreferences.getValue("groupId", String.valueOf(themeDisplay.getScopeGroupId())));
String ddmStructureKey = portletPreferences.getValue("ddmStructureKey", StringPool.BLANK);
String type = portletPreferences.getValue("type", StringPool.BLANK);
String pageUrl = portletPreferences.getValue("pageUrl", "maximized");
int pageDelta = GetterUtil.getInteger(portletPreferences.getValue("pageDelta", StringPool.BLANK));
String orderByCol = portletPreferences.getValue("orderByCol", StringPool.BLANK);
String orderByType = portletPreferences.getValue("orderByType", StringPool.BLANK);

OrderByComparator orderByComparator = JournalUtil.getArticleOrderByComparator(orderByCol, orderByType);

DDMStructure ddmStructure = null;

if (Validator.isNotNull(ddmStructureKey)) {
	ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(groupId, PortalUtil.getClassNameId(JournalArticle.class), ddmStructureKey);
}

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/html/portlet/journal_articles/init-ext.jsp" %>