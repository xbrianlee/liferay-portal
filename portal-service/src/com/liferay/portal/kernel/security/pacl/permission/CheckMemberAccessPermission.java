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

package com.liferay.portal.kernel.security.pacl.permission;

import java.security.BasicPermission;

/**
 * @author     Raymond Augé
 * @author     Zsolt Berentey
 * @deprecated As of 6.2.0
 */
public class CheckMemberAccessPermission extends BasicPermission {

	public CheckMemberAccessPermission(
		String name, Class<?> callerClass, ClassLoader callerClassLoader,
		Class<?> subjectClass, ClassLoader subjectClassLoader) {

		super(name);
	}

}