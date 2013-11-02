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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.Props;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class PropsImpl implements Props {

	@Override
	public boolean contains(String key) {
		return PropsUtil.contains(key);
	}

	@Override
	public String get(String key) {
		return PropsUtil.get(key);
	}

	@Override
	public String get(String key, Filter filter) {
		return PropsUtil.get(key, filter);
	}

	@Override
	public String[] getArray(String key) {
		return PropsUtil.getArray(key);
	}

	@Override
	public String[] getArray(String key, Filter filter) {
		return PropsUtil.getArray(key, filter);
	}

	@Override
	public Properties getProperties() {
		return PropsUtil.getProperties();
	}

	@Override
	public Properties getProperties(String prefix, boolean removePrefix) {
		return PropsUtil.getProperties(prefix, removePrefix);
	}

}