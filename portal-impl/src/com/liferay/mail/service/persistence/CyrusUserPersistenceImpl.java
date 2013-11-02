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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchCyrusUserException;
import com.liferay.mail.model.CyrusUser;
import com.liferay.portal.kernel.dao.orm.ObjectNotFoundException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Dummy;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class CyrusUserPersistenceImpl
	extends BasePersistenceImpl<Dummy> implements CyrusUserPersistence {

	@Override
	public CyrusUser findByPrimaryKey(long userId)
		throws NoSuchCyrusUserException, SystemException {

		Session session = null;

		try {
			session = openSession();

			return (CyrusUser)session.load(
				CyrusUser.class, String.valueOf(userId));
		}
		catch (ObjectNotFoundException onfe) {
			throw new NoSuchCyrusUserException();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public void remove(long userId)
		throws NoSuchCyrusUserException, SystemException {

		Session session = null;

		try {
			session = openSession();

			CyrusUser user = (CyrusUser)session.load(
				CyrusUser.class, String.valueOf(userId));

			session.delete(user);

			session.flush();
		}
		catch (ObjectNotFoundException onfe) {
			throw new NoSuchCyrusUserException();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public void update(CyrusUser user) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			try {
				CyrusUser userModel = (CyrusUser)session.load(
					CyrusUser.class, String.valueOf(user.getUserId()));

				userModel.setPassword(user.getPassword());

				session.flush();
			}
			catch (ObjectNotFoundException onfe) {
				CyrusUser userModel = new CyrusUser(
					user.getUserId(), user.getPassword());

				session.save(userModel);

				session.flush();
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

}