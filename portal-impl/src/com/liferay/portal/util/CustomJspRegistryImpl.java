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

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ryan Park
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class CustomJspRegistryImpl implements CustomJspRegistry {

	public CustomJspRegistryImpl() {
		_servletContextNames = new ConcurrentHashMap<String, String>();
	}

	@Override
	public String getCustomJspFileName(
		String servletContextName, String fileName) {

		int pos = fileName.lastIndexOf(CharPool.PERIOD);

		if (pos == -1) {
			return fileName.concat(StringPool.PERIOD).concat(
				servletContextName);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(fileName.substring(0, pos));
		sb.append(CharPool.PERIOD);
		sb.append(servletContextName);
		sb.append(fileName.substring(pos));

		return sb.toString();
	}

	@Override
	public String getDisplayName(String servletContextName) {
		return _servletContextNames.get(servletContextName);
	}

	@Override
	public Set<String> getServletContextNames() {
		return _servletContextNames.keySet();
	}

	@Override
	public void registerServletContextName(
		String servletContextName, String displayName) {

		_servletContextNames.put(servletContextName, displayName);
	}

	@Override
	public void unregisterServletContextName(String servletContextName) {
		_servletContextNames.remove(servletContextName);
	}

	private Map<String, String> _servletContextNames;

}