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

<%@ page session="false" %>

<%@ include file="/html/common/init.jsp" %>

<%
String referer = null;

String refererParam = PortalUtil.escapeRedirect(request.getParameter(WebKeys.REFERER));
String refererRequest = (String)request.getAttribute(WebKeys.REFERER);

String refererSession = null;

HttpSession session = request.getSession(false);

if (session != null) {
	refererSession = (String)session.getAttribute(WebKeys.REFERER);
}

if (Validator.isNotNull(refererParam)) {
	referer = refererParam;
}
else if (Validator.isNotNull(refererRequest)) {
	referer = refererRequest;
}
else if (Validator.isNotNull(refererSession)) {
	referer = refererSession;
}
else if (themeDisplay != null) {
	referer = themeDisplay.getPathMain();
}
else {
	referer = PortalUtil.getPathMain();
}

if ((session != null) && !CookieKeys.hasSessionId(request) && Validator.isNotNull(referer)) {
	referer = PortalUtil.getURLWithSessionId(referer, session.getId());
}
%>