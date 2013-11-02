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

package com.liferay.portal.action;

import com.liferay.portal.kernel.portlet.PortletContainerUtil;
import com.liferay.portal.kernel.portlet.WindowStateFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class RenderPortletAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		themeDisplay.setAjax(true);

		String ajaxId = request.getParameter("ajax_id");

		long companyId = PortalUtil.getCompanyId(request);
		User user = PortalUtil.getUser(request);
		Layout layout = (Layout)request.getAttribute(WebKeys.LAYOUT);
		String portletId = ParamUtil.getString(request, "p_p_id");

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			companyId, portletId);

		String columnId = ParamUtil.getString(request, "p_p_col_id");
		int columnPos = ParamUtil.getInteger(request, "p_p_col_pos");
		int columnCount = ParamUtil.getInteger(request, "p_p_col_count");
		boolean staticPortlet = ParamUtil.getBoolean(request, "p_p_static");
		boolean staticStartPortlet = ParamUtil.getBoolean(
			request, "p_p_static_start");

		if (staticPortlet) {
			portlet = (Portlet)portlet.clone();

			portlet.setStatic(true);
			portlet.setStaticStart(staticStartPortlet);
		}

		if (ajaxId != null) {
			response.setHeader("Ajax-ID", ajaxId);
		}

		WindowState windowState = WindowStateFactory.getWindowState(
			ParamUtil.getString(request, "p_p_state"));

		PortalUtil.updateWindowState(
			portletId, user, layout, windowState, request);

		request = PortletContainerUtil.setupOptionalRenderParameters(
			request, null, columnId, columnPos, columnCount);

		PortletContainerUtil.render(request, response, portlet);

		return null;
	}

}