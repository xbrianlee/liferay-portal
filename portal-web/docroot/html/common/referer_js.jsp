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

<%@ include file="/html/common/referer_common.jsp" %>

<script type="text/javascript">

	<%
	Boolean logout = (Boolean)request.getAttribute(WebKeys.LOGOUT);

	if (logout == null) {
		logout = Boolean.FALSE;
	}
	%>

	<c:if test="<%= logout && BrowserSnifferUtil.isIe(request) && PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), PropsKeys.NTLM_AUTH_ENABLED, PropsValues.NTLM_AUTH_ENABLED) %>">
		document.execCommand("ClearAuthenticationCache");
	</c:if>

	location.href = '<%= HtmlUtil.escapeJS(referer) %>';
</script>