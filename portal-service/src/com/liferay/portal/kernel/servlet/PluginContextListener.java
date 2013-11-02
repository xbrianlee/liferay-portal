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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.ClassLoaderPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class PluginContextListener
	extends BasePortalLifecycle
	implements ServletContextAttributeListener, ServletContextListener {

	public static final String PLUGIN_CLASS_LOADER = "PLUGIN_CLASS_LOADER";

	@Override
	public void attributeAdded(
		ServletContextAttributeEvent servletContextAttributeEvent) {

		String name = servletContextAttributeEvent.getName();

		if (_addedPluginClassLoader && name.equals(PLUGIN_CLASS_LOADER) &&
			(servletContextAttributeEvent.getValue() != pluginClassLoader)) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"Preventing the addition of another plugin class loader");
			}

			servletContext.setAttribute(PLUGIN_CLASS_LOADER, pluginClassLoader);
		}
		else if (!_addedPluginClassLoader && name.equals(PLUGIN_CLASS_LOADER)) {
			_addedPluginClassLoader = true;
		}
	}

	@Override
	public void attributeRemoved(
		ServletContextAttributeEvent servletContextAttributeEvent) {

		String name = servletContextAttributeEvent.getName();

		if (_addedPluginClassLoader && name.equals(PLUGIN_CLASS_LOADER)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Preventing the removal of the plugin class loader");
			}

			servletContext.setAttribute(PLUGIN_CLASS_LOADER, pluginClassLoader);
		}
	}

	@Override
	public void attributeReplaced(
		ServletContextAttributeEvent servletContextAttributeEvent) {

		String name = servletContextAttributeEvent.getName();

		if (_addedPluginClassLoader && name.equals(PLUGIN_CLASS_LOADER)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Preventing the replacement of the plugin class loader");
			}

			servletContext.removeAttribute(PLUGIN_CLASS_LOADER);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		ClassLoaderPool.unregister(servletContext.getServletContextName());

		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		servletContext = servletContextEvent.getServletContext();

		Thread currentThread = Thread.currentThread();

		pluginClassLoader = currentThread.getContextClassLoader();

		ClassLoaderPool.register(
			servletContext.getServletContextName(), pluginClassLoader);

		servletContext.setAttribute(PLUGIN_CLASS_LOADER, pluginClassLoader);

		ServletContextPool.put(
			servletContext.getServletContextName(), servletContext);

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		PluginContextLifecycleThreadLocal.setDestroying(true);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != pluginClassLoader) {
			currentThread.setContextClassLoader(pluginClassLoader);
		}

		try {
			fireUndeployEvent();
		}
		finally {
			PluginContextLifecycleThreadLocal.setDestroying(false);

			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	@Override
	protected void doPortalInit() throws Exception {
		PluginContextLifecycleThreadLocal.setInitializing(true);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != pluginClassLoader) {
			currentThread.setContextClassLoader(pluginClassLoader);
		}

		try {
			fireDeployEvent();
		}
		finally {
			PluginContextLifecycleThreadLocal.setInitializing(false);

			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	protected void fireDeployEvent() {
		HotDeployUtil.fireDeployEvent(
			new HotDeployEvent(servletContext, pluginClassLoader));
	}

	protected void fireUndeployEvent() {
		HotDeployUtil.fireUndeployEvent(
			new HotDeployEvent(servletContext, pluginClassLoader));
	}

	protected ClassLoader pluginClassLoader;
	protected ServletContext servletContext;

	private static Log _log = LogFactoryUtil.getLog(
		PluginContextListener.class);

	private boolean _addedPluginClassLoader;

}