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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.util.HtmlUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wraps a bean so that all strings returned from <code>@AutoEscape</code>
 * annotated methods are automatically HTML escaped.
 *
 * <p>
 * For a usage example see {@link
 * com.liferay.portlet.shopping.util.ShoppingUtil#getBreadcrumbs(
 * com.liferay.portlet.shopping.model.ShoppingCategory,
 * javax.servlet.jsp.PageContext, javax.portlet.RenderRequest,
 * javax.portlet.RenderResponse) ShoppingUtil.getBreadcrumbs} .
 * </p>
 *
 * @author Shuyang Zhou
 * @see    AutoEscape
 */
public class AutoEscapeBeanHandler implements InvocationHandler, Serializable {

	public AutoEscapeBeanHandler(Object bean) {
		_bean = (Serializable)bean;
	}

	public Object getBean() {
		return _bean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		String methodName = method.getName();

		if (methodName.startsWith("set")) {
			throw new IllegalAccessException(
				"Setter methods cannot be called on an escaped bean");
		}

		if (methodName.endsWith("isEscapedModel")) {
			return true;
		}
		else if (methodName.endsWith("toEscapedModel")) {
			return proxy;
		}

		Object result = null;

		try {
			result = method.invoke(_bean, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}

		if (method.getAnnotation(AutoEscape.class) != null) {
			result = HtmlUtil.escape((String)result);
		}

		return result;
	}

	private Serializable _bean;

}