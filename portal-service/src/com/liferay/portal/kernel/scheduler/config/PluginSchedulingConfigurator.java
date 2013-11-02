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

package com.liferay.portal.kernel.scheduler.config;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class PluginSchedulingConfigurator
	extends AbstractSchedulingConfigurator {

	@Override
	public void configure() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		String servletContextName =
			PortletClassLoaderUtil.getServletContextName();

		boolean forceSync = ProxyModeThreadLocal.isForceSync();

		ProxyModeThreadLocal.setForceSync(true);

		try {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			currentThread.setContextClassLoader(portalClassLoader);

			for (SchedulerEntry schedulerEntry : schedulerEntries) {
				try {
					SchedulerEngineHelperUtil.schedule(
						schedulerEntry, storageType, servletContextName,
						exceptionsMaxSize);
				}
				catch (Exception e) {
					_log.error("Unable to schedule " + schedulerEntry, e);
				}
			}
		}
		finally {
			ProxyModeThreadLocal.setForceSync(forceSync);

			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void destroy() {
		for (SchedulerEntry schedulerEntry : schedulerEntries) {
			try {
				SchedulerEngineHelperUtil.delete(schedulerEntry, storageType);
			}
			catch (Exception e) {
				_log.error("Unable to unschedule " + schedulerEntry, e);
			}
		}

		schedulerEntries.clear();
	}

	private static Log _log = LogFactoryUtil.getLog(
		PluginSchedulingConfigurator.class);

}