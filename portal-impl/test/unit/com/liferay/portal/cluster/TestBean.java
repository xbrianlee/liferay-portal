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

/**
 * @author Tina Tian
 */
public class TestBean {

	public static String TIMESTAMP;

	public static String testMethod1(String timeStamp) {
		if (timeStamp.length() == 0) {
			return null;
		}

		TIMESTAMP = timeStamp;

		return timeStamp;
	}

	public static Object testMethod2() {
		return new Object();
	}

	public static Object testMethod3(String timeStamp) throws Exception {
		throw new Exception(timeStamp);
	}

	public void setKey(String key) {
		_key = key;
	}

	public String testMethod4() {
		return _key;
	}

	private String _key;

}