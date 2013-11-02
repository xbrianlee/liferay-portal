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

import java.security.Permission;
import java.security.PermissionCollection;

import java.util.Collections;
import java.util.Enumeration;

/**
 * @author Raymond Augé
 */
public class LenientPermissionCollection extends PermissionCollection {

	@Override
	public void add(Permission permission) {
	}

	@Override
	public Enumeration<Permission> elements() {
		return Collections.enumeration(Collections.<Permission>emptyList());
	}

	@Override
	public boolean implies(Permission permission) {
		return true;
	}

}