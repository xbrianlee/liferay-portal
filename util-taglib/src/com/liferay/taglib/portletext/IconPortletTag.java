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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.taglib.ui.IconTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconPortletTag extends IconTag {

	public void setPortlet(Portlet portlet) {
		_portlet = portlet;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_portlet = null;
	}

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)pageContext.getAttribute(
			"themeDisplay");

		String message = null;
		String src = null;

		if (_portlet != null) {
			message = PortalUtil.getPortletTitle(
				_portlet, pageContext.getServletContext(),
				themeDisplay.getLocale());
			src = _portlet.getStaticResourcePath().concat(_portlet.getIcon());
		}
		else {
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			if (!portletDisplay.isShowPortletIcon()) {
				return null;
			}

			message = portletDisplay.getTitle();
			src = portletDisplay.getURLPortlet();
		}

		setAlt(StringPool.BLANK);
		setMessage(message);
		setSrc(src);

		return super.getPage();
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		request.setAttribute("liferay-portlet:icon_portlet:portlet", _portlet);
	}

	private static final String _PAGE =
		"/html/taglib/portlet/icon_portlet/page.jsp";

	private Portlet _portlet;

}