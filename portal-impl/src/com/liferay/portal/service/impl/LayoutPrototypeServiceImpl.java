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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutPrototypeServiceBaseImpl;
import com.liferay.portal.service.permission.LayoutPrototypePermissionUtil;
import com.liferay.portal.service.permission.PortalPermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class LayoutPrototypeServiceImpl extends LayoutPrototypeServiceBaseImpl {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #addLayoutPrototype(Map,
	 *             String, boolean, ServiceContext)}
	 */
	@Override
	public LayoutPrototype addLayoutPrototype(
			Map<Locale, String> nameMap, String description, boolean active)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_LAYOUT_PROTOTYPE);

		User user = getUser();

		return layoutPrototypeLocalService.addLayoutPrototype(
			user.getUserId(), user.getCompanyId(), nameMap, description,
			active);
	}

	@Override
	public LayoutPrototype addLayoutPrototype(
			Map<Locale, String> nameMap, String description, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_LAYOUT_PROTOTYPE);

		User user = getUser();

		return layoutPrototypeLocalService.addLayoutPrototype(
			user.getUserId(), user.getCompanyId(), nameMap, description, active,
			serviceContext);
	}

	@Override
	public void deleteLayoutPrototype(long layoutPrototypeId)
		throws PortalException, SystemException {

		LayoutPrototypePermissionUtil.check(
			getPermissionChecker(), layoutPrototypeId, ActionKeys.DELETE);

		layoutPrototypeLocalService.deleteLayoutPrototype(layoutPrototypeId);
	}

	@Override
	public LayoutPrototype getLayoutPrototype(long layoutPrototypeId)
		throws PortalException, SystemException {

		LayoutPrototypePermissionUtil.check(
			getPermissionChecker(), layoutPrototypeId, ActionKeys.VIEW);

		return layoutPrototypeLocalService.getLayoutPrototype(
			layoutPrototypeId);
	}

	@Override
	public List<LayoutPrototype> search(
			long companyId, Boolean active, OrderByComparator obc)
		throws PortalException, SystemException {

		List<LayoutPrototype> filteredLayoutPrototypes =
			new ArrayList<LayoutPrototype>();

		List<LayoutPrototype> layoutPrototypes =
			layoutPrototypeLocalService.search(
				companyId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		for (LayoutPrototype layoutPrototype : layoutPrototypes) {
			if (LayoutPrototypePermissionUtil.contains(
					getPermissionChecker(),
					layoutPrototype.getLayoutPrototypeId(), ActionKeys.VIEW)) {

				filteredLayoutPrototypes.add(layoutPrototype);
			}
		}

		return filteredLayoutPrototypes;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #updateLayoutPrototype(long,
	 *             Map, String, boolean, ServiceContext)}
	 */
	@Override
	public LayoutPrototype updateLayoutPrototype(
			long layoutPrototypeId, Map<Locale, String> nameMap,
			String description, boolean active)
		throws PortalException, SystemException {

		LayoutPrototypePermissionUtil.check(
			getPermissionChecker(), layoutPrototypeId, ActionKeys.UPDATE);

		return layoutPrototypeLocalService.updateLayoutPrototype(
			layoutPrototypeId, nameMap, description, active);
	}

	@Override
	public LayoutPrototype updateLayoutPrototype(
			long layoutPrototypeId, Map<Locale, String> nameMap,
			String description, boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutPrototypePermissionUtil.check(
			getPermissionChecker(), layoutPrototypeId, ActionKeys.UPDATE);

		return layoutPrototypeLocalService.updateLayoutPrototype(
			layoutPrototypeId, nameMap, description, active, serviceContext);
	}

}