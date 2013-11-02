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

package com.liferay.portal.dao.jdbc;

import com.liferay.portal.kernel.dao.jdbc.CurrentConnection;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Shuyang Zhou
 */
@DoPrivileged
public class CurrentConnectionImpl implements CurrentConnection {

	@Override
	public Connection getConnection(DataSource dataSource) {
		ConnectionHolder connectionHolder =
			(ConnectionHolder)TransactionSynchronizationManager.getResource(
				dataSource);

		if (connectionHolder == null) {
			return null;
		}
		else {
			return connectionHolder.getConnection();
		}
	}

}