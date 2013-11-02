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

package com.liferay.portal.dao.orm.hibernate;

import org.hibernate.LockMode;

/**
 * @author Shuyang Zhou
 */
public class SybaseASE157Dialect
	extends org.hibernate.dialect.SybaseASE157Dialect {

	@Override
	public String appendLockHint(LockMode mode, String tableName) {
		if (mode.greaterThan(LockMode.READ)) {
			return tableName + " holdlock";
		}

		return tableName;
	}

}