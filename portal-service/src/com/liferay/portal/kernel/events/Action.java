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

package com.liferay.portal.kernel.events;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class Action {

	public abstract void run(
			HttpServletRequest request, HttpServletResponse response)
		throws ActionException;

	public void run(RenderRequest renderRequest, RenderResponse renderResponse)
		throws ActionException {

		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				renderResponse);

			run(request, response);
		}
		catch (ActionException ae) {
			throw ae;
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

}