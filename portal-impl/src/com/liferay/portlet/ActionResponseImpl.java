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

package com.liferay.portlet;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionResponseImpl
	extends StateAwareResponseImpl implements ActionResponse {

	@Override
	public String getLifecycle() {
		return PortletRequest.ACTION_PHASE;
	}

	@Override
	public void sendRedirect(String location) {
		if ((location == null) ||
			(!location.startsWith("/") && !location.contains("://") &&
			 !location.startsWith("wsrp_rewrite?"))) {

			throw new IllegalArgumentException(
				location + " is not a valid redirect");
		}

		// This is needed because app servers will try to prepend a host if they
		// see an invalid URL

		if (location.startsWith("wsrp_rewrite?")) {
			location = "http://wsrp-rewrite-holder?" + location;
		}

		if (isCalledSetRenderParameter()) {
			throw new IllegalStateException(
				"Set render parameter has already been called");
		}

		setRedirectLocation(location);
	}

	@Override
	public void sendRedirect(String location, String renderUrlParamName) {
	}

}