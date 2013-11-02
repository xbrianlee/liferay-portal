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

<%@ include file="/html/common/themes/init.jsp" %>

<%
StringBundler sb = (StringBundler)request.getAttribute(WebKeys.LAYOUT_CONTENT);

if ((sb != null) && (themeDisplay.isFacebook() || themeDisplay.isStateExclusive())) {
	sb.writeTo(out);
}
else {
	ComponentContext componentContext = (ComponentContext)request.getAttribute(ComponentConstants.COMPONENT_CONTEXT);

	boolean tilesPopUp = false;

	if (componentContext != null) {
		tilesPopUp = GetterUtil.getBoolean(componentContext.getAttribute("pop_up"));
	}

	if (tilesPopUp || themeDisplay.isStatePopUp() || themeDisplay.isWidget()) {
%>

		<liferay-theme:include page="portal_pop_up.jsp" />

<%
	}
	else {
%>

		<liferay-theme:include page="portal_normal.jsp" />

<%
	}
}

request.removeAttribute(WebKeys.LAYOUT_CONTENT);

PortalMessages.clear(request);
SessionMessages.clear(request);
SessionErrors.clear(request);
%>