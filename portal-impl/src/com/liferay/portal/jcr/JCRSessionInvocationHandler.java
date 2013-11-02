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

package com.liferay.portal.jcr;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.memory.FinalizeAction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Binary;
import javax.jcr.Session;

/**
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class JCRSessionInvocationHandler
	implements FinalizeAction, InvocationHandler {

	public JCRSessionInvocationHandler(Session session) {
		_session = session;

		if (_log.isDebugEnabled()) {
			_log.debug("Starting session " + _session);
		}
	}

	@Override
	public void doFinalize() {
		for (Entry<String, Binary> entry : _binaries.entrySet()) {
			Binary binary = entry.getValue();

			binary.dispose();
		}

		_session.logout();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		String methodName = method.getName();

		if (methodName.equals("logout")) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skipping logout for session " + _session);
			}

			return null;
		}
		else if (methodName.equals("put")) {
			String key = (String)arguments[0];
			Binary binary = (Binary)arguments[1];

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Tracking binary " + key + " for session " + _session);
			}

			_binaries.put(key, binary);

			return null;
		}

		try {
			return method.invoke(_session, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getCause();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JCRSessionInvocationHandler.class);

	private Map<String, Binary> _binaries = new HashMap<String, Binary>();
	private Session _session;

}