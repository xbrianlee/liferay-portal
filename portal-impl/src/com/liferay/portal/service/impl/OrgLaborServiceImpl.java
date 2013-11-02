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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.base.OrgLaborServiceBaseImpl;
import com.liferay.portal.service.permission.OrganizationPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgLaborServiceImpl extends OrgLaborServiceBaseImpl {

	@Override
	public OrgLabor addOrgLabor(
			long organizationId, int typeId, int sunOpen, int sunClose,
			int monOpen, int monClose, int tueOpen, int tueClose, int wedOpen,
			int wedClose, int thuOpen, int thuClose, int friOpen, int friClose,
			int satOpen, int satClose)
		throws PortalException, SystemException {

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organizationId, ActionKeys.UPDATE);

		return orgLaborLocalService.addOrgLabor(
			organizationId, typeId, sunOpen, sunClose, monOpen, monClose,
			tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen,
			friClose, satOpen, satClose);
	}

	@Override
	public void deleteOrgLabor(long orgLaborId)
		throws PortalException, SystemException {

		OrgLabor orgLabor = orgLaborPersistence.findByPrimaryKey(orgLaborId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), orgLabor.getOrganizationId(),
			ActionKeys.UPDATE);

		orgLaborLocalService.deleteOrgLabor(orgLaborId);
	}

	@Override
	public OrgLabor getOrgLabor(long orgLaborId)
		throws PortalException, SystemException {

		OrgLabor orgLabor = orgLaborPersistence.findByPrimaryKey(orgLaborId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), orgLabor.getOrganizationId(),
			ActionKeys.VIEW);

		return orgLabor;
	}

	@Override
	public List<OrgLabor> getOrgLabors(long organizationId)
		throws PortalException, SystemException {

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organizationId, ActionKeys.VIEW);

		return orgLaborLocalService.getOrgLabors(organizationId);
	}

	@Override
	public OrgLabor updateOrgLabor(
			long orgLaborId, int typeId, int sunOpen, int sunClose, int monOpen,
			int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
			int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
			int satClose)
		throws PortalException, SystemException {

		OrgLabor orgLabor = orgLaborPersistence.findByPrimaryKey(orgLaborId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), orgLabor.getOrganizationId(),
			ActionKeys.UPDATE);

		return orgLaborLocalService.updateOrgLabor(
			orgLaborId, typeId, sunOpen, sunClose, monOpen, monClose, tueOpen,
			tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose,
			satOpen, satClose);
	}

}