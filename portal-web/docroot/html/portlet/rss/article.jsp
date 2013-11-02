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

<%@ include file="/html/portlet/rss/init.jsp" %>

<%
String url = ParamUtil.getString(request, "url");
int index = ParamUtil.getInteger(request, "index");

SyndFeed feed = null;

try {
	ObjectValuePair ovp = RSSUtil.getFeed(url);

	feed = (SyndFeed)ovp.getValue();
}
catch (Exception e) {
}
%>

<c:if test="<%= (url != null) && (feed != null) %>">
	<div style="padding: 10px 10px 10px 10px;">

		<%
		List entries = feed.getEntries();

		if (index < entries.size()) {
			SyndEntry entry = (SyndEntry)entries.get(index);

			SyndContent description = entry.getDescription();

			String contentString = description.getValue();

			SyndContent content = null;

			try {
				content = (SyndContent)entry.getContents().get(0);

				if (Validator.isNotNull(content.getValue().trim())) {
					contentString = content.getValue();
				}
			}
			catch (Throwable t) {
			}
		%>

			<aui:a cssClass="font-large" href="<%= entry.getLink() %>" style="font-weight: bold;" target="_blank"><%= entry.getTitle() %></aui:a><br />

			<c:if test="<%= entry.getPublishedDate() != null %>">
				<%= dateFormatDateTime.format(entry.getPublishedDate()) %><br />
			</c:if>

			<div class="font-small">
				<%= contentString %>
			</div>

		<%
		}
		%>

	</div>
</c:if>