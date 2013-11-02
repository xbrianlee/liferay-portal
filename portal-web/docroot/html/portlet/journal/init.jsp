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

<%@ page import="com.liferay.portal.kernel.search.SearchResult" %><%@
page import="com.liferay.portal.kernel.xml.Document" %><%@
page import="com.liferay.portal.kernel.xml.Element" %><%@
page import="com.liferay.portal.kernel.xml.Node" %><%@
page import="com.liferay.portal.kernel.xml.SAXReaderUtil" %><%@
page import="com.liferay.portal.kernel.xml.XPath" %><%@
page import="com.liferay.portlet.asset.NoSuchEntryException" %><%@
page import="com.liferay.portlet.documentlibrary.util.JournalSearcher" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchTemplateException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMStructurePermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.storage.Fields" %><%@
page import="com.liferay.portlet.journal.ArticleContentException" %><%@
page import="com.liferay.portlet.journal.ArticleContentSizeException" %><%@
page import="com.liferay.portlet.journal.ArticleDisplayDateException" %><%@
page import="com.liferay.portlet.journal.ArticleExpirationDateException" %><%@
page import="com.liferay.portlet.journal.ArticleIdException" %><%@
page import="com.liferay.portlet.journal.ArticleSmallImageNameException" %><%@
page import="com.liferay.portlet.journal.ArticleSmallImageSizeException" %><%@
page import="com.liferay.portlet.journal.ArticleTitleException" %><%@
page import="com.liferay.portlet.journal.ArticleTypeException" %><%@
page import="com.liferay.portlet.journal.ArticleVersionException" %><%@
page import="com.liferay.portlet.journal.DuplicateArticleIdException" %><%@
page import="com.liferay.portlet.journal.DuplicateFeedIdException" %><%@
page import="com.liferay.portlet.journal.DuplicateFolderNameException" %><%@
page import="com.liferay.portlet.journal.FeedContentFieldException" %><%@
page import="com.liferay.portlet.journal.FeedIdException" %><%@
page import="com.liferay.portlet.journal.FeedNameException" %><%@
page import="com.liferay.portlet.journal.FeedTargetLayoutFriendlyUrlException" %><%@
page import="com.liferay.portlet.journal.FeedTargetPortletIdException" %><%@
page import="com.liferay.portlet.journal.FolderNameException" %><%@
page import="com.liferay.portlet.journal.NoSuchArticleException" %><%@
page import="com.liferay.portlet.journal.NoSuchFolderException" %><%@
page import="com.liferay.portlet.journal.model.JournalArticleResource" %><%@
page import="com.liferay.portlet.journal.model.JournalFeed" %><%@
page import="com.liferay.portlet.journal.model.JournalFeedConstants" %><%@
page import="com.liferay.portlet.journal.model.JournalFolder" %><%@
page import="com.liferay.portlet.journal.model.JournalFolderConstants" %><%@
page import="com.liferay.portlet.journal.model.JournalSearchConstants" %><%@
page import="com.liferay.portlet.journal.model.impl.JournalArticleImpl" %><%@
page import="com.liferay.portlet.journal.search.ArticleDisplayTerms" %><%@
page import="com.liferay.portlet.journal.search.EntriesChecker" %><%@
page import="com.liferay.portlet.journal.search.FeedDisplayTerms" %><%@
page import="com.liferay.portlet.journal.search.FeedSearch" %><%@
page import="com.liferay.portlet.journal.search.FeedSearchTerms" %><%@
page import="com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.JournalFeedLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.JournalFolderServiceUtil" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalArticlePermission" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalFeedPermission" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalFolderPermission" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalPermission" %><%@
page import="com.liferay.portlet.journal.util.JournalConverterUtil" %><%@
page import="com.liferay.portlet.journal.util.JournalUtil" %><%@
page import="com.liferay.util.RSSUtil" %>

<%
PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

String[] displayViews = StringUtil.split(PrefsParamUtil.getString(portletPreferences, liferayPortletRequest, "displayViews", StringUtil.merge(PropsValues.JOURNAL_DISPLAY_VIEWS)));

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/html/portlet/journal/init-ext.jsp" %>