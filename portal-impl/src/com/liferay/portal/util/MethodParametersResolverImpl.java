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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodParameter;
import com.liferay.portal.kernel.util.MethodParametersResolver;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import jodd.paramo.Paramo;

/**
 * @author Igor Spasic
 */
public class MethodParametersResolverImpl implements MethodParametersResolver {

	@Override
	public MethodParameter[] resolveMethodParameters(Method method) {
		MethodParameter[] methodParameters = _methodParameters.get(method);

		if (methodParameters != null) {
			return methodParameters;
		}

		try {
			Class<?>[] methodParameterTypes = method.getParameterTypes();

			jodd.paramo.MethodParameter[] joddMethodParameters =
				Paramo.resolveParameters(method);

			methodParameters = new MethodParameter[joddMethodParameters.length];

			for (int i = 0; i < joddMethodParameters.length; i++) {
				methodParameters[i] = new MethodParameter(
					joddMethodParameters[i].getName(),
					joddMethodParameters[i].getSignature(),
					methodParameterTypes[i]);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}

		_methodParameters.put(method, methodParameters);

		return methodParameters;
	}

	private static Log _log = LogFactoryUtil.getLog(
		MethodParametersResolverImpl.class);

	private Map<AccessibleObject, MethodParameter[]> _methodParameters =
		new HashMap<AccessibleObject, MethodParameter[]>();

}