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
import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.service.base.UserIdMapperLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserIdMapperLocalServiceImpl
	extends UserIdMapperLocalServiceBaseImpl {

	@Override
	public void deleteUserIdMappers(long userId) throws SystemException {
		userIdMapperPersistence.removeByUserId(userId);
	}

	@Override
	public UserIdMapper getUserIdMapper(long userId, String type)
		throws PortalException, SystemException {

		return userIdMapperPersistence.findByU_T(userId, type);
	}

	@Override
	public UserIdMapper getUserIdMapperByExternalUserId(
			String type, String externalUserId)
		throws PortalException, SystemException {

		return userIdMapperPersistence.findByT_E(type, externalUserId);
	}

	@Override
	public List<UserIdMapper> getUserIdMappers(long userId)
		throws SystemException {

		return userIdMapperPersistence.findByUserId(userId);
	}

	@Override
	public UserIdMapper updateUserIdMapper(
			long userId, String type, String description, String externalUserId)
		throws SystemException {

		UserIdMapper userIdMapper = userIdMapperPersistence.fetchByU_T(
			userId, type);

		if (userIdMapper == null) {
			long userIdMapperId = counterLocalService.increment();

			userIdMapper = userIdMapperPersistence.create(userIdMapperId);
		}

		userIdMapper.setUserId(userId);
		userIdMapper.setType(type);
		userIdMapper.setDescription(description);
		userIdMapper.setExternalUserId(externalUserId);

		userIdMapperPersistence.update(userIdMapper);

		return userIdMapper;
	}

}