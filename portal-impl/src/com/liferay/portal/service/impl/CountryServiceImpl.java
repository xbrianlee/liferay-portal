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

package com.liferay.portal.service.impl;

import com.liferay.portal.CountryA2Exception;
import com.liferay.portal.CountryA3Exception;
import com.liferay.portal.CountryIddException;
import com.liferay.portal.CountryNameException;
import com.liferay.portal.CountryNumberException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Country;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.base.CountryServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CountryServiceImpl extends CountryServiceBaseImpl {

	@Override
	public Country addCountry(
			String name, String a2, String a3, String number, String idd,
			boolean active)
		throws PortalException, SystemException {

		if (!getPermissionChecker().isOmniadmin()) {
			throw new PrincipalException();
		}

		if (Validator.isNull(name)) {
			throw new CountryNameException();
		}

		if (Validator.isNull(a2)) {
			throw new CountryA2Exception();
		}

		if (Validator.isNull(a3)) {
			throw new CountryA3Exception();
		}

		if (Validator.isNull(number)) {
			throw new CountryNumberException();
		}

		if (Validator.isNull(idd)) {
			throw new CountryIddException();
		}

		long countryId = counterLocalService.increment();

		Country country = countryPersistence.create(countryId);

		country.setName(name);
		country.setA2(a2);
		country.setA3(a3);
		country.setNumber(number);
		country.setIdd(idd);
		country.setActive(active);

		countryPersistence.update(country);

		return country;
	}

	@Override
	public Country fetchCountry(long countryId) throws SystemException {
		return countryPersistence.fetchByPrimaryKey(countryId);
	}

	@Override
	public Country fetchCountryByA2(String a2) throws SystemException {
		return countryPersistence.fetchByA2(a2);
	}

	@Override
	public Country fetchCountryByA3(String a3) throws SystemException {
		return countryPersistence.fetchByA3(a3);
	}

	@Override
	public List<Country> getCountries() throws SystemException {
		return countryPersistence.findAll();
	}

	@Override
	public List<Country> getCountries(boolean active) throws SystemException {
		return countryPersistence.findByActive(active);
	}

	@Override
	public Country getCountry(long countryId)
		throws PortalException, SystemException {

		return countryPersistence.findByPrimaryKey(countryId);
	}

	@Override
	public Country getCountryByA2(String a2)
		throws PortalException, SystemException {

		return countryPersistence.findByA2(a2);
	}

	@Override
	public Country getCountryByA3(String a3)
		throws PortalException, SystemException {

		return countryPersistence.findByA3(a3);
	}

	@Override
	public Country getCountryByName(String name)
		throws PortalException, SystemException {

		return countryPersistence.findByName(name);
	}

}