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

package com.liferay.portal.kernel.webdav.methods;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MethodFactoryRegistryUtil {

	public static MethodFactory getDefaultMethodFactory() {
		return getMethodFactoryRegistry().getDefaultMethodFactory();
	}

	public static MethodFactory getMethodFactory(String className) {
		return getMethodFactoryRegistry().getMethodFactory(className);
	}

	public static List<MethodFactory> getMethodFactoryFactories() {
		return getMethodFactoryRegistry().getMethodFactories();
	}

	public static MethodFactoryRegistry getMethodFactoryRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			MethodFactoryRegistryUtil.class);

		return _methodFactoryRegistry;
	}

	public static void registerMethodFactory(MethodFactory methodFactory) {
		getMethodFactoryRegistry().registerMethodFactory(methodFactory);
	}

	public static void unregisterMethodFactory(MethodFactory methodFactory) {
		getMethodFactoryRegistry().unregisterMethodFactory(methodFactory);
	}

	public void setMethodFactoryRegistry(
		MethodFactoryRegistry methodFactoryRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_methodFactoryRegistry = methodFactoryRegistry;
	}

	private static MethodFactoryRegistry _methodFactoryRegistry;

}