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

package com.liferay.portal.kernel.scripting;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Shuyang Zhou
 */
public class ScriptingHelperUtil {

	public static Map<String, Object> getPortletObjects(
		PortletConfig portletConfig, PortletContext portletContext,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Map<String, Object> portletObjects = new HashMap<String, Object>();

		portletObjects.put("portletConfig", portletConfig);
		portletObjects.put("portletContext", portletContext);
		portletObjects.put("preferences", portletRequest.getPreferences());

		if (portletRequest instanceof ActionRequest) {
			portletObjects.put("actionRequest", portletRequest);
		}
		else if (portletRequest instanceof RenderRequest) {
			portletObjects.put("renderRequest", portletRequest);
		}
		else if (portletRequest instanceof ResourceRequest) {
			portletObjects.put("resourceRequest", portletRequest);
		}
		else {
			portletObjects.put("portletRequest", portletRequest);
		}

		if (portletResponse instanceof ActionResponse) {
			portletObjects.put("actionResponse", portletResponse);
		}
		else if (portletResponse instanceof RenderResponse) {
			portletObjects.put("renderResponse", portletResponse);
		}
		else if (portletResponse instanceof ResourceResponse) {
			portletObjects.put("resourceResponse", portletResponse);
		}
		else {
			portletObjects.put("portletResponse", portletResponse);
		}

		portletObjects.put(
			"userInfo", portletRequest.getAttribute(PortletRequest.USER_INFO));

		return portletObjects;
	}

}