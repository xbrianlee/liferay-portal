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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Region;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class AddressImpl extends AddressBaseImpl {

	public AddressImpl() {
	}

	@Override
	public Country getCountry() {
		Country country = null;

		try {
			country = CountryServiceUtil.getCountry(getCountryId());
		}
		catch (Exception e) {
			country = new CountryImpl();

			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		return country;
	}

	@Override
	public Region getRegion() {
		Region region = null;

		try {
			region = RegionServiceUtil.getRegion(getRegionId());
		}
		catch (Exception e) {
			region = new RegionImpl();

			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		return region;
	}

	@Override
	public ListType getType() {
		ListType type = null;

		try {
			type = ListTypeServiceUtil.getListType(getTypeId());
		}
		catch (Exception e) {
			type = new ListTypeImpl();

			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		return type;
	}

	private static Log _log = LogFactoryUtil.getLog(AddressImpl.class);

}