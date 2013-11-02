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

<%
for (String servletContextName : ServletContextPool.keySet()) {
	ServletContext servletContext = ServletContextPool.get(servletContextName);

	if (Validator.isNull(servletContextName) || servletContextName.equals(PortalUtil.getPathContext())) {
		continue;
	}

	PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(servletContextName);

	List<Portlet> portlets = portletApp.getPortlets();

	for (Portlet portlet : portlets) {
		PortletConfig portletConfig = PortletConfigFactoryUtil.create(portlet, servletContext);

		String invokerPortletName = portletConfig.getInitParameter(InvokerPortlet.INIT_INVOKER_PORTLET_NAME);

		if (invokerPortletName == null) {
			invokerPortletName = portletConfig.getPortletName();
		}

		String portletName = PortalUtil.getJsSafePortletId(invokerPortletName);

		String path = StringPool.SLASH.concat(portletName).concat("/invoke");

		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

		request.setAttribute(WebKeys.EXTEND_SESSION, Boolean.TRUE);

		try {
			requestDispatcher.include(request, response);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to extend session for " + servletContextName);
			}
		}
	}
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portal.extend_session_jsp");
%>