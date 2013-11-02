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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public class PortalBeanLocatorUtil {

	public static BeanLocator getBeanLocator() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortalBeanLocatorUtil.class);

		return _beanLocator;
	}

	public static <T> Map<String, T> locate(Class<T> clazz) {
		BeanLocator beanLocator = getBeanLocator();

		if (beanLocator == null) {
			_log.error("BeanLocator is null");

			throw new BeanLocatorException("BeanLocator has not been set");
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = _pacl.getContextClassLoader(
			currentThread);

		ClassLoader beanClassLoader = _pacl.getBeanLocatorClassLoader(
			beanLocator);

		try {
			if (contextClassLoader != beanClassLoader) {
				_pacl.setContextClassLoader(currentThread, beanClassLoader);
			}

			return beanLocator.locate(clazz);
		}
		finally {
			if (contextClassLoader != beanClassLoader) {
				_pacl.setContextClassLoader(currentThread, contextClassLoader);
			}
		}
	}

	public static Object locate(String name) throws BeanLocatorException {
		BeanLocator beanLocator = getBeanLocator();

		if (beanLocator == null) {
			_log.error("BeanLocator is null");

			Thread.dumpStack();

			if (_log.isDebugEnabled()) {
				Exception e = new Exception();

				_log.debug(e, e);
			}

			throw new BeanLocatorException("BeanLocator has not been set");
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = _pacl.getContextClassLoader(
			currentThread);

		ClassLoader beanClassLoader = _pacl.getBeanLocatorClassLoader(
			beanLocator);

		try {
			if (contextClassLoader != beanClassLoader) {
				_pacl.setContextClassLoader(currentThread, beanClassLoader);
			}

			return beanLocator.locate(name);
		}
		finally {
			if (contextClassLoader != beanClassLoader) {
				_pacl.setContextClassLoader(currentThread, contextClassLoader);
			}
		}
	}

	public static void reset() {
		setBeanLocator(null);
	}

	public static void setBeanLocator(BeanLocator beanLocator) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortalBeanLocatorUtil.class);

		if (_log.isDebugEnabled()) {
			if (beanLocator == null) {
				_log.debug("Setting BeanLocator " + beanLocator);
			}
			else {
				_log.debug("Setting BeanLocator " + beanLocator.hashCode());
			}
		}

		_beanLocator = beanLocator;
	}

	public static interface PACL {

		public ClassLoader getBeanLocatorClassLoader(BeanLocator beanLocator);

		public ClassLoader getContextClassLoader(Thread currentThread);

		public void setContextClassLoader(
			Thread currentThread, ClassLoader classLoader);

	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalBeanLocatorUtil.class);

	private static BeanLocator _beanLocator;
	private static PACL _pacl = new NoPACL();

	private static class NoPACL implements PACL {

		@Override
		public ClassLoader getBeanLocatorClassLoader(BeanLocator beanLocator) {
			return beanLocator.getClassLoader();
		}

		@Override
		public ClassLoader getContextClassLoader(Thread currentThread) {
			return currentThread.getContextClassLoader();
		}

		@Override
		public void setContextClassLoader(
			Thread currentThread, ClassLoader classLoader) {

			currentThread.setContextClassLoader(classLoader);
		}

	}

}