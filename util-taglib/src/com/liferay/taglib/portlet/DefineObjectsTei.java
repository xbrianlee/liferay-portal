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

package com.liferay.taglib.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.SearchContainerReference;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Brian Wing Shun Chan
 */
public class DefineObjectsTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		return _variableInfo;
	}

	private static VariableInfo[] _variableInfo = new VariableInfo[] {
		new VariableInfo(
			"actionRequest", ActionRequest.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"actionResponse", ActionResponse.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"eventRequest", EventRequest.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"eventResponse", EventResponse.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"liferayPortletRequest", LiferayPortletRequest.class.getName(),
			true, VariableInfo.AT_END),
		new VariableInfo(
			"liferayPortletResponse", LiferayPortletResponse.class.getName(),
			true, VariableInfo.AT_END),
		new VariableInfo(
			"portletConfig", PortletConfig.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"portletName", String.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"portletPreferences", PortletPreferences.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"portletPreferencesValues", Map.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"portletSession", PortletSession.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"portletSessionScope", Map.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"renderRequest", RenderRequest.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"renderResponse", RenderResponse.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"resourceRequest", ResourceRequest.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"resourceResponse", ResourceResponse.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"searchContainerReference",
			SearchContainerReference.class.getName(), true, VariableInfo.AT_END)
	};

}