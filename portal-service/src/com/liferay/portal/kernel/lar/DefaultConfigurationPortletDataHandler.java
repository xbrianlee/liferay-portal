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

import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Garcia
 */
public class DefaultConfigurationPortletDataHandler
	extends BasePortletDataHandler {

	public DefaultConfigurationPortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
	}

	@Override
	public PortletPreferences deleteData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return null;
	}

	@Override
	public String exportData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		return null;
	}

	@Override
	public long getExportModelCount(ManifestSummary manifestSummary) {
		return 0;
	}

	@Override
	public PortletPreferences importData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences, String data) {

		return null;
	}

}