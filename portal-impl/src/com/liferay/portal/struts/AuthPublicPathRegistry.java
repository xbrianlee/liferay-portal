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

package com.liferay.portal.struts;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mika Koivisto
 */
public class AuthPublicPathRegistry {

	public static boolean contains(String path) {
		return _paths.contains(path);
	}

	public static void register(String... paths) {
		for (String path : paths) {
			_paths.add(path);
		}
	}

	public static void unregister(String... paths) {
		for (String path : paths) {
			_paths.remove(path);
		}
	}

	private static Set<String> _paths = new HashSet<String>();

}