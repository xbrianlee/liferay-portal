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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.FilterChain;

/**
 * @author Michael Young
 */
public class PortletFilterUtil {

	public static void doFilter(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String lifecycle, FilterChain filterChain)
		throws IOException, PortletException {

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			ActionRequest actionRequest = (ActionRequest)portletRequest;
			ActionResponse actionResponse = (ActionResponse)portletResponse;

			filterChain.doFilter(actionRequest, actionResponse);

			if (ParamUtil.getBoolean(actionRequest, "wsrp")) {
				actionResponse.setRenderParameter("wsrp", "1");
			}
		}
		else if (lifecycle.equals(PortletRequest.EVENT_PHASE)) {
			EventRequest eventRequest = (EventRequest)portletRequest;
			EventResponse eventResponse = (EventResponse)portletResponse;

			filterChain.doFilter(eventRequest, eventResponse);
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			RenderRequest renderRequest = (RenderRequest)portletRequest;
			RenderResponse renderResponse = (RenderResponse)portletResponse;

			filterChain.doFilter(renderRequest, renderResponse);
		}
		else if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
			ResourceRequest resourceRequest = (ResourceRequest)portletRequest;
			ResourceResponse resourceResponse =
				(ResourceResponse)portletResponse;

			filterChain.doFilter(resourceRequest, resourceResponse);
		}
	}

}