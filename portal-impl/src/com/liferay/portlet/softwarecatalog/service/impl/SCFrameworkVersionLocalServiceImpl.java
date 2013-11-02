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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.softwarecatalog.FrameworkVersionNameException;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.service.base.SCFrameworkVersionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCFrameworkVersionLocalServiceImpl
	extends SCFrameworkVersionLocalServiceBaseImpl {

	@Override
	public SCFrameworkVersion addFrameworkVersion(
			long userId, String name, String url, boolean active, int priority,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Framework version

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(name);

		long frameworkVersionId = counterLocalService.increment();

		SCFrameworkVersion frameworkVersion =
			scFrameworkVersionPersistence.create(frameworkVersionId);

		frameworkVersion.setGroupId(groupId);
		frameworkVersion.setCompanyId(user.getCompanyId());
		frameworkVersion.setUserId(user.getUserId());
		frameworkVersion.setUserName(user.getFullName());
		frameworkVersion.setCreateDate(now);
		frameworkVersion.setModifiedDate(now);
		frameworkVersion.setName(name);
		frameworkVersion.setUrl(url);
		frameworkVersion.setActive(active);
		frameworkVersion.setPriority(priority);

		scFrameworkVersionPersistence.update(frameworkVersion);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addFrameworkVersionResources(
				frameworkVersion, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addFrameworkVersionResources(
				frameworkVersion, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return frameworkVersion;
	}

	@Override
	public void addFrameworkVersionResources(
			long frameworkVersionId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		SCFrameworkVersion frameworkVersion =
			scFrameworkVersionPersistence.findByPrimaryKey(frameworkVersionId);

		addFrameworkVersionResources(
			frameworkVersion, addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addFrameworkVersionResources(
			long frameworkVersionId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		SCFrameworkVersion frameworkVersion =
			scFrameworkVersionPersistence.findByPrimaryKey(frameworkVersionId);

		addFrameworkVersionResources(
			frameworkVersion, groupPermissions, guestPermissions);
	}

	@Override
	public void addFrameworkVersionResources(
			SCFrameworkVersion frameworkVersion, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			frameworkVersion.getCompanyId(), frameworkVersion.getGroupId(),
			frameworkVersion.getUserId(), SCFrameworkVersion.class.getName(),
			frameworkVersion.getFrameworkVersionId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addFrameworkVersionResources(
			SCFrameworkVersion frameworkVersion, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			frameworkVersion.getCompanyId(), frameworkVersion.getGroupId(),
			frameworkVersion.getUserId(), SCFrameworkVersion.class.getName(),
			frameworkVersion.getFrameworkVersionId(), groupPermissions,
			guestPermissions);
	}

	@Override
	public void deleteFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {

		SCFrameworkVersion frameworkVersion =
			scFrameworkVersionPersistence.findByPrimaryKey(frameworkVersionId);

		deleteFrameworkVersion(frameworkVersion);
	}

	@Override
	public void deleteFrameworkVersion(SCFrameworkVersion frameworkVersion)
		throws SystemException {

		scFrameworkVersionPersistence.remove(frameworkVersion);
	}

	@Override
	public void deleteFrameworkVersions(long groupId) throws SystemException {
		List<SCFrameworkVersion> frameworkVersions =
			scFrameworkVersionPersistence.findByGroupId(groupId);

		for (SCFrameworkVersion frameworkVersion : frameworkVersions) {
			deleteFrameworkVersion(frameworkVersion);
		}
	}

	@Override
	public SCFrameworkVersion getFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {

		return scFrameworkVersionPersistence.findByPrimaryKey(
			frameworkVersionId);
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions(
			long groupId, boolean active)
		throws SystemException {

		return scFrameworkVersionPersistence.findByG_A(groupId, active);
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions(
			long groupId, boolean active, int start, int end)
		throws SystemException {

		return scFrameworkVersionPersistence.findByG_A(
			groupId, active, start, end);
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions(
			long groupId, int start, int end)
		throws SystemException {

		return scFrameworkVersionPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public int getFrameworkVersionsCount(long groupId) throws SystemException {
		return scFrameworkVersionPersistence.countByGroupId(groupId);
	}

	@Override
	public int getFrameworkVersionsCount(long groupId, boolean active)
		throws SystemException {

		return scFrameworkVersionPersistence.countByG_A(groupId, active);
	}

	@Override
	public List<SCFrameworkVersion> getProductVersionFrameworkVersions(
			long productVersionId)
		throws SystemException {

		return scProductVersionPersistence.getSCFrameworkVersions(
			productVersionId);
	}

	@Override
	public SCFrameworkVersion updateFrameworkVersion(
			long frameworkVersionId, String name, String url, boolean active,
			int priority)
		throws PortalException, SystemException {

		validate(name);

		SCFrameworkVersion frameworkVersion =
			scFrameworkVersionPersistence.findByPrimaryKey(frameworkVersionId);

		frameworkVersion.setName(name);
		frameworkVersion.setUrl(url);
		frameworkVersion.setActive(active);
		frameworkVersion.setPriority(priority);

		scFrameworkVersionPersistence.update(frameworkVersion);

		return frameworkVersion;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new FrameworkVersionNameException();
		}
	}

}