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

package com.liferay.portlet.dynamicdatamapping.util;

import java.util.HashMap;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class DDMFieldsCounter extends HashMap<Object, Integer> {

	@Override
	public Integer get(Object key) {
		if (!containsKey(key)) {
			put(key, 0);
		}

		return super.get(key);
	}

	public int incrementKey(Object key) {
		int value = get(key);

		put(key, ++value);

		return value;
	}

}