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

package com.liferay.portal.servlet;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alberto Montero
 * @author Julio Camarero
 */
public class NetvibesServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			String content = getContent(request);

			if (content == null) {
				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), request, response);
			}
			else {
				request.setAttribute(WebKeys.NETVIBES, Boolean.TRUE);

				response.setContentType(ContentTypes.TEXT_HTML);

				ServletResponseUtil.write(response, content);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}
	}

	protected String getContent(HttpServletRequest request) throws Exception {
		String path = GetterUtil.getString(request.getPathInfo());

		if (Validator.isNull(path)) {
			return null;
		}

		int pos = path.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		long companyId = PortalUtil.getCompanyId(request);

		String portletId = path.substring(
			pos + Portal.FRIENDLY_URL_SEPARATOR.length());

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			companyId, portletId);

		String title = HtmlUtil.escape(portlet.getDisplayName());

		String portalURL = PortalUtil.getPortalURL(request);

		String iconURL =
			portalURL + PortalUtil.getPathContext() + portlet.getIcon();

		String widgetJsURL =
			portalURL + PortalUtil.getPathContext() +
				"/html/js/liferay/widget.js";

		String widgetURL = request.getRequestURL().toString();

		widgetURL = widgetURL.replaceFirst(
			PropsValues.NETVIBES_SERVLET_MAPPING,
			PropsValues.WIDGET_SERVLET_MAPPING);
		widgetURL = HtmlUtil.escapeJS(widgetURL);

		StringBundler sb = new StringBundler(31);

		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link href=\"");
		sb.append(_NETVIBES_CSS);
		sb.append("\" rel=\"stylesheet\" ");
		sb.append("type=\"text/css\" />");
		sb.append("<script src=\"");
		sb.append(_NETVIBES_JS);
		sb.append("\" ");
		sb.append("type=\"text/javascript\"></script>");
		sb.append("<title>");
		sb.append(title);
		sb.append("</title>");
		sb.append("<link href=\"");
		sb.append(iconURL);
		sb.append("\" rel=\"icon\" ");
		sb.append("type=\"image/png\" />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<script src=\"");
		sb.append(widgetJsURL);
		sb.append("\" ");
		sb.append("type=\"text/javascript\"></script>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("Liferay.Widget({url:\"");
		sb.append(widgetURL);
		sb.append("\"});");
		sb.append("</script>");
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();
	}

	private static final String _NETVIBES_CSS =
		"http://www.netvibes.com/themes/uwa/style.css";

	private static final String _NETVIBES_JS =
		"http://www.netvibes.com/js/UWA/load.js.php?env=Standalone";

	private static Log _log = LogFactoryUtil.getLog(NetvibesServlet.class);

}