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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.security.Principal;

/**
 * @author Brian Wing Shun Chan
 */
public class ProtectedPrincipal implements Principal, Serializable {

	public ProtectedPrincipal() {
		this(StringPool.BLANK);
	}

	public ProtectedPrincipal(String name) {
		_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProtectedPrincipal)) {
			return false;
		}

		ProtectedPrincipal protectedPrincipal = (ProtectedPrincipal)obj;

		if (protectedPrincipal.getName().equals(_name)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public String toString() {
		return _name;
	}

	private String _name;

}