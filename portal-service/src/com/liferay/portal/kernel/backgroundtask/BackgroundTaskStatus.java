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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatus implements Serializable {

	public void clearAttributes() {
		_attributes.clear();
	}

	public Serializable getAttribute(String key) {
		return _attributes.get(key);
	}

	public Map<String, Serializable> getAttributes() {
		return Collections.unmodifiableMap(_attributes);
	}

	public String getAttributesJSON() {
		return JSONFactoryUtil.serialize(_attributes);
	}

	public void setAttribute(String key, Serializable value) {
		_attributes.put(key, value);
	}

	private Map<String, Serializable> _attributes =
		new ConcurrentHashMap<String, Serializable>();

}