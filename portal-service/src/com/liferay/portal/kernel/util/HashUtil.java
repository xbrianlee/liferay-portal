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

package com.liferay.portal.kernel.util;

/**
 * @author Shuyang Zhou
 */
public class HashUtil {

	public static int hash(int seed, boolean value) {
		return seed * 11 + (value ? 1 : 0);
	}

	public static int hash(int seed, int value) {
		return seed * 11 + value;
	}

	public static int hash(int seed, long value) {
		return (int)(seed * 11 + value);
	}

	public static int hash(int seed, Object value) {
		return seed * 11 + (value == null ? 0 : value.hashCode());
	}

}