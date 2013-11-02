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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.taglib.ui.IconTag;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconConfigurationTag extends IconTag {

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		PortletDisplay portletDisplay =
			(PortletDisplay)pageContext.getAttribute("portletDisplay");

		if (!portletDisplay.isShowConfigurationIcon()) {
			return null;
		}

		setCssClass("portlet-configuration portlet-configuration-icon");
		setImage("../aui/wrench");
		setMessage("configuration");
		setMethod("get");

		StringBundler sb = new StringBundler(11);

		sb.append("Liferay.Portlet.openWindow('#p_p_id_");
		sb.append(portletDisplay.getId());
		sb.append("_', '");
		sb.append(portletDisplay.getId());
		sb.append("', '");
		sb.append(portletDisplay.getURLConfiguration());
		sb.append("', '");
		sb.append(portletDisplay.getNamespace());
		sb.append("', '");
		sb.append(LanguageUtil.get(pageContext, "configuration"));
		sb.append("'); return false;");

		setOnClick(sb.toString());

		setToolTip(false);
		setUrl(portletDisplay.getURLConfiguration());

		return super.getPage();
	}

	private static final String _PAGE =
		"/html/taglib/portlet/icon_configuration/page.jsp";

}