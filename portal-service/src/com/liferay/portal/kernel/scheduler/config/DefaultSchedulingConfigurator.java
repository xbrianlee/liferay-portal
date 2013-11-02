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
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.PortalLifecycle;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class DefaultSchedulingConfigurator
	extends AbstractSchedulingConfigurator {

	@Override
	public void configure() {
		if (schedulerEntries.isEmpty()) {
			return;
		}

		SchedulingConfiguratorLifecycle schedulingConfiguratorLifecycle =
			new SchedulingConfiguratorLifecycle();

		schedulingConfiguratorLifecycle.registerPortalLifecycle(
			PortalLifecycle.METHOD_INIT);
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultSchedulingConfigurator.class);

	private class SchedulingConfiguratorLifecycle extends BasePortalLifecycle {

		@Override
		protected void doPortalDestroy() throws Exception {
		}

		@Override
		protected void doPortalInit() throws Exception {
			for (SchedulerEntry schedulerEntry : schedulerEntries) {
				try {
					SchedulerEngineHelperUtil.schedule(
						schedulerEntry, storageType, null, exceptionsMaxSize);
				}
				catch (Exception e) {
					_log.error("Unable to schedule " + schedulerEntry, e);
				}
			}
		}

	}

}