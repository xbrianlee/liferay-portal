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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<%
String defaultHeight = GetterUtil.getString(portletConfig.getInitParameter("wai.connector.iframe.height.default"), "500");

Map<String, String[]> parameterMap = new HashMap<String, String[]>(renderRequest.getParameterMap());

String appURL = ParamUtil.getString(request, "appURL", PortalUtil.getPathContext(renderRequest));

parameterMap.remove("appURL");

appURL = appURL.concat(HttpUtil.parameterMapToString(parameterMap));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("appURL", StringPool.BLANK);
%>

<div id="<portlet:namespace />iframeDiv">
	<iframe frameborder="0" height="<%= defaultHeight %>" id="<portlet:namespace />iframe" src="<%= appURL %>" width="100%"></iframe>
</div>

<div id="<portlet:namespace />bookmarkDiv">
	<a href="#">Permanent Link</a>
</div>

<script type="text/javascript">
	AUI().use(
		'aui-base',
		function(A) {
			var iframe = A.one('#<portlet:namespace />iframe');

			var getURL = function() {
				var location = iframe.get('contentWindow.document.location');

				if (location) {
					return location.pathname + location.search;
				}

				return null;
			};

			var bookmarkLink = A.one('#<portlet:namespace />bookmarkDiv a');

			var getHeight = function() {
				var body = iframe.get('contentWindow.document.body');

				if (body) {
					var max = 0;

					// The scrollHeight of the body does not always account for every
					// element. One solution is to manually check the position of the
					// bottom edge of every div.

					body.all('div').each(function(div) {
						var height = div.getY() + div.get('scrollHeight');

						if (height > max) {
							max = height;
						}
					});

					var scrollHeight = body.get('scrollHeight');

					if (scrollHeight > max) {
						return scrollHeight;
					}
					else {
						return max;
					}
				}
				else {
					return <%= defaultHeight %>;
				}
			}

			var resizeIframe = function() {
				iframe.set('height', getHeight());
			};

			var updateIframe = function() {
				bookmarkLink.set('href', '<%= portletURL.toString() %>' + escape(getURL()));

				resizeIframe();
			};

			iframe.on('load', updateIframe);

			updateIframe();
		}
	);
</script>