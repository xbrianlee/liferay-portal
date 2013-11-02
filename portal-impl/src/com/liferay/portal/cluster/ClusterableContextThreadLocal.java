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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.util.InitialThreadLocal;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tina Tian
 */
public class ClusterableContextThreadLocal {

	public static void putThreadLocalContext(String key, Serializable value) {
		Map<String, Serializable> context = _contextThreadLocal.get();

		context.put(key, value);
	}

	protected static Map<String, Serializable> collectThreadLocalContext() {
		Map<String, Serializable> context = _contextThreadLocal.get();

		_contextThreadLocal.remove();

		return context;
	}

	private static ThreadLocal<HashMap<String, Serializable>>
		_contextThreadLocal =
			new InitialThreadLocal<HashMap<String, Serializable>>(
				ClusterableContextThreadLocal.class.getName() +
					"._contextThreadLocal",
				new HashMap<String, Serializable>(), true);

}