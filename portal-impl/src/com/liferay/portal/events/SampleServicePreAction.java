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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class SampleServicePreAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		setSharedSessionAttributes(request);
	}

	public void setSharedSessionAttributes(HttpServletRequest request) {

		// Modify portal.properties property "session.shared.attributes". Make
		// sure that "TEST_SHARED_" is also one of the prefixed attributes that
		// will be shared across all portlets.

		HttpSession session = request.getSession();

		session.setAttribute("TEST_SHARED_HELLO", "world");
	}

}