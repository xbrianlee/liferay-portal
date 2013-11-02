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

<%@ include file="/html/portlet/init.jsp" %>

<iframe allowTransparency="true" frameborder="0" id="<portlet:namespace />iframe" scrolling="no" src="<%= themeDisplay.getPathMain() %>/portal/license?p_p_state=pop_up" style="border: none; width: 100%;"></iframe>

<aui:script use="aui-autosize-iframe">
	var iframe = A.one('#<portlet:namespace />iframe');

	if (iframe) {
		iframe.plug(A.Plugin.AutosizeIframe);
	}
</aui:script>