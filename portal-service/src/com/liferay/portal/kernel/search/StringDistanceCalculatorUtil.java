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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 */
public class StringDistanceCalculatorUtil {

	public static float getDistance(String string1, String string2) {
		return getStringDistanceCalculator().getDistance(string1, string2);
	}

	public static StringDistanceCalculator getStringDistanceCalculator() {
		PortalRuntimePermission.checkGetBeanProperty(
			StringDistanceCalculatorUtil.class);

		return _stringDistanceCalculator;
	}

	public void setStringDistanceCalculator(
		StringDistanceCalculator stringDistanceCalculator) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_stringDistanceCalculator = stringDistanceCalculator;
	}

	private static StringDistanceCalculator _stringDistanceCalculator;

}