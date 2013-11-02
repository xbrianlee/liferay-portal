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

<%@ page import="com.liferay.portal.kernel.sanitizer.Sanitizer" %><%@
page import="com.liferay.portal.kernel.sanitizer.SanitizerException" %><%@
page import="com.liferay.portal.kernel.sanitizer.SanitizerUtil" %><%@
page import="com.liferay.portlet.rss.util.RSSUtil" %>

<%@ page import="com.sun.syndication.feed.synd.SyndContent" %><%@
page import="com.sun.syndication.feed.synd.SyndEnclosure" %><%@
page import="com.sun.syndication.feed.synd.SyndEntry" %><%@
page import="com.sun.syndication.feed.synd.SyndFeed" %><%@
page import="com.sun.syndication.feed.synd.SyndImage" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

String[] urls = portletPreferences.getValues("urls", new String[0]);
String[] titles = portletPreferences.getValues("titles", new String[0]);
int entriesPerFeed = GetterUtil.getInteger(portletPreferences.getValue("entriesPerFeed", "8"));
int expandedEntriesPerFeed = GetterUtil.getInteger(portletPreferences.getValue("expandedEntriesPerFeed", "1"));
boolean showFeedTitle = GetterUtil.getBoolean(portletPreferences.getValue("showFeedTitle", Boolean.TRUE.toString()));
boolean showFeedPublishedDate = GetterUtil.getBoolean(portletPreferences.getValue("showFeedPublishedDate", Boolean.TRUE.toString()));
boolean showFeedDescription = GetterUtil.getBoolean(portletPreferences.getValue("showFeedDescription", Boolean.TRUE.toString()));
boolean showFeedImage = GetterUtil.getBoolean(portletPreferences.getValue("showFeedImage", Boolean.TRUE.toString()));
String feedImageAlignment = portletPreferences.getValue("feedImageAlignment", "right");
boolean showFeedItemAuthor = GetterUtil.getBoolean(portletPreferences.getValue("showFeedItemAuthor", Boolean.TRUE.toString()));

String[] headerArticleValues = portletPreferences.getValues("headerArticleValues", new String[] {"0", ""});

long headerArticleGroupId = GetterUtil.getLong(headerArticleValues[0]);
String headerArticleId = headerArticleValues[1];

String[] footerArticleValues = portletPreferences.getValues("footerArticleValues", new String[] {"0", ""});

long footerArticleGroupId = GetterUtil.getLong(footerArticleValues[0]);
String footerArticleId = footerArticleValues[1];

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/html/portlet/rss/init-ext.jsp" %>