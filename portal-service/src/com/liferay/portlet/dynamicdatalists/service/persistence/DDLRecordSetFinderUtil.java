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

package com.liferay.portlet.dynamicdatalists.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class DDLRecordSetFinderUtil {
	public static int countByKeywords(long companyId, long groupId,
		java.lang.String keywords, int scope)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(companyId, groupId, keywords, scope);
	}

	public static int countByC_G_N_D_S(long companyId, long groupId,
		java.lang.String name, java.lang.String description, int scope,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_N_D_S(companyId, groupId, name, description,
			scope, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long groupId,
		java.lang.String keywords, int scope)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupId, keywords, scope);
	}

	public static int filterCountByC_G_N_D_S(long companyId, long groupId,
		java.lang.String name, java.lang.String description, int scope,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_N_D_S(companyId, groupId, name,
			description, scope, andOperator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> filterFindByKeywords(
		long companyId, long groupId, java.lang.String keywords, int scope,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupId, keywords, scope,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> filterFindByC_G_N_D_S(
		long companyId, long groupId, java.lang.String name,
		java.lang.String description, int scope, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_N_D_S(companyId, groupId, name,
			description, scope, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> filterFindByC_G_N_D_S(
		long companyId, long groupId, java.lang.String[] names,
		java.lang.String[] descriptions, int scope, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_N_D_S(companyId, groupId, names,
			descriptions, scope, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> findByKeywords(
		long companyId, long groupId, java.lang.String keywords, int scope,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, keywords, scope, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> findByC_G_N_D_S(
		long companyId, long groupId, java.lang.String name,
		java.lang.String description, int scope, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_N_D_S(companyId, groupId, name, description,
			scope, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> findByC_G_N_D_S(
		long companyId, long groupId, java.lang.String[] names,
		java.lang.String[] descriptions, int scope, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_N_D_S(companyId, groupId, names, descriptions,
			scope, andOperator, start, end, orderByComparator);
	}

	public static DDLRecordSetFinder getFinder() {
		if (_finder == null) {
			_finder = (DDLRecordSetFinder)PortalBeanLocatorUtil.locate(DDLRecordSetFinder.class.getName());

			ReferenceRegistry.registerReference(DDLRecordSetFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DDLRecordSetFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DDLRecordSetFinderUtil.class,
			"_finder");
	}

	private static DDLRecordSetFinder _finder;
}