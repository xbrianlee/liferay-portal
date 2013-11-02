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

package com.liferay.util;

/**
 * @author Brian Wing Shun Chan
 */
public class Distance {

	public static double calculate(
		double lat1, double lon1, double lat2, double lon2) {

		// Convert from radians to degrees

		lat1 = (Math.PI * lat1) / 180;
		lon1 = (Math.PI * lon1) / 180;
		lat2 = (Math.PI * lat2) / 180;
		lon2 = (Math.PI * lon2) / 180;

		double miles =
			3963.4 *
			Math.acos(
				(Math.sin(lat1) * Math.sin(lat2)) +
				(Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)));

		return miles;
	}

	public static double kmToMiles(double km) {
		return km * 0.621;
	}

	public static double milesToKm(double miles) {
		return miles / 0.621;
	}

}