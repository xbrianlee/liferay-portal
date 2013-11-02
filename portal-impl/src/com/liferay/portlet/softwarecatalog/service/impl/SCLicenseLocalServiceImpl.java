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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.softwarecatalog.LicenseNameException;
import com.liferay.portlet.softwarecatalog.RequiredLicenseException;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.service.base.SCLicenseLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCLicenseLocalServiceImpl extends SCLicenseLocalServiceBaseImpl {

	@Override
	public SCLicense addLicense(
			String name, String url, boolean openSource, boolean active,
			boolean recommended)
		throws PortalException, SystemException {

		validate(name);

		long licenseId = counterLocalService.increment();

		SCLicense license = scLicensePersistence.create(licenseId);

		license.setName(name);
		license.setUrl(url);
		license.setOpenSource(openSource);
		license.setActive(active);
		license.setRecommended(recommended);

		scLicensePersistence.update(license);

		return license;
	}

	@Override
	public void deleteLicense(long licenseId)
		throws PortalException, SystemException {

		SCLicense license = scLicensePersistence.findByPrimaryKey(licenseId);

		if (scLicensePersistence.getSCProductEntriesSize(licenseId) > 0) {
			throw new RequiredLicenseException();
		}

		deleteLicense(license);
	}

	@Override
	public void deleteLicense(SCLicense license) throws SystemException {
		scLicensePersistence.remove(license);
	}

	@Override
	public SCLicense getLicense(long licenseId)
		throws PortalException, SystemException {

		return scLicensePersistence.findByPrimaryKey(licenseId);
	}

	@Override
	public List<SCLicense> getLicenses() throws SystemException {
		return scLicensePersistence.findAll();
	}

	@Override
	public List<SCLicense> getLicenses(boolean active, boolean recommended)
		throws SystemException {

		return scLicensePersistence.findByA_R(active, recommended);
	}

	@Override
	public List<SCLicense> getLicenses(
			boolean active, boolean recommended, int start, int end)
		throws SystemException {

		return scLicensePersistence.findByA_R(active, recommended, start, end);
	}

	@Override
	public List<SCLicense> getLicenses(int start, int end)
		throws SystemException {

		return scLicensePersistence.findAll(start, end);
	}

	@Override
	public int getLicensesCount() throws SystemException {
		return scLicensePersistence.countAll();
	}

	@Override
	public int getLicensesCount(boolean active, boolean recommended)
		throws SystemException {

		return scLicensePersistence.countByA_R(active, recommended);
	}

	@Override
	public List<SCLicense> getProductEntryLicenses(long productEntryId)
		throws SystemException {

		return scProductEntryPersistence.getSCLicenses(productEntryId);
	}

	@Override
	public SCLicense updateLicense(
			long licenseId, String name, String url, boolean openSource,
			boolean active, boolean recommended)
		throws PortalException, SystemException {

		validate(name);

		SCLicense license = scLicensePersistence.findByPrimaryKey(licenseId);

		license.setName(name);
		license.setUrl(url);
		license.setOpenSource(openSource);
		license.setActive(active);
		license.setRecommended(recommended);

		scLicensePersistence.update(license);

		return license;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new LicenseNameException();
		}
	}

}