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

import java.lang.reflect.Method;

import java.security.Permission;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class InactivePACLPolicy extends BasePACLPolicy {

	public InactivePACLPolicy(
		String servletContextName, ClassLoader classLoader,
		Properties properties) {

		super(servletContextName, classLoader, properties);
	}

	@Override
	public boolean hasJNDI(String name) {
		return true;
	}

	public boolean hasPortalService(
		Object object, Method method, Object[] arguments) {

		return true;
	}

	@Override
	public boolean hasSQL(String sql) {
		return true;
	}

	@Override
	public boolean implies(Permission permission) {
		return true;
	}

	@Override
	public boolean isActive() {
		return false;
	}

}