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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OrganizationFinderUtil {
	public static int countByKeywords(long companyId,
		long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator,
		java.lang.String keywords, java.lang.String type,
		java.lang.Long regionId, java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, parentOrganizationId,
			parentOrganizationIdComparator, keywords, type, regionId,
			countryId, params);
	}

	public static int countByO_U(long organizationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByO_U(organizationId, userId);
	}

	public static int countByC_PO_N_T_S_C_Z_R_C(long companyId,
		long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator, java.lang.String name,
		java.lang.String type, java.lang.String street, java.lang.String city,
		java.lang.String zip, java.lang.Long regionId,
		java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_PO_N_T_S_C_Z_R_C(companyId, parentOrganizationId,
			parentOrganizationIdComparator, name, type, street, city, zip,
			regionId, countryId, params, andOperator);
	}

	public static int countByC_PO_N_T_S_C_Z_R_C(long companyId,
		long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator,
		java.lang.String[] names, java.lang.String type,
		java.lang.String[] streets, java.lang.String[] cities,
		java.lang.String[] zips, java.lang.Long regionId,
		java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_PO_N_T_S_C_Z_R_C(companyId, parentOrganizationId,
			parentOrganizationIdComparator, names, type, streets, cities, zips,
			regionId, countryId, params, andOperator);
	}

	public static java.util.List<com.liferay.portal.model.Organization> findByKeywords(
		long companyId, long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator,
		java.lang.String keywords, java.lang.String type,
		java.lang.Long regionId, java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, parentOrganizationId,
			parentOrganizationIdComparator, keywords, type, regionId,
			countryId, params, start, end, obc);
	}

	public static java.util.List<com.liferay.portal.model.Organization> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<java.lang.Long> findByC_P(long companyId,
		long parentOrganizationId, long previousOrganizationId, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_P(companyId, parentOrganizationId,
			previousOrganizationId, size);
	}

	public static java.util.List<com.liferay.portal.model.Organization> findByC_PO_N_T_S_C_Z_R_C(
		long companyId, long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator, java.lang.String name,
		java.lang.String type, java.lang.String street, java.lang.String city,
		java.lang.String zip, java.lang.Long regionId,
		java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_PO_N_T_S_C_Z_R_C(companyId, parentOrganizationId,
			parentOrganizationIdComparator, name, type, street, city, zip,
			regionId, countryId, params, andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.portal.model.Organization> findByC_PO_N_T_S_C_Z_R_C(
		long companyId, long parentOrganizationId,
		java.lang.String parentOrganizationIdComparator,
		java.lang.String[] names, java.lang.String type,
		java.lang.String[] streets, java.lang.String[] cities,
		java.lang.String[] zips, java.lang.Long regionId,
		java.lang.Long countryId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_PO_N_T_S_C_Z_R_C(companyId, parentOrganizationId,
			parentOrganizationIdComparator, names, type, streets, cities, zips,
			regionId, countryId, params, andOperator, start, end, obc);
	}

	public static OrganizationFinder getFinder() {
		if (_finder == null) {
			_finder = (OrganizationFinder)PortalBeanLocatorUtil.locate(OrganizationFinder.class.getName());

			ReferenceRegistry.registerReference(OrganizationFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OrganizationFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OrganizationFinderUtil.class,
			"_finder");
	}

	private static OrganizationFinder _finder;
}