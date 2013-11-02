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

package com.liferay.portal.kernel.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.servlet.PersistentHttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 */
public class CompoundSessionIdServletRequest
	extends PersistentHttpServletRequestWrapper {

	public CompoundSessionIdServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public HttpSession getSession() {
		HttpSession session = super.getSession();

		return new CompoundSessionIdHttpSession(session);
	}

	@Override
	public HttpSession getSession(boolean create) {
		HttpSession session = super.getSession(create);

		if (session == null) {
			return session;
		}

		return new CompoundSessionIdHttpSession(session);
	}

}