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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.util.WebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class ClearRenderParametersAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {

		// Some users are confused by the behavior stated in the JSR 168 spec
		// that render parameters are saved across requests. Set this class to
		// always clear render parameters to please those users. You can also
		// modify the "layout.remember.request.window.state.maximized" property
		// in portal.properties to disable the remembering of window states
		// across requests.

		HttpSession session = request.getSession();

		Map<Long, Map<String, Map<String, String[]>>> renderParametersPool =
			(Map<Long, Map<String, Map<String, String[]>>>)session.getAttribute(
				WebKeys.PORTLET_RENDER_PARAMETERS);

		if (renderParametersPool != null) {
			renderParametersPool.clear();
		}
	}

}