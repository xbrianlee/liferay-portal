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

package com.liferay.portal.cluster;

import com.liferay.portal.bean.IdentifiableBeanInvokerUtil;
import com.liferay.portal.kernel.cluster.ClusterInvokeAcceptor;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import java.io.Serializable;

import java.lang.reflect.Constructor;

import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class ClusterableInvokerUtil {

	public static MethodHandler createMethodHandler(
		Class<? extends ClusterInvokeAcceptor> clusterInvokeAcceptorClass,
		MethodInvocation methodInvocation) {

		MethodHandler methodHandler =
			IdentifiableBeanInvokerUtil.createMethodHandler(methodInvocation);

		Map<String, Serializable> context =
			ClusterableContextThreadLocal.collectThreadLocalContext();

		if (clusterInvokeAcceptorClass == ClusterInvokeAcceptor.class) {
			clusterInvokeAcceptorClass = null;
		}

		return new MethodHandler(
			_invokeMethodKey, methodHandler, clusterInvokeAcceptorClass,
			context);
	}

	@SuppressWarnings("unused")
	private static Object _invoke(
			MethodHandler methodHandler,
			Class<? extends ClusterInvokeAcceptor> clusterInvokeAcceptorClass,
			Map<String, Serializable> context)
		throws Exception {

		if (clusterInvokeAcceptorClass != null) {
			Constructor<? extends ClusterInvokeAcceptor> constructor =
				clusterInvokeAcceptorClass.getDeclaredConstructor();

			if (!constructor.isAccessible()) {
				constructor.setAccessible(true);
			}

			ClusterInvokeAcceptor clusterInvokeAcceptor =
				constructor.newInstance();

			if (!clusterInvokeAcceptor.accept(context)) {
				return null;
			}
		}

		return methodHandler.invoke(false);
	}

	private static MethodKey _invokeMethodKey = new MethodKey(
		ClusterableInvokerUtil.class, "_invoke", MethodHandler.class,
		Class.class, Map.class);

}