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

package com.liferay.portlet.layoutsetprototypes.action;

import com.liferay.portal.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Garcia
 */
public class ActionUtil {

	public static void getLayoutSetPrototype(HttpServletRequest request)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = null;

		try {
			long layoutSetPrototypeId = ParamUtil.getLong(
				request, "layoutSetPrototypeId");

			if (Validator.isNotNull(layoutSetPrototypeId)) {
				layoutSetPrototype =
					LayoutSetPrototypeServiceUtil.getLayoutSetPrototype(
						layoutSetPrototypeId);
			}
			else {
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
					WebKeys.THEME_DISPLAY);

				Group group = themeDisplay.getScopeGroup();

				if (group.isLayoutSetPrototype()) {
					layoutSetPrototype =
						LayoutSetPrototypeLocalServiceUtil.
							getLayoutSetPrototype(group.getClassPK());
				}
			}
		}
		catch (NoSuchLayoutSetPrototypeException nslspe) {
		}

		request.setAttribute(WebKeys.LAYOUT_PROTOTYPE, layoutSetPrototype);
	}

	public static void getLayoutSetPrototype(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getLayoutSetPrototype(request);
	}

}