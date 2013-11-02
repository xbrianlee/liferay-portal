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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;

/**
 * @author Mate Thurzo
 */
public class ThemeImporter {

	public void importTheme(
			PortletDataContext portletDataContext, LayoutSet layoutSet)
		throws Exception {

		boolean importThemeSettings = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.THEME_REFERENCE);

		if (_log.isDebugEnabled()) {
			_log.debug("Import theme settings " + importThemeSettings);
		}

		if (!importThemeSettings) {
			return;
		}

		Element importDataRootElement =
			portletDataContext.getImportDataRootElement();
		Element headerElement = importDataRootElement.element("header");

		String themeId = layoutSet.getThemeId();
		String colorSchemeId = layoutSet.getColorSchemeId();

		Attribute themeIdAttribute = headerElement.attribute("theme-id");

		if (themeIdAttribute != null) {
			themeId = themeIdAttribute.getValue();
		}

		Attribute colorSchemeIdAttribute = headerElement.attribute(
			"color-scheme-id");

		if (colorSchemeIdAttribute != null) {
			colorSchemeId = colorSchemeIdAttribute.getValue();
		}

		String css = GetterUtil.getString(headerElement.elementText("css"));

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			layoutSet.getGroupId(), layoutSet.isPrivateLayout(), themeId,
			colorSchemeId, css, false);
	}

	private static Log _log = LogFactoryUtil.getLog(ThemeImporter.class);

}