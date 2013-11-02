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

package com.liferay.portal.dao.jdbc.pool.c3p0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PropsUtil;

import com.mchange.v2.c3p0.ConnectionCustomizer;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalConnectionCustomizer implements ConnectionCustomizer {

	@Override
	public void onAcquire(
			Connection connection, String parentDataSourceIdentityToken)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("JDBC property prefix " + parentDataSourceIdentityToken);
		}

		String transactionIsolation = PropsUtil.get(
			parentDataSourceIdentityToken + "transactionIsolation");

		if (_log.isDebugEnabled()) {
			_log.debug("Custom transaction isolation " + transactionIsolation);
		}

		if (transactionIsolation != null) {
			connection.setTransactionIsolation(
				GetterUtil.getInteger(transactionIsolation));
		}
	}

	@Override
	public void onCheckIn(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	@Override
	public void onCheckOut(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	@Override
	public void onDestroy(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalConnectionCustomizer.class);

}