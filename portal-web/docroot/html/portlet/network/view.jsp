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

<%@ include file="/html/portlet/network/init.jsp" %>

<%
String cmd = ParamUtil.getString(request, Constants.CMD);

String tabs1 = ParamUtil.getString(request, "tabs1", "dns-lookup");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/network/view");
portletURL.setParameter("tabs1", tabs1);
%>

<form action="<portlet:renderURL><portlet:param name="struts_action" value="/network/view" /></portlet:renderURL>" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.SEARCH %>" />
<input name="<portlet:namespace />tabs1" type="hidden" value="<%= HtmlUtil.escapeAttribute(tabs1) %>" />

<liferay-ui:tabs
	names="dns-lookup,whois"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("dns-lookup") %>'>

		<%
		String domain = ParamUtil.getString(request, "domain");

		DNSLookup dnsLookup = null;

		if (cmd.equals(Constants.SEARCH)) {
			dnsLookup = NetworkUtil.getDNSLookup(domain);

			if (dnsLookup == null) {
				SessionErrors.add(renderRequest, DNSLookup.class.getName());
			}
		}
		%>

		<liferay-ui:error exception="<%= DNSLookup.class %>" message="please-enter-a-valid-host-name-or-ip" />

		<input name="<portlet:namespace />domain" size="30" type="text" value="<%= HtmlUtil.escape(domain) %>" /> <input type="submit" value="<liferay-ui:message key="search" />" />

		<c:if test="<%= dnsLookup != null %>">
<pre>
<%= dnsLookup.getResults() %>
</pre>
		</c:if>
	</c:when>
	<c:when test='<%= tabs1.equals("whois") %>'>

		<%
		String domain = ParamUtil.getString(request, "domain");

		Whois whois = null;

		if (cmd.equals(Constants.SEARCH)) {
			whois = NetworkUtil.getWhois(domain);

			if (whois == null) {
				SessionErrors.add(renderRequest, Whois.class.getName());
			}
		}
		%>

		<liferay-ui:error exception="<%= Whois.class %>" message="an-unexpected-error-occurred" />

		<input name="<portlet:namespace />domain" size="30" type="text" value="<%= HtmlUtil.escape(domain) %>" /> <input type="submit" value="<liferay-ui:message key="search" />" />

		<c:if test="<%= whois != null %>">
<pre>
<%= whois.getResults() %>
</pre>
		</c:if>
	</c:when>
</c:choose>

</form>

<c:if test='<%= windowState.equals(WindowState.MAXIMIZED) && (tabs1.equals("dns-lookup") || tabs1.equals("whois")) %>'>
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />domain);
	</aui:script>
</c:if>