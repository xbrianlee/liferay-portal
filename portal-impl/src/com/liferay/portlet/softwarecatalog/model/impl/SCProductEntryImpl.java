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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.service.SCLicenseLocalServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductScreenshotLocalServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SCProductEntryImpl extends SCProductEntryBaseImpl {

	public SCProductEntryImpl() {
	}

	@Override
	public SCProductVersion getLatestVersion() throws SystemException {
		List<SCProductVersion> results =
			SCProductVersionLocalServiceUtil.getProductVersions(
				getProductEntryId(), 0, 1);

		SCProductVersion lastVersion = null;

		if (results.size() > 0) {
			lastVersion = results.get(0);
		}

		return lastVersion;
	}

	@Override
	public List<SCLicense> getLicenses() throws SystemException {
		return SCLicenseLocalServiceUtil.getProductEntryLicenses(
			getProductEntryId());
	}

	@Override
	public List<SCProductScreenshot> getScreenshots() throws SystemException {
		return SCProductScreenshotLocalServiceUtil.getProductScreenshots(
			getProductEntryId());
	}

}