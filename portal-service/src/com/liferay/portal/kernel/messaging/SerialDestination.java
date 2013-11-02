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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;

import java.util.Set;

/**
 * <p>
 * Destination that delivers a message to a list of message listeners one at a
 * time.
 * </p>
 *
 * @author Michael C. Han
 */
public class SerialDestination extends BaseAsyncDestination {

	public SerialDestination() {
		super();

		setWorkersCoreSize(_WORKERS_CORE_SIZE);
		setWorkersMaxSize(_WORKERS_MAX_SIZE);
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public SerialDestination(String name) {
		super(name, _WORKERS_CORE_SIZE, _WORKERS_MAX_SIZE);
	}

	@Override
	protected void dispatch(
		final Set<MessageListener> messageListeners, final Message message) {

		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

		Runnable runnable = new MessageRunnable(message) {

			@Override
			public void run() {
				try {
					populateThreadLocalsFromMessage(message);

					for (MessageListener messageListener : messageListeners) {
						try {
							messageListener.receive(message);
						}
						catch (MessageListenerException mle) {
							_log.error(
								"Unable to process message " + message, mle);
						}
					}
				}
				finally {
					ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}
			}

		};

		threadPoolExecutor.execute(runnable);
	}

	private static final int _WORKERS_CORE_SIZE = 1;

	private static final int _WORKERS_MAX_SIZE = 1;

	private static Log _log = LogFactoryUtil.getLog(SerialDestination.class);

}