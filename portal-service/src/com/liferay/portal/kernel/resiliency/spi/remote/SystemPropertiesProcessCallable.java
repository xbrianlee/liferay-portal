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

package com.liferay.portal.kernel.resiliency.spi.remote;

import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Shuyang Zhou
 */
public class SystemPropertiesProcessCallable
	implements ProcessCallable<Serializable> {

	public SystemPropertiesProcessCallable(Map<String, String> propertiesMap) {
		_propertiesMap = new HashMap<String, String>(propertiesMap);
	}

	@Override
	public Serializable call() {
		Properties systemProperties = System.getProperties();

		systemProperties.putAll(_propertiesMap);

		return null;
	}

	private static final long serialVersionUID = 1L;

	private final Map<String, String> _propertiesMap;

}