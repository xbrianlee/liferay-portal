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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Field;

/**
 * @author Brian Wing Shun Chan
 */
public class StaticFieldGetter {

	public static StaticFieldGetter getInstance() {
		return _instance;
	}

	public Object getFieldValue(String className, String fieldName) {
		Object obj = null;

		try {
			Class<?> objClass = Class.forName(className);

			Field field = objClass.getField(fieldName);

			obj = field.get(objClass);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return obj;
	}

	private StaticFieldGetter() {
	}

	private static Log _log = LogFactoryUtil.getLog(StaticFieldGetter.class);

	private static StaticFieldGetter _instance = new StaticFieldGetter();

}