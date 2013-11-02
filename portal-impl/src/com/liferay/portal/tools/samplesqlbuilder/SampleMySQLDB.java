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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.dao.db.MySQLDB;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * A simplified version of MySQLDB for sample SQL generation. This should not be
 * used for any other purposes.
 *
 * @author Shuyang Zhou
 */
public class SampleMySQLDB extends MySQLDB {

	@Override
	public String buildSQL(String template) {
		return StringUtil.replace(template, _GENERIC_TEMPLATE, _MYSQL_TEMPLATE);
	}

	private static final String[] _GENERIC_TEMPLATE = {
		"TRUE", "FALSE", "'01/01/1970'", "CURRENT_TIMESTAMP",
		"COMMIT_TRANSACTION"
	};

	private static final String[] _MYSQL_TEMPLATE = {
		"1", "0", "'1970-01-01'", "now()", "commit"
	};

}