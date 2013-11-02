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

package com.liferay.portal.scheduler.quartz;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.quartz.impl.jdbcjobstore.DB2v8Delegate;
import org.quartz.impl.jdbcjobstore.DriverDelegate;
import org.quartz.impl.jdbcjobstore.HSQLDBDelegate;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.impl.jdbcjobstore.MSSQLDelegate;
import org.quartz.impl.jdbcjobstore.NoSuchDelegateException;
import org.quartz.impl.jdbcjobstore.PostgreSQLDelegate;
import org.quartz.impl.jdbcjobstore.StdJDBCDelegate;
import org.quartz.impl.jdbcjobstore.SybaseDelegate;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalJobStore extends JobStoreTX {

	@Override
	protected DriverDelegate getDelegate() throws NoSuchDelegateException {
		if (_driverDelegate != null) {
			return _driverDelegate;
		}

		try {
			Class<?> driverDelegateClass = StdJDBCDelegate.class;

			DB db = DBFactoryUtil.getDB();

			String dbType = db.getType();

			if (dbType.equals(DB.TYPE_DB2)) {
				driverDelegateClass = DB2v8Delegate.class;
			}
			else if (dbType.equals(DB.TYPE_HYPERSONIC)) {
				driverDelegateClass = HSQLDBDelegate.class;
			}
			else if (dbType.equals(DB.TYPE_POSTGRESQL)) {
				driverDelegateClass = PostgreSQLDelegate.class;
			}
			else if (dbType.equals(DB.TYPE_SQLSERVER)) {
				driverDelegateClass = MSSQLDelegate.class;
			}
			else if (dbType.equals(DB.TYPE_SYBASE)) {
				driverDelegateClass = SybaseDelegate.class;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Instantiating " + driverDelegateClass);
			}

			setDriverDelegateClass(driverDelegateClass.getName());

			_driverDelegate = super.getDelegate();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Using driver delegate " +
						_driverDelegate.getClass().getName());
			}

			return _driverDelegate;
		}
		catch (NoSuchDelegateException nsde) {
			throw nsde;
		}
		catch (Exception e) {
			throw new NoSuchDelegateException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortalJobStore.class);

	private DriverDelegate _driverDelegate;

}