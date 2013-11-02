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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.LockMode;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class LockFinderImpl
	extends BasePersistenceImpl<Lock> implements LockFinder {

	public static final String FIND_BY_C_K =
		LockFinder.class.getName() + ".findByC_K";

	@Override
	public Lock fetchByC_K(String className, String key, LockMode lockMode)
		throws SystemException {

		if (lockMode == null) {
			return lockPersistence.fetchByC_K(className, key);
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_K);

			Query q = session.createQuery(sql);

			q.setLockMode("lock", lockMode);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(className);
			qPos.add(key);

			List<Lock> locks = q.list();

			if (!locks.isEmpty()) {
				return locks.get(0);
			}

			return null;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;

}