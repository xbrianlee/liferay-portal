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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");
Long liveGroupId = (Long)request.getAttribute("site.liveGroupId");

String host = PortalUtil.getHost(request);

String sitemapUrl = PortalUtil.getPortalURL(host, request.getServerPort(), request.isSecure()) + themeDisplay.getPathContext() + "/sitemap.xml";

String publicSitemapUrl = sitemapUrl;

LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroupId, false);

if (!host.equals(publicLayoutSet.getVirtualHostname())) {
	publicSitemapUrl += "?groupId=" + liveGroup.getGroupId() + "&privateLayout=" + false;
}

String privateSitemapUrl = sitemapUrl;

LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroupId, true);

if (!host.equals(privateLayoutSet.getVirtualHostname())) {
	privateSitemapUrl += "?groupId=" + liveGroup.getGroupId() + "&privateLayout=" + true;
}
%>

<liferay-ui:error-marker key="errorSection" value="siteMap" />

<h3><liferay-ui:message key="sitemap" /></h3>

<liferay-util:buffer var="linkContent">
	<aui:a href="http://www.sitemaps.org" target="_blank">http://www.sitemaps.org</aui:a>
</liferay-util:buffer>

<liferay-ui:message key="the-sitemap-protocol-notifies-search-engines-of-the-structure-of-the-website" /> <liferay-ui:message arguments="<%= linkContent %>" key="see-x-for-more-information" />

<br /><br />

<aui:fieldset label="public-pages">
	<%= LanguageUtil.format(pageContext, "send-sitemap-information-to-preview", new Object[] {"<a target=\"_blank\" href=\"" + publicSitemapUrl + "\">", "</a>"}) %>

	<ul>
		<li>
			<aui:a href='<%= "http://www.google.com/webmasters/sitemaps/ping?sitemap=" + HtmlUtil.escapeURL(publicSitemapUrl) %>' target="_blank">Google</aui:a>
		</li>
		<li>
			<aui:a href='<%= "https://siteexplorer.search.yahoo.com/submit/ping?sitemap=" + HtmlUtil.escapeURL(publicSitemapUrl) %>' target="_blank">Yahoo!</aui:a> (<liferay-ui:message key="requires-login" />)
		</li>
	</ul>
</aui:fieldset>

<aui:fieldset label="private-pages">
	<%= LanguageUtil.format(pageContext, "send-sitemap-information-to-preview", new Object[] {"<a target=\"_blank\" href=\"" + privateSitemapUrl + "\">", "</a>"}) %>

	<ul>
		<li>
			<aui:a href='<%= "http://www.google.com/webmasters/sitemaps/ping?sitemap=" + HtmlUtil.escapeURL(privateSitemapUrl) %>' target="_blank">Google</aui:a>
		</li>
		<li>
			<aui:a href='<%= "https://siteexplorer.search.yahoo.com/submit/ping?sitemap=" + HtmlUtil.escapeURL(privateSitemapUrl) %>' target="_blank">Yahoo!</aui:a> (<liferay-ui:message key="requires-login" />)
		</li>
	</ul>
</aui:fieldset>