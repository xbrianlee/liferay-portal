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

import com.liferay.portal.kernel.dao.orm.Dialect;

/**
 * @author Brian Wing Shun Chan
 */
public class DialectImpl implements Dialect {

	public DialectImpl(org.hibernate.dialect.Dialect dialect) {
		_dialect = dialect;
	}

	public org.hibernate.dialect.Dialect getWrappedDialect() {
		return _dialect;
	}

	@Override
	public boolean supportsLimit() {
		return _dialect.supportsLimit();
	}

	private org.hibernate.dialect.Dialect _dialect;

}