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

package com.liferay.portal.bean;

import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderPool;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PortalUtil;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class IdentifiableBeanInvokerUtil {

	public static MethodHandler createMethodHandler(
		MethodInvocation methodInvocation) {

		MethodHandler methodHandler = new MethodHandler(
			methodInvocation.getMethod(), methodInvocation.getArguments());

		String threadContextServletContextName = ClassLoaderPool.getContextName(
			ClassLoaderUtil.getContextClassLoader());

		IdentifiableBean identifiableBean =
			(IdentifiableBean)methodInvocation.getThis();

		Class<?> identifiableBeanClass = identifiableBean.getClass();

		String identifiableBeanServletContextName =
			ClassLoaderPool.getContextName(
				identifiableBeanClass.getClassLoader());

		String beanIdentifier = identifiableBean.getBeanIdentifier();

		return new MethodHandler(
			_invokeMethodKey, methodHandler, threadContextServletContextName,
			identifiableBeanServletContextName, beanIdentifier);
	}

	@SuppressWarnings("unused")
	private static Object _invoke(
			MethodHandler methodHandler, String threadContextServletContextName,
			String identifiableBeanServletContextName, String beanIdentifier)
		throws Exception {

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(
			threadContextServletContextName);

		ClassLoaderUtil.setContextClassLoader(classLoader);

		try {
			Object bean = null;

			if (identifiableBeanServletContextName.equals(
					PortalUtil.getServletContextName())) {

				bean = PortalBeanLocatorUtil.locate(beanIdentifier);
			}
			else {
				bean = PortletBeanLocatorUtil.locate(
					identifiableBeanServletContextName, beanIdentifier);
			}

			return methodHandler.invoke(bean);
		}
		finally {
			ClassLoaderUtil.setContextClassLoader(contextClassLoader);
		}
	}

	private static MethodKey _invokeMethodKey = new MethodKey(
		IdentifiableBeanInvokerUtil.class, "_invoke", MethodHandler.class,
		String.class, String.class, String.class);

}