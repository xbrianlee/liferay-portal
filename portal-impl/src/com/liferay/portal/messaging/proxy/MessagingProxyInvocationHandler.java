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

package com.liferay.portal.messaging.proxy;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.messaging.proxy.ProxyRequest;
import com.liferay.portal.spring.aop.InvocationHandlerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Shuyang Zhou
 */
public class MessagingProxyInvocationHandler implements InvocationHandler {

	public static InvocationHandlerFactory getInvocationHandlerFactory() {
		return _invocationHandlerFactory;
	}

	public MessagingProxyInvocationHandler(BaseProxyBean baseProxyBean) {
		_baseProxyBean = baseProxyBean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		ProxyRequest proxyRequest = new ProxyRequest(method, args);

		if (proxyRequest.isSynchronous() ||
			ProxyModeThreadLocal.isForceSync()) {

			return _baseProxyBean.synchronousSend(proxyRequest);
		}
		else {
			_baseProxyBean.send(proxyRequest);

			return null;
		}
	}

	private static InvocationHandlerFactory _invocationHandlerFactory =
		new InvocationHandlerFactory() {

		@Override
		public InvocationHandler createInvocationHandler(Object obj) {
			return new MessagingProxyInvocationHandler((BaseProxyBean)obj);
		}

	};

	private BaseProxyBean _baseProxyBean;

}