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

<%@ include file="/html/portal/init.jsp" %>

<%@ include file="/html/portal/layout/view/embedded_js.jspf" %>

<div id="iframe">

	<%
	UnicodeProperties typeSettingsProperties = layout.getTypeSettingsProperties();
	%>

	<iframe frameborder="0" id="embeddedIframe" src="<%= HtmlUtil.escapeHREF(typeSettingsProperties.getProperty("url")) %>" width="100%"></iframe>
</div>

<%@ include file="/html/portal/layout/view/common.jspf" %>