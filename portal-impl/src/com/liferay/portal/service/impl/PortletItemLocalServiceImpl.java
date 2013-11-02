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

import com.liferay.portal.NoSuchPortletItemException;
import com.liferay.portal.PortletItemNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.User;
import com.liferay.portal.service.base.PortletItemLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class PortletItemLocalServiceImpl
	extends PortletItemLocalServiceBaseImpl {

	@Override
	public PortletItem addPortletItem(
			long userId, long groupId, String name, String portletId,
			String className)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(name);

		long portletItemId = counterLocalService.increment();

		PortletItem portletItem = portletItemPersistence.create(portletItemId);

		portletItem.setGroupId(groupId);
		portletItem.setCompanyId(user.getCompanyId());
		portletItem.setUserId(user.getUserId());
		portletItem.setUserName(user.getFullName());
		portletItem.setCreateDate(now);
		portletItem.setModifiedDate(now);
		portletItem.setName(name);
		portletItem.setPortletId(portletId);
		portletItem.setClassNameId(classNameId);

		portletItemPersistence.update(portletItem);

		return portletItem;
	}

	@Override
	public PortletItem getPortletItem(
			long groupId, String name, String portletId, String className)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return portletItemPersistence.findByG_N_P_C(
			groupId, name, portletId, classNameId);
	}

	@Override
	public List<PortletItem> getPortletItems(long groupId, String className)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return portletItemPersistence.findByG_C(groupId, classNameId);
	}

	@Override
	public List<PortletItem> getPortletItems(
			long groupId, String portletId, String className)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return portletItemPersistence.findByG_P_C(
			groupId, portletId, classNameId);
	}

	@Override
	public PortletItem updatePortletItem(
			long userId, long groupId, String name, String portletId,
			String className)
		throws PortalException, SystemException {

		PortletItem portletItem = null;

		try {
			User user = userPersistence.findByPrimaryKey(userId);

			portletItem = getPortletItem(
				groupId, name, portletId, PortletPreferences.class.getName());

			portletItem.setUserId(userId);
			portletItem.setUserName(user.getFullName());
			portletItem.setModifiedDate(new Date());

			portletItemPersistence.update(portletItem);
		}
		catch (NoSuchPortletItemException nspie) {
			portletItem = addPortletItem(
				userId, groupId, name, portletId,
				PortletPreferences.class.getName());
		}

		return portletItem;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new PortletItemNameException();
		}
	}

}