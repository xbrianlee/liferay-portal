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

package com.liferay.portal.dao.orm.jpa;

import com.liferay.portal.kernel.dao.orm.Dialect;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class SessionFactoryImpl implements SessionFactory {

	@Override
	public void closeSession(Session session) throws ORMException {
		if (session != null) {
			session.close();
		}
	}

	@Override
	public Session getCurrentSession() {
		return _session;
	}

	@Override
	public Dialect getDialect() throws ORMException {
		return new DialectImpl();
	}

	@Override
	public Session openNewSession(Connection connection) throws ORMException {
		EntityManager entityManager =
			_entityManagerFactory.createEntityManager();

		return new NewSessionImpl(entityManager);
	}

	@Override
	public Session openSession() throws ORMException {
		return _session;
	}

	@PersistenceUnit
	public void setEntityManagerFactory(
		EntityManagerFactory entityManagerFactory) {

		_entityManagerFactory = entityManagerFactory;
	}

	public void setSession(Session session) {
		_session = session;
	}

	private EntityManagerFactory _entityManagerFactory;
	private Session _session;

}