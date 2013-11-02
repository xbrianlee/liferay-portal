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

package com.liferay.portal.security.lang;

import com.liferay.portal.kernel.security.pacl.NotPrivileged;
import com.liferay.portal.kernel.security.pacl.permission.PortalServicePermission;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class DoPrivilegedHandler
	implements DoPrivilegedBean, InvocationHandler {

	public DoPrivilegedHandler(Object bean) {
		_bean = bean;

		_initNotPrivilegedMethods();
	}

	@Override
	public Object getActualBean() {
		return _bean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			return doInvoke(proxy, method, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected Object doInvoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		Class<?> methodDeclaringClass = method.getDeclaringClass();
		String methodName = method.getName();

		if (methodDeclaringClass.equals(DoPrivilegedBean.class) &&
			methodName.equals("getActualBean")) {

			return _bean;
		}
		else if (methodDeclaringClass.equals(Object.class) &&
				 methodName.equals("equals")) {

			Object object = arguments[0];

			if (object instanceof DoPrivilegedBean) {
				DoPrivilegedBean doPrivilegedBean = (DoPrivilegedBean)object;

				object = doPrivilegedBean.getActualBean();
			}

			return _bean.equals(object);
		}
		else if (_isNotPrivileged(method)) {
			return method.invoke(_bean, arguments);
		}

		String declaringClassName = methodDeclaringClass.getName();

		if (declaringClassName.endsWith(_BEAN_NAME_SUFFIX_FINDER) ||
			declaringClassName.endsWith(_BEAN_NAME_SUFFIX_PERSISTENCE)) {

			PortalServicePermission.checkService(_bean, method, arguments);
		}

		try {
			return AccessController.doPrivileged(
				new InvokePrivilegedExceptionAction(_bean, method, arguments));
		}
		catch (PrivilegedActionException pae) {
			Exception e = pae.getException();

			throw e.getCause();
		}
	}

	private void _initNotPrivilegedMethods() {
		_notPrivilegedMethods = new ArrayList<MethodKey>();

		Class<?> beanClass = _bean.getClass();

		Method[] methods = beanClass.getMethods();

		for (Method method : methods) {
			NotPrivileged notPrivileged = method.getAnnotation(
				NotPrivileged.class);

			if (notPrivileged == null) {
				continue;
			}

			_notPrivilegedMethods.add(new MethodKey(method));
		}

		_notPrivilegedMethods = Collections.unmodifiableList(
			_notPrivilegedMethods);

		if (!_notPrivilegedMethods.isEmpty()) {
			_hasNotPrivilegedMethods = true;
		}
	}

	private boolean _isNotPrivileged(Method method) {
		if (_hasNotPrivilegedMethods &&
			_notPrivilegedMethods.contains(new MethodKey(method))) {

			return true;
		}

		return false;
	}

	private static final String _BEAN_NAME_SUFFIX_FINDER = "Finder";

	private static final String _BEAN_NAME_SUFFIX_PERSISTENCE = "Persistence";

	private Object _bean;
	private boolean _hasNotPrivilegedMethods = false;
	private List<MethodKey> _notPrivilegedMethods;

	private class InvokePrivilegedExceptionAction
		implements PrivilegedExceptionAction<Object> {

		public InvokePrivilegedExceptionAction(
			Object bean, Method method, Object[] arguments) {

			_bean = bean;
			_method = method;
			_arguments = arguments;
		}

		@Override
		public Object run() throws Exception {
			return _method.invoke(_bean, _arguments);
		}

		private Object[] _arguments;
		private Object _bean;
		private Method _method;

	}

	/**
	 * This is not the typical MethodKey. It matches on overload conditions
	 * rather than on equality. The key in the cache should always be an
	 * implementation, while the method being checked will be from an interface,
	 * therefore the <code>equals</code> check is not symmetrical.
	 */
	private class MethodKey {

		public MethodKey(Method method) {
			_declaringClass = method.getDeclaringClass();
			_methodName = method.getName();
			_parameterTypes = method.getParameterTypes();
		}

		@Override
		public boolean equals(Object obj) {
			MethodKey methodKey = (MethodKey)obj;

			// Note again that this check is not symmetrical. This method key's
			// class must be assignable from the cached method key's class

			if (_declaringClass.isAssignableFrom(methodKey._declaringClass) &&
				Validator.equals(_methodName, methodKey._methodName) &&
				Arrays.equals(_parameterTypes, methodKey._parameterTypes)) {

				return true;
			}

			return false;
		}

		private Class<?> _declaringClass;
		private String _methodName;
		private Class<?>[] _parameterTypes;

	}

}