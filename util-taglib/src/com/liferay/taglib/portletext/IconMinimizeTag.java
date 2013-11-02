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

package com.liferay.taglib.portletext;

import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.taglib.ui.IconTag;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconMinimizeTag extends IconTag {

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		PortletDisplay portletDisplay =
			(PortletDisplay)pageContext.getAttribute("portletDisplay");

		if (!portletDisplay.isShowMinIcon()) {
			return null;
		}

		setCssClass("portlet-minimize portlet-minimize-icon");

		String image = null;
		String message = null;

		if (portletDisplay.isStateMin()) {
			image = "resize-vertical";
			message = "restore";
		}
		else {
			image = "minus";
			message = "minimize";
		}

		setImage("../aui/".concat(image));
		setMessage(message);

		String onClick =
			"Liferay.Portlet.minimize('#p_p_id_".concat(
				portletDisplay.getId()).concat("_', this); return false;");

		setOnClick(onClick);

		setToolTip(false);
		setUrl(portletDisplay.getURLMin());

		return super.getPage();
	}

	private static final String _PAGE =
		"/html/taglib/portlet/icon_minimize/page.jsp";

}