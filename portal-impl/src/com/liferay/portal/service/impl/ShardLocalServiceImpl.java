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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Shard;
import com.liferay.portal.service.base.ShardLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class ShardLocalServiceImpl extends ShardLocalServiceBaseImpl {

	@Override
	public Shard addShard(String className, long classPK, String name)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		if (Validator.isNull(name)) {
			name = PropsValues.SHARD_DEFAULT_NAME;
		}

		long shardId = counterLocalService.increment();

		Shard shard = shardPersistence.create(shardId);

		shard.setClassNameId(classNameId);
		shard.setClassPK(classPK);
		shard.setName(name);

		shardPersistence.update(shard);

		return shard;
	}

	@Override
	public Shard getShard(String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return shardPersistence.findByC_C(classNameId, classPK);
	}

}