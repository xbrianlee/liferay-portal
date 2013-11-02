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
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portlet.layoutsadmin.lar.StagedTheme;

/**
 * @author Mate Thurzo
 */
public class ThemeExporter {

	public void exportTheme(
			PortletDataContext portletDataContext, LayoutSet layoutSet)
		throws Exception {

		boolean exportThemeSettings = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.THEME_REFERENCE);

		if (_log.isDebugEnabled()) {
			_log.debug("Export theme settings " + exportThemeSettings);
		}

		if (!exportThemeSettings) {
			return;
		}

		StagedTheme stagedTheme = new StagedTheme(layoutSet.getTheme());

		if (!portletDataContext.isPerformDirectBinaryImport()) {
			Element layoutSetElement = portletDataContext.getExportDataElement(
				layoutSet);

			portletDataContext.addReferenceElement(
				layoutSet, layoutSetElement, stagedTheme,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}

		exportThemeSettings(
			portletDataContext, stagedTheme.getThemeId(),
			layoutSet.getColorSchemeId(), layoutSet.getCss());
	}

	public void exportTheme(
			PortletDataContext portletDataContext,
			LayoutSetBranch layoutSetBranch)
		throws Exception {

		boolean exportThemeSettings = MapUtil.getBoolean(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.THEME_REFERENCE);

		if (_log.isDebugEnabled()) {
			_log.debug("Export theme settings " + exportThemeSettings);
		}

		if (!exportThemeSettings) {
			return;
		}

		StagedTheme stagedTheme = new StagedTheme(layoutSetBranch.getTheme());

		if (!portletDataContext.isPerformDirectBinaryImport()) {
			Element layoutSetBranchElement =
				portletDataContext.getExportDataElement(layoutSetBranch);

			portletDataContext.addReferenceElement(
				layoutSetBranch, layoutSetBranchElement, stagedTheme,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}

		exportThemeSettings(
			portletDataContext, stagedTheme.getThemeId(),
			layoutSetBranch.getColorSchemeId(), layoutSetBranch.getCss());
	}

	protected void exportThemeSettings(
			PortletDataContext portletDataContext, String themeId,
			String colorSchemeId, String css)
		throws Exception {

		Element exportDataRootElement =
			portletDataContext.getExportDataRootElement();

		Element headerElement = exportDataRootElement.element("header");

		headerElement.addAttribute("theme-id", themeId);
		headerElement.addAttribute("color-scheme-id", colorSchemeId);

		Element cssElement = headerElement.addElement("css");

		cssElement.addCDATA(css);
	}

	private static Log _log = LogFactoryUtil.getLog(ThemeExporter.class);

}