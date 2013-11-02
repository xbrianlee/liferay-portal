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

/**
 * @author Brian Wing Shun Chan
 */
public class HashCodeFactoryUtil {

	public static HashCode getHashCode() {
		return getHashCodeFactory().getHashCode();
	}

	public static HashCode getHashCode(
		int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {

		return getHashCodeFactory().getHashCode(
			initialNonZeroOddNumber, multiplierNonZeroOddNumber);
	}

	public static HashCodeFactory getHashCodeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(HashCodeFactoryUtil.class);

		return _hashCodeFactory;
	}

	public void setHashCodeFactory(HashCodeFactory hashCodeFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_hashCodeFactory = hashCodeFactory;
	}

	private static HashCodeFactory _hashCodeFactory;

}