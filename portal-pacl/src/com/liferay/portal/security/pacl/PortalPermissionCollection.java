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

import com.liferay.portal.kernel.util.StringPool;

import java.security.Permission;
import java.security.Policy;

/**
 * @author Raymond Augé
 */
public class PortalPermissionCollection extends LenientPermissionCollection {

	public PortalPermissionCollection(PACLPolicy paclPolicy) {
		_paclPolicy = paclPolicy;
	}

	public ClassLoader getClassLoader() {
		return _paclPolicy.getClassLoader();
	}

	public PACLPolicy getPACLPolicy() {
		return _paclPolicy;
	}

	public Policy getPolicy() {
		return _paclPolicy.getPolicy();
	}

	@Override
	public boolean implies(Permission permission) {
		if (!_paclPolicy.isActive()) {
			return true;
		}

		if (permission instanceof PACLUtil.Permission) {
			PACLPolicyThreadLocal.set(_paclPolicy);

			return true;
		}

		if (_paclPolicy.implies(permission)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		Class<?> clazz = getClass();

		String className = clazz.getSimpleName();

		return className.concat(StringPool.POUND).concat(
			_paclPolicy.toString());
	}

	private PACLPolicy _paclPolicy;

}