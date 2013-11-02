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

package com.liferay.portlet.layoutprototypes.action;

import com.liferay.portal.NoSuchLayoutPrototypeException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.service.LayoutPrototypeServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge Ferrer
 */
public class ActionUtil {

	public static void getLayoutPrototype(HttpServletRequest request)
		throws Exception {

		long layoutPrototypeId = ParamUtil.getLong(
			request, "layoutPrototypeId");

		LayoutPrototype layoutPrototype = null;

		try {
			layoutPrototype = LayoutPrototypeServiceUtil.getLayoutPrototype(
				layoutPrototypeId);
		}
		catch (NoSuchLayoutPrototypeException nslpe) {
		}

		request.setAttribute(WebKeys.LAYOUT_PROTOTYPE, layoutPrototype);
	}

	public static void getLayoutPrototype(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getLayoutPrototype(request);
	}

}