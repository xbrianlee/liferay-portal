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

package com.liferay.taglib.aui;

import com.liferay.alloy.util.ReservedAttributeUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.taglib.aui.base.BaseComponentTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 */
public class ComponentTag extends BaseComponentTag {

	protected boolean isEventAttribute(String key) {
		if (StringUtil.startsWith(key, "after") ||
			StringUtil.startsWith(key, "on")) {

			return true;
		}

		return false;
	}

	protected boolean isValidAttribute(String key) {
		String excludeAttributes = getExcludeAttributes();

		if (excludeAttributes == null) {
			return true;
		}

		Set<String> excludeAttributesSet = SetUtil.fromArray(
			StringUtil.split(excludeAttributes));

		if (key.equals("dynamicAttributes") ||
			excludeAttributesSet.contains(key)) {

			return false;
		}

		return true;
	}

	protected void proccessAttributes(
		Map<String, Object> options, Map<String, Object> jsonifiedOptions) {

		Map<String, String> afterEventOptions = new HashMap<String, String>();
		Map<String, String> onEventOptions = new HashMap<String, String>();

		for (String key : options.keySet()) {
			if (!isValidAttribute(key)) {
				continue;
			}

			Object value = options.get(key);

			String originalKey = ReservedAttributeUtil.getOriginalName(
				getName(), key);

			if (value instanceof Map) {
				Map<String, Object> childOptions =
					new HashMap<String, Object>();

				proccessAttributes((Map<String, Object>)value, childOptions);

				jsonifiedOptions.put(originalKey, childOptions);

				continue;
			}

			if (isEventAttribute(key)) {
				processEventAttribute(
					key, String.valueOf(value), afterEventOptions,
					onEventOptions);
			}
			else {
				jsonifiedOptions.put(originalKey, value);
			}
		}

		if (!afterEventOptions.isEmpty()) {
			jsonifiedOptions.put("after", afterEventOptions);
		}

		if (!onEventOptions.isEmpty()) {
			jsonifiedOptions.put("on", onEventOptions);
		}
	}

	protected void processEventAttribute(
			String key, String value, Map<String, String> afterEventOptions,
			Map<String, String> onEventsOptions) {

		if (key.startsWith("after")) {
			String eventName = StringUtils.uncapitalize(key.substring(5));

			afterEventOptions.put(eventName, value);
		}
		else {
			String eventName = StringUtils.uncapitalize(key.substring(2));

			onEventsOptions.put(eventName, value);
		}
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		Map<String, Object> options = getOptions();

		Map<String, Object> jsonifiedOptions = new HashMap<String, Object>();

		proccessAttributes(options, jsonifiedOptions);

		super.setAttributes(request);

		setNamespacedAttribute(request, "jsonifiedOptions", jsonifiedOptions);
		setNamespacedAttribute(request, "options", options);
	}

}