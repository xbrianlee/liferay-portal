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

package com.liferay.portal.kernel.portlet;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class FriendlyURLMapperThreadLocal {

	public static Map<String, String> getPRPIdentifiers() {
		return _prpIdentifiers.get();
	}

	public static void setPRPIdentifiers(Map<String, String> prpIdentifiers) {
		_prpIdentifiers.set(prpIdentifiers);
	}

	private static ThreadLocal<Map<String, String>> _prpIdentifiers =
		new ThreadLocal<Map<String, String>>();

}