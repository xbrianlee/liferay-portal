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

import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Raymond Augé
 */
public class ArgumentsUtil {

	public static Map<String, String> parseArguments(String[] args) {
		Map<String, String> arguments = new ArgumentsMap();

		for (String arg : args) {
			int pos = arg.indexOf('=');

			if (pos <= 0) {
				throw new IllegalArgumentException("Bad argument " + arg);
			}

			String key = arg.substring(0, pos).trim();
			String value = arg.substring(pos + 1).trim();

			if (key.startsWith("-D")) {
				key = key.substring(2);

				System.setProperty(key, value);
			}
			else {
				arguments.put(key, value);
			}
		}

		return arguments;
	}

}