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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Date;
import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class PortletDataContextFactoryUtil {

	public static PortletDataContext clonePortletDataContext(
		PortletDataContext portletDataContext) {

		return getPortletDataContextFactory().clonePortletDataContext(
			portletDataContext);
	}

	public static PortletDataContext createExportPortletDataContext(
			long companyId, long groupId, Map<String, String[]> parameterMap,
			Date startDate, Date endDate, ZipWriter zipWriter)
		throws PortletDataException {

		return getPortletDataContextFactory().createExportPortletDataContext(
			companyId, groupId, parameterMap, startDate, endDate, zipWriter);
	}

	public static PortletDataContext createImportPortletDataContext(
			long companyId, long groupId, Map<String, String[]> parameterMap,
			UserIdStrategy userIdStrategy, ZipReader zipReader)
		throws PortletDataException {

		return getPortletDataContextFactory().createImportPortletDataContext(
			companyId, groupId, parameterMap, userIdStrategy, zipReader);
	}

	public static PortletDataContext createPreparePortletDataContext(
			ThemeDisplay themeDisplay, Date startDate, Date endDate)
		throws PortletDataException {

		return getPortletDataContextFactory().createPreparePortletDataContext(
			themeDisplay, startDate, endDate);
	}

	public static PortletDataContextFactory getPortletDataContextFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletDataContextFactoryUtil.class);

		return _portletDataContextFactory;
	}

	public void setPortletDataContextFactory(
		PortletDataContextFactory portletDataContextFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletDataContextFactory = portletDataContextFactory;
	}

	private static PortletDataContextFactory _portletDataContextFactory;

}