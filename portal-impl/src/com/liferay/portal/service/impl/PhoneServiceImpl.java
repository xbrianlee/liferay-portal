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
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.PhoneServiceBaseImpl;
import com.liferay.portal.service.permission.CommonPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PhoneServiceImpl extends PhoneServiceBaseImpl {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #addPhone( String, long,
	 *             String, String, int, boolean, ServiceContext)}
	 */
	@Override
	public Phone addPhone(
			String className, long classPK, String number, String extension,
			int typeId, boolean primary)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.UPDATE);

		return phoneLocalService.addPhone(
			getUserId(), className, classPK, number, extension, typeId,
			primary);
	}

	@Override
	public Phone addPhone(
			String className, long classPK, String number, String extension,
			int typeId, boolean primary, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.UPDATE);

		return phoneLocalService.addPhone(
			getUserId(), className, classPK, number, extension, typeId, primary,
			serviceContext);
	}

	@Override
	public void deletePhone(long phoneId)
		throws PortalException, SystemException {

		Phone phone = phonePersistence.findByPrimaryKey(phoneId);

		CommonPermissionUtil.check(
			getPermissionChecker(), phone.getClassNameId(), phone.getClassPK(),
			ActionKeys.UPDATE);

		phoneLocalService.deletePhone(phoneId);
	}

	@Override
	public Phone getPhone(long phoneId)
		throws PortalException, SystemException {

		Phone phone = phonePersistence.findByPrimaryKey(phoneId);

		CommonPermissionUtil.check(
			getPermissionChecker(), phone.getClassNameId(), phone.getClassPK(),
			ActionKeys.VIEW);

		return phone;
	}

	@Override
	public List<Phone> getPhones(String className, long classPK)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.VIEW);

		User user = getUser();

		return phoneLocalService.getPhones(
			user.getCompanyId(), className, classPK);
	}

	@Override
	public Phone updatePhone(
			long phoneId, String number, String extension, int typeId,
			boolean primary)
		throws PortalException, SystemException {

		Phone phone = phonePersistence.findByPrimaryKey(phoneId);

		CommonPermissionUtil.check(
			getPermissionChecker(), phone.getClassNameId(), phone.getClassPK(),
			ActionKeys.UPDATE);

		return phoneLocalService.updatePhone(
			phoneId, number, extension, typeId, primary);
	}

}