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

package com.liferay.portlet.mypages.action;

import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.portlet.sites.action.ActionUtil;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jorge Ferrer
 * @author Amos Fong
 */
public class ViewAction extends PortletAction {

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		if (renderRequest.getRemoteUser() == null) {
			return actionMapping.findForward("portlet.my_pages.view");
		}

		if (!renderRequest.getWindowState().equals(WindowState.MAXIMIZED)) {
			return actionMapping.findForward("portlet.my_pages.view");
		}

		User user = PortalUtil.getUser(renderRequest);

		RenderRequestImpl renderRequestImpl = (RenderRequestImpl)renderRequest;

		DynamicServletRequest dynamicRequest =
			(DynamicServletRequest)renderRequestImpl.getHttpServletRequest();

		dynamicRequest.setParameter(
			"p_u_i_d", String.valueOf(user.getUserId()));

		String tabs1 = "public-pages";

		boolean hasPowerUserRole = RoleLocalServiceUtil.hasUserRole(
			user.getUserId(), user.getCompanyId(), RoleConstants.POWER_USER,
			true);

		if (PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED &&
			!hasPowerUserRole) {

			tabs1 = "private-pages";
		}

		dynamicRequest.setParameter("tabs1", tabs1);

		Group group = user.getGroup();

		dynamicRequest.setParameter(
			"groupId", String.valueOf(group.getGroupId()));

		ActionUtil.getGroup(renderRequest);

		return actionMapping.findForward("portlet.my_pages.edit_layouts");
	}

}