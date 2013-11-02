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
public class MultiValueMapFactoryUtil {

	public static MultiValueMap<?, ?> getMultiValueMap(int type) {
		return getMultiValueMapFactory().getMultiValueMap(type);
	}

	public static MultiValueMap<?, ?> getMultiValueMap(String propertyKey) {
		return getMultiValueMapFactory().getMultiValueMap(propertyKey);
	}

	public static MultiValueMapFactory getMultiValueMapFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			MultiValueMapFactoryUtil.class);

		return _multiValueMapFactory;
	}

	public void setMultiValueMapFactory(
		MultiValueMapFactory multiValueMapFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_multiValueMapFactory = multiValueMapFactory;
	}

	private static MultiValueMapFactory _multiValueMapFactory;

}