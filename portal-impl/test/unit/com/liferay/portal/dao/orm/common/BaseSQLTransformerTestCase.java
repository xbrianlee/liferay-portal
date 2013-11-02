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

package com.liferay.portal.dao.orm.common;

import com.liferay.portal.dao.db.DBFactoryImpl;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;

import org.junit.Before;

/**
 * @author Miguel Pastor
 */
public abstract class BaseSQLTransformerTestCase {

	@Before
	public void setUp() {
		DBFactoryUtil.setDBFactory(new DBFactoryImpl());

		DBFactoryUtil.setDB(getDBType());
	}

	protected abstract String getDBType();

	protected String transformSQL(String sql) {
		return SQLTransformer.transform(sql);
	}

}