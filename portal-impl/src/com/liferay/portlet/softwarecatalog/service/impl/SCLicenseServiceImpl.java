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

package com.liferay.portlet.softwarecatalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.permission.PortalPermissionUtil;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.service.base.SCLicenseServiceBaseImpl;
import com.liferay.portlet.softwarecatalog.service.permission.SCLicensePermission;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCLicenseServiceImpl extends SCLicenseServiceBaseImpl {

	@Override
	public SCLicense addLicense(
			String name, String url, boolean openSource, boolean active,
			boolean recommended)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_LICENSE);

		return scLicenseLocalService.addLicense(
			name, url, openSource, active, recommended);
	}

	@Override
	public void deleteLicense(long licenseId)
		throws PortalException, SystemException {

		SCLicensePermission.check(
			getPermissionChecker(), licenseId, ActionKeys.DELETE);

		scLicenseLocalService.deleteLicense(licenseId);
	}

	@Override
	public SCLicense getLicense(long licenseId)
		throws PortalException, SystemException {

		return scLicenseLocalService.getLicense(licenseId);
	}

	@Override
	public SCLicense updateLicense(
			long licenseId, String name, String url, boolean openSource,
			boolean active, boolean recommended)
		throws PortalException, SystemException {

		SCLicensePermission.check(
			getPermissionChecker(), licenseId, ActionKeys.UPDATE);

		return scLicenseLocalService.updateLicense(
			licenseId, name, url, openSource, active, recommended);
	}

}