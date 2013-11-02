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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

/**
 * @author Raymond Augé
 */
public class ArgumentsMap extends HashMap<String, String> {

	@Override
	public String get(Object key) {
		String value = super.get(key);

		if (Validator.isNull(value)) {
			value = System.getProperty((String)key);
		}

		return value;
	}

}