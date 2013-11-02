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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class CustomSQLParam {

	public CustomSQLParam(String sql, Object value) {
		_sql = sql;
		_value = value;
	}

	public String getSQL() {
		return _sql;
	}

	public void process(QueryPos qPos) {
		if (_value instanceof Long) {
			Long valueLong = (Long)_value;

			if (valueLong != null) {
				qPos.add(valueLong);
			}
		}
		else if (_value instanceof Long[]) {
			Long[] valueArray = (Long[])_value;

			for (int i = 0; i < valueArray.length; i++) {
				if (valueArray[i] != null) {
					qPos.add(valueArray[i]);
				}
			}
		}
		else if (_value instanceof String) {
			String valueString = (String)_value;

			if (Validator.isNotNull(valueString)) {
				qPos.add(valueString);
			}
		}
		else if (_value instanceof String[]) {
			String[] valueArray = (String[])_value;

			for (int i = 0; i < valueArray.length; i++) {
				if (Validator.isNotNull(valueArray[i])) {
					qPos.add(valueArray[i]);
				}
			}
		}
	}

	private String _sql;
	private Object _value;

}