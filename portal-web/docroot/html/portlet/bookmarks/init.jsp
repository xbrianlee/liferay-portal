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

<%@ page import="com.liferay.portlet.bookmarks.EntryURLException" %><%@
page import="com.liferay.portlet.bookmarks.FolderNameException" %><%@
page import="com.liferay.portlet.bookmarks.NoSuchEntryException" %><%@
page import="com.liferay.portlet.bookmarks.NoSuchFolderException" %><%@
page import="com.liferay.portlet.bookmarks.model.BookmarksEntry" %><%@
page import="com.liferay.portlet.bookmarks.model.BookmarksFolder" %><%@
page import="com.liferay.portlet.bookmarks.model.BookmarksFolderConstants" %><%@
page import="com.liferay.portlet.bookmarks.service.BookmarksEntryServiceUtil" %><%@
page import="com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil" %><%@
page import="com.liferay.portlet.bookmarks.service.BookmarksFolderServiceUtil" %><%@
page import="com.liferay.portlet.bookmarks.service.permission.BookmarksEntryPermission" %><%@
page import="com.liferay.portlet.bookmarks.service.permission.BookmarksFolderPermission" %><%@
page import="com.liferay.portlet.bookmarks.util.BookmarksSearcher" %><%@
page import="com.liferay.portlet.bookmarks.util.BookmarksUtil" %>

<%
PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

String portletResource = ParamUtil.getString(request, "portletResource");

if (layout.isTypeControlPanel()) {
	portletPreferences = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), scopeGroupId, PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, PortletKeys.BOOKMARKS, null);
}

long rootFolderId = PrefsParamUtil.getLong(portletPreferences, request, "rootFolderId", BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
String rootFolderName = StringPool.BLANK;

if (rootFolderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
	try {
		BookmarksFolder rootFolder = BookmarksFolderServiceUtil.getFolder(rootFolderId);

		rootFolderName = rootFolder.getName();
	}
	catch (NoSuchFolderException nsfe) {
	}
}

boolean showFoldersSearch = PrefsParamUtil.getBoolean(portletPreferences, request, "showFoldersSearch", true);
boolean showSubfolders = PrefsParamUtil.getBoolean(portletPreferences, request, "showSubfolders", true);
int foldersPerPage = PrefsParamUtil.getInteger(portletPreferences, request, "foldersPerPage", SearchContainer.DEFAULT_DELTA);

String defaultFolderColumns = "folder,num-of-folders,num-of-entries";

String portletId = portletDisplay.getId();

if (portletId.equals(PortletKeys.PORTLET_CONFIGURATION)) {
	portletId = portletResource;
}

if (portletId.equals(PortletKeys.BOOKMARKS)) {
	defaultFolderColumns += ",action";
}

String allFolderColumns = defaultFolderColumns;

String[] folderColumns = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "folderColumns", defaultFolderColumns));

if (!portletId.equals(PortletKeys.BOOKMARKS)) {
	folderColumns = ArrayUtil.remove(folderColumns, "action");
}

boolean enableRelatedAssets = GetterUtil.getBoolean(portletPreferences.getValue("enableRelatedAssets", null), true);
int entriesPerPage = PrefsParamUtil.getInteger(portletPreferences, request, "entriesPerPage", SearchContainer.DEFAULT_DELTA);

String defaultEntryColumns = "name,url,visits,modified-date";

if (portletId.equals(PortletKeys.BOOKMARKS)) {
	defaultEntryColumns += ",action";
}

String allEntryColumns = defaultEntryColumns;

String[] entryColumns = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "entryColumns", defaultEntryColumns));

if (!portletId.equals(PortletKeys.BOOKMARKS)) {
	entryColumns = ArrayUtil.remove(entryColumns, "action");
}

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
%>

<%@ include file="/html/portlet/bookmarks/init-ext.jsp" %>