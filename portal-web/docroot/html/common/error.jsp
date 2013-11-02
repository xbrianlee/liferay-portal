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

<%@ include file="/html/common/init.jsp" %>

<%@ page isErrorPage="true" %>

<%
String userId = request.getRemoteUser();
String currentURL = PortalUtil.getCurrentURL(request);

if (exception instanceof PrincipalException) {
	_log.warn("User ID " + userId);
	_log.warn("Current URL " + currentURL);
	_log.warn("Referer " + request.getHeader("Referer"));
	_log.warn("Remote address " + request.getRemoteAddr());

	_log.warn(exception, exception);
}
else {
	_log.error("User ID " + userId);
	_log.error("Current URL " + currentURL);
	_log.error("Referer " + request.getHeader("Referer"));
	_log.error("Remote address " + request.getRemoteAddr());

	_log.error(exception, exception);
}

String message = exception.getMessage();
%>

<center>

<br />

<table border="0" cellpadding="0" cellspacing="0" width="95%">
<tr>
	<td>
		<font color="#FF0000" face="Verdana, Tahoma, Arial" size="2">

		<c:choose>
			<c:when test="<%= exception instanceof PrincipalException %>">
				<liferay-ui:message key="you-do-not-have-permission-to-view-this-page" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="an-unexpected-system-error-occurred" />
			</c:otherwise>
		</c:choose>

		<br />

		</font>

		<c:if test="<%= message != null %>">
			<br />

			<%= HtmlUtil.escape(message) %>
		</c:if>

	</td>
</tr>
</table>

<br />

</center>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.common.error_jsp");
%>