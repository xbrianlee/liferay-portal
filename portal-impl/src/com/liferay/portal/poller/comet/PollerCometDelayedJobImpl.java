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

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.util.PropsValues;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Edward Han
 */
public class PollerCometDelayedJobImpl
	extends BaseMessageListener implements PollerCometDelayedJob {

	@Override
	public void addPollerCometDelayedTask(
		PollerCometDelayedTask pollerCometDelayedTask) {

		synchronized (_pollerCometDelayedTasks) {
			if (_timer == null) {
				_timer = new Timer(PollerCometDelayedJobImpl.class.getName());

				_timer.schedule(
					new PollerCometTimerTask(),
					PropsValues.POLLER_NOTIFICATIONS_TIMEOUT);
			}

			_pollerCometDelayedTasks.add(pollerCometDelayedTask);
		}
	}

	@Override
	protected synchronized void doReceive(Message message) throws Exception {
		synchronized (_pollerCometDelayedTasks) {
			for (PollerCometDelayedTask pollerCometDelayedTask :
					_pollerCometDelayedTasks) {

				try {
					pollerCometDelayedTask.executeTask();
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to do task" + e);
					}
				}
			}

			_pollerCometDelayedTasks.clear();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerCometDelayedJobImpl.class);

	private List<PollerCometDelayedTask> _pollerCometDelayedTasks =
		new LinkedList<PollerCometDelayedTask>();
	private Timer _timer;

	private class PollerCometTimerTask extends TimerTask {

		@Override
		public void run() {
			synchronized (_pollerCometDelayedTasks) {
				for (PollerCometDelayedTask pollerCometDelayedTask :
						_pollerCometDelayedTasks) {

					try {
						pollerCometDelayedTask.executeTask();
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to do task" + e);
						}
					}
				}

				_pollerCometDelayedTasks.clear();
			}
		}
	}

}