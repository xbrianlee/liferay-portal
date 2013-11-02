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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.JavaDetector;

import java.lang.reflect.Method;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-38327.
 * </p>
 *
 * @author Raymond Augé
 */
public class Reflection extends SecurityManager {

	public static Class<?> getCallerClass(int depth) {
		return _instance._getCallerClass(depth);
	}

	public static int getStackIndex(int oracle, int ibm) {
		return _instance._getStackIndex(new int[] {oracle}, new int[] {ibm});
	}

	public static int getStackIndex(int[] oracle, int[] ibm) {
		return _instance._getStackIndex(oracle, ibm);
	}

	private Reflection() {
		Method[] methods = sun.reflect.Reflection.class.getMethods();

		for (Method method : methods) {
			String methodName = method.getName();

			if (methodName.equals("isCallerSensitive")) {
				_useOldReflection = false;

				break;
			}
		}
	}

	private Class<?> _getCallerClass(int depth) {
		if (_useOldReflection) {

			// This operation is faster, so leave it here for legacy versions

			return sun.reflect.Reflection.getCallerClass(depth + 2);
		}

		Class<?>[] callerClasses = getClassContext();

		// [0] Reflection._getCallerClass
		// [1] Reflection.getCallerClass

		return callerClasses[depth + 1];
	}

	private int _getStackIndex(int[] oracle, int[] ibm) {
		if ((oracle.length != ibm.length) && (oracle.length == 0)) {
			throw new IllegalArgumentException(
				"Both arrays must not be empty and have the same length");
		}

		int index = 0;

		// Case 1: Oracle or IBM (default case)

		if (JavaDetector.isIBM()) {
			index = ibm[0];
		}
		else {
			index = oracle[0];
		}

		if (oracle.length == 1) {
			return index;
		}

		// Case 2: JDK7

		if (JavaDetector.isJDK7()) {
			if (JavaDetector.isIBM()) {
				index = ibm[1];
			}
			else {
				index = oracle[1];
			}
		}

		if (oracle.length == 2) {
			return index;
		}

		// Case 3: JDK7 >= u25

		if (JavaDetector.isJDK7() && !_useOldReflection) {
			if (JavaDetector.isIBM()) {
				index = ibm[2];
			}
			else {
				index = oracle[2];
			}
		}

		return index;
	}

	private static final Reflection _instance = new Reflection();

	private boolean _useOldReflection = true;

}