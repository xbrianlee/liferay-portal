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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link CountryService}.
 *
 * @author Brian Wing Shun Chan
 * @see CountryService
 * @generated
 */
@ProviderType
public class CountryServiceWrapper implements CountryService,
	ServiceWrapper<CountryService> {
	public CountryServiceWrapper(CountryService countryService) {
		_countryService = countryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _countryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_countryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.model.Country addCountry(java.lang.String name,
		java.lang.String a2, java.lang.String a3, java.lang.String number,
		java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryService.addCountry(name, a2, a3, number, idd, active);
	}

	@Override
	public com.liferay.portal.model.Country fetchCountry(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _countryService.fetchCountry(countryId);
	}

	@Override
	public com.liferay.portal.model.Country fetchCountryByA2(
		java.lang.String a2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _countryService.fetchCountryByA2(a2);
	}

	@Override
	public com.liferay.portal.model.Country fetchCountryByA3(
		java.lang.String a3)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _countryService.fetchCountryByA3(a3);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Country> getCountries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountries();
	}

	@Override
	public java.util.List<com.liferay.portal.model.Country> getCountries(
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountries(active);
	}

	@Override
	public com.liferay.portal.model.Country getCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountry(countryId);
	}

	@Override
	public com.liferay.portal.model.Country getCountryByA2(java.lang.String a2)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountryByA2(a2);
	}

	@Override
	public com.liferay.portal.model.Country getCountryByA3(java.lang.String a3)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountryByA3(a3);
	}

	@Override
	public com.liferay.portal.model.Country getCountryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryService.getCountryByName(name);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CountryService getWrappedCountryService() {
		return _countryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCountryService(CountryService countryService) {
		_countryService = countryService;
	}

	@Override
	public CountryService getWrappedService() {
		return _countryService;
	}

	@Override
	public void setWrappedService(CountryService countryService) {
		_countryService = countryService;
	}

	private CountryService _countryService;
}