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

package com.liferay.portal.kernel.javadoc;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JavadocManagerUtil {

	public static JavadocManager getJavadocManager() {
		PortalRuntimePermission.checkGetBeanProperty(JavadocManagerUtil.class);

		return _javadocManager;
	}

	public static void load(
		String servletContextName, ClassLoader classLoader) {

		getJavadocManager().load(servletContextName, classLoader);
	}

	public static JavadocClass lookupJavadocClass(Class<?> clazz) {
		return getJavadocManager().lookupJavadocClass(clazz);
	}

	public static JavadocMethod lookupJavadocMethod(Method method) {
		return getJavadocManager().lookupJavadocMethod(method);
	}

	public static void unload(String servletContextName) {
		getJavadocManager().unload(servletContextName);
	}

	public void setJavadocManager(JavadocManager javadocManager) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_javadocManager = javadocManager;
	}

	private static JavadocManager _javadocManager;

}