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

package com.liferay.portlet.portletconfiguration.util;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.filter.ActionRequestWrapper;

/**
 * @author Raymond Augé
 */
public class ConfigurationActionRequest
	extends ActionRequestWrapper implements ConfigurationPortletRequest {

	public ConfigurationActionRequest(
		ActionRequest actionRequest, PortletPreferences portletPreferences) {

		super(actionRequest);

		_portletPreferences = portletPreferences;
	}

	@Override
	public PortletPreferences getPreferences() {
		return _portletPreferences;
	}

	private PortletPreferences _portletPreferences;

}