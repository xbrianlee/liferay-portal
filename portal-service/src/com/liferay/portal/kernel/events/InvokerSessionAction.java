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

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class InvokerSessionAction extends SessionAction {

	public InvokerSessionAction(SessionAction sessionAction) {
		this(sessionAction, Thread.currentThread().getContextClassLoader());
	}

	public InvokerSessionAction(
		SessionAction sessionAction, ClassLoader classLoader) {

		_sessionAction = sessionAction;
		_classLoader = classLoader;
	}

	@Override
	public void run(HttpSession session) throws ActionException {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(_classLoader);

		try {
			_sessionAction.run(session);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private ClassLoader _classLoader;
	private SessionAction _sessionAction;

}