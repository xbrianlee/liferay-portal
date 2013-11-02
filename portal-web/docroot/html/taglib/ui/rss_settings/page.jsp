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

<%@ include file="/html/taglib/ui/rss_settings/init.jsp" %>

<%
int delta = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:rss-settings:delta"));
String displayStyle = (String)request.getAttribute("liferay-ui:rss-settings:displayStyle");
String[] displayStyles = (String[])request.getAttribute("liferay-ui:rss-settings:displayStyles");
boolean enabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:rss-settings:enabled"));
String feedType = (String)request.getAttribute("liferay-ui:rss-settings:feedType");
String name = (String)request.getAttribute("liferay-ui:rss-settings:name");
boolean nameEnabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:rss-settings:nameEnabled"));
%>

<div class="taglib-rss-settings">
	<aui:fieldset>
		<aui:input label="enable-rss-subscription" name="preferences--enableRss--" type="checkbox" value="<%= enabled %>" />

		<div class="rss-settings-options" id="<portlet:namespace />rssOptions">
			<c:if test="<%= nameEnabled %>">
				<aui:input label="rss-feed-name" name="preferences--rssName--" type="text" value="<%= name %>" />
			</c:if>

			<aui:select label="maximum-items-to-display" name="preferences--rssDelta--">
				<aui:option label="1" selected="<%= delta == 1 %>" />
				<aui:option label="2" selected="<%= delta == 2 %>" />
				<aui:option label="3" selected="<%= delta == 3 %>" />
				<aui:option label="4" selected="<%= delta == 4 %>" />
				<aui:option label="5" selected="<%= delta == 5 %>" />
				<aui:option label="10" selected="<%= delta == 10 %>" />
				<aui:option label="15" selected="<%= delta == 15 %>" />
				<aui:option label="20" selected="<%= delta == 20 %>" />
				<aui:option label="25" selected="<%= delta == 25 %>" />
				<aui:option label="30" selected="<%= delta == 30 %>" />
				<aui:option label="40" selected="<%= delta == 40 %>" />
				<aui:option label="50" selected="<%= delta == 50 %>" />
				<aui:option label="60" selected="<%= delta == 60 %>" />
				<aui:option label="70" selected="<%= delta == 70 %>" />
				<aui:option label="80" selected="<%= delta == 80 %>" />
				<aui:option label="90" selected="<%= delta == 90 %>" />
				<aui:option label="100" selected="<%= delta == 100 %>" />
			</aui:select>

			<aui:select label="display-style" name="preferences--rssDisplayStyle--">

				<%
				for (String curDisplayStyle : displayStyles) {
				%>

					<aui:option label="<%= curDisplayStyle %>" selected="<%= displayStyle.equals(curDisplayStyle) %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select label="format" name="preferences--rssFeedType--">

				<%
				for (String type : RSSUtil.FEED_TYPES) {
				%>

					<aui:option label="<%= RSSUtil.getFeedTypeName(type) %>" selected="<%= feedType.equals(type) %>" value="<%= type %>" />

				<%
				}
				%>

			</aui:select>
		</div>
	</aui:fieldset>
</div>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />enableRssCheckbox','<portlet:namespace />rssOptions');
</aui:script>