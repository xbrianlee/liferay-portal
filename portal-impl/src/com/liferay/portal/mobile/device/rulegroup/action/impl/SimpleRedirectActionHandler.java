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

package com.liferay.portal.mobile.device.rulegroup.action.impl;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public class SimpleRedirectActionHandler extends BaseRedirectActionHandler {

	public static String getHandlerType() {
		return SimpleRedirectActionHandler.class.getName();
	}

	@Override
	public Collection<String> getPropertyNames() {
		return _propertyNames;
	}

	@Override
	public String getType() {
		return getHandlerType();
	}

	@Override
	protected String getURL(
		MDRAction mdrAction, HttpServletRequest request,
		HttpServletResponse response) {

		UnicodeProperties typeSettingsProperties =
			mdrAction.getTypeSettingsProperties();

		return GetterUtil.getString(typeSettingsProperties.getProperty("url"));
	}

	private static Collection<String> _propertyNames;

	static {
		_propertyNames = new ArrayList<String>(1);

		_propertyNames.add("url");

		_propertyNames = Collections.unmodifiableCollection(_propertyNames);
	}

}