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

<%
String forwardURL = null;

String forwardParam = PortalUtil.escapeRedirect(request.getParameter(WebKeys.FORWARD_URL));
String forwardRequest = (String)request.getAttribute(WebKeys.FORWARD_URL);
String forwardSession = (String)session.getAttribute(WebKeys.FORWARD_URL);

if (Validator.isNotNull(forwardParam)) {
	forwardURL = forwardParam;
}
else if (Validator.isNotNull(forwardRequest)) {
	forwardURL = forwardRequest;
}
else if (Validator.isNotNull(forwardSession)) {
	forwardURL = forwardSession;
}
else if (themeDisplay != null) {
	forwardURL = themeDisplay.getPathMain();
}
else {
	forwardURL = PortalUtil.getPathMain();
}

if (!CookieKeys.hasSessionId(request) && Validator.isNotNull(forwardURL)) {
	forwardURL = PortalUtil.getURLWithSessionId(forwardURL, session.getId());
}
%>