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

<%@ include file="/html/portlet/portlet_sharing/init.jsp" %>

<%
String netvibesURL = ParamUtil.getString(request, "netvibesURL");
String widgetURL = ParamUtil.getString(request, "widgetURL");
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(widgetURL) %>">
		<p>
			<liferay-ui:message key="share-this-application-on-any-website" />
		</p>

		<textarea class="lfr-textarea" onClick="Liferay.Util.selectAndCopy(this);">&lt;script src=&quot;<%= themeDisplay.getPortalURL() %><%= themeDisplay.getPathContext() %>/html/js/liferay/widget.js&quot; type=&quot;text/javascript&quot;&gt;&lt;/script&gt;
&lt;script type=&quot;text/javascript&quot;&gt;
Liferay.Widget({ url: &#x27;<%= HtmlUtil.escape(widgetURL) %>&#x27;});
&lt;/script&gt;</textarea>
	</c:when>
	<c:when test="<%= Validator.isNotNull(netvibesURL) %>">
		<p>
			<aui:a href="http://eco.netvibes.com/submit/widget" target="_blank"><liferay-ui:message key="add-this-application-to-netvibes" /></aui:a>
		</p>

		<liferay-ui:input-resource url="<%= netvibesURL %>" />
	</c:when>
</c:choose>