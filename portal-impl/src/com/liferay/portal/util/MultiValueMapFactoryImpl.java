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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MultiValueMap;
import com.liferay.portal.kernel.util.MultiValueMapFactory;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class MultiValueMapFactoryImpl implements MultiValueMapFactory {

	@Override
	public MultiValueMap<?, ?> getMultiValueMap(int type) {
		if (type == MultiValueMapFactory.FILE) {
			return new FileMultiValueMap<Serializable, Serializable>();
		}
		else {
			return new MemoryMultiValueMap<Serializable, Serializable>();
		}
	}

	@Override
	public MultiValueMap<?, ?> getMultiValueMap(String propertyKey) {
		int type = GetterUtil.getInteger(PropsUtil.get(propertyKey));

		if (_log.isInfoEnabled()) {
			_log.info("Using type " + type + " for " + propertyKey);
		}

		return getMultiValueMap(type);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MultiValueMapFactoryImpl.class);

}