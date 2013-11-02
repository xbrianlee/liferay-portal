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

import com.liferay.portal.kernel.configuration.Filter;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public interface Props {

	public boolean contains(String key);

	public String get(String key);

	public String get(String key, Filter filter);

	public String[] getArray(String key);

	public String[] getArray(String key, Filter filter);

	public Properties getProperties();

	public Properties getProperties(String prefix, boolean removePrefix);

}