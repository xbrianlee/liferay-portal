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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class MethodParametersResolverUtil {

	public static MethodParametersResolver getMethodParametersResolver() {
		PortalRuntimePermission.checkGetBeanProperty(
			MethodParametersResolverUtil.class);

		return _methodParametersResolver;
	}

	public static MethodParameter[] resolveMethodParameters(Method method) {
		return getMethodParametersResolver().resolveMethodParameters(method);
	}

	public void setMethodParametersResolver(
		MethodParametersResolver methodParametersResolver) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_methodParametersResolver = methodParametersResolver;
	}

	private static MethodParametersResolver _methodParametersResolver;

}