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
public class IconCloseTag extends IconTag {

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		PortletDisplay portletDisplay =
			(PortletDisplay)pageContext.getAttribute("portletDisplay");

		if (!portletDisplay.isShowCloseIcon()) {
			return null;
		}

		setCssClass("portlet-close portlet-close-icon");
		setImage("../aui/remove");
		setMessage("remove");

		String onClick =
			"Liferay.Portlet.close('#p_p_id_".concat(
				portletDisplay.getId()).concat("_'); return false;");

		setOnClick(onClick);

		setToolTip(false);
		setUrl(portletDisplay.getURLClose());

		return super.getPage();
	}

	private static final String _PAGE =
		"/html/taglib/portlet/icon_close/page.jsp";

}