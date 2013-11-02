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

package com.liferay.support.resin;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;

import java.util.ArrayList;

/**
 * @author Raymond Augé
 */
public class EnvironmentClassLoader
	extends com.caucho.loader.EnvironmentClassLoader {

	public EnvironmentClassLoader(ClassLoader classLoader, String id) {
		super(classLoader, id);

		_id = id;
	}

	@Override
	public ArrayList<Permission> getPermissions() {
		if (_SECURITY_ENABLED && (_id != null) && _id.startsWith("web-app:") &&
			!_id.endsWith("/ROOT")) {

			return new ArrayList<Permission>();
		}

		return super.getPermissions();
	}

	@Override
	protected PermissionCollection getPermissions(CodeSource codeSource) {
		if (_SECURITY_ENABLED && (_id != null) && _id.startsWith("web-app:") &&
			!_id.endsWith("/ROOT")) {

			return new Permissions();
		}

		return super.getPermissions(codeSource);
	}

	private static final boolean _SECURITY_ENABLED =
		(System.getSecurityManager() != null);

	private String _id;

}