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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tina Tian
 */
public class MessageValuesThreadLocal {

	public static Object getValue(String key) {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			return null;
		}

		return messageValues.get(key);
	}

	public static Map<String, Object> getValues() {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			return Collections.emptyMap();
		}

		return messageValues;
	}

	public static void setValue(String key, Object value) {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			messageValues = new HashMap<String, Object>();

			_messageValuesThreadLocal.set(messageValues);
		}

		messageValues.put(key, value);
	}

	private static ThreadLocal<Map<String, Object>> _messageValuesThreadLocal =
		new AutoResetThreadLocal<Map<String, Object>>(
			MessageValuesThreadLocal.class.getName());

}