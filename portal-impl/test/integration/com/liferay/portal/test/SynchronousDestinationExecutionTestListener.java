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

package com.liferay.portal.test;

import com.liferay.portal.kernel.annotation.AnnotationLocator;
import com.liferay.portal.kernel.messaging.BaseAsyncDestination;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.SynchronousDestination;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.test.AbstractExecutionTestListener;
import com.liferay.portal.kernel.test.TestContext;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class SynchronousDestinationExecutionTestListener
	extends AbstractExecutionTestListener {

	@Override
	public void runAfterClass(TestContext testContext) {
		_classSyncHandler.restorePreviousSync();
	}

	@Override
	public void runAfterTest(TestContext testContext) {
		_methodSyncHandler.restorePreviousSync();
	}

	@Override
	public void runBeforeClass(TestContext testContext) {
		Class<?> testClass = testContext.getClazz();

		Sync sync = AnnotationLocator.locate(testClass, Sync.class);

		_classSyncHandler.setSync(sync);
		_classSyncHandler.setForceSync(ProxyModeThreadLocal.isForceSync());

		_classSyncHandler.enableSync();
	}

	@Override
	public void runBeforeTest(TestContext testContext) {
		Method method = testContext.getMethod();
		Class<?> testClass = testContext.getClazz();

		Sync sync = AnnotationLocator.locate(method, testClass, Sync.class);

		_methodSyncHandler.setForceSync(ProxyModeThreadLocal.isForceSync());
		_methodSyncHandler.setSync(sync);

		_methodSyncHandler.enableSync();
	}

	private List<Destination> _asyncServiceDestinations =
		new ArrayList<Destination>();
	private SyncHandler _classSyncHandler = new SyncHandler();
	private SyncHandler _methodSyncHandler = new SyncHandler();

	private class SyncHandler {

		public void enableSync() {
			if (_sync == null) {
				return;
			}

			ProxyModeThreadLocal.setForceSync(true);

			replaceDestination(DestinationNames.ASYNC_SERVICE);
			replaceDestination(DestinationNames.BACKGROUND_TASK);
			replaceDestination(DestinationNames.SUBSCRIPTION_SENDER);
		}

		protected void replaceDestination(String destinationName) {
			MessageBus messageBus = MessageBusUtil.getMessageBus();

			Destination destination = messageBus.getDestination(
				destinationName);

			if (destination instanceof BaseAsyncDestination) {
				_asyncServiceDestinations.add(destination);

				SynchronousDestination synchronousDestination =
					new SynchronousDestination();

				synchronousDestination.setName(destinationName);

				messageBus.replace(synchronousDestination);
			}
		}

		public void restorePreviousSync() {
			if (_sync == null) {
				return;
			}

			ProxyModeThreadLocal.setForceSync(_forceSync);

			if (!_asyncServiceDestinations.isEmpty()) {
				MessageBus messageBus = MessageBusUtil.getMessageBus();

				for (Destination destination : _asyncServiceDestinations) {
					messageBus.replace(destination);
				}

				_asyncServiceDestinations.clear();
			}
		}

		public void setForceSync(boolean forceSync) {
			_forceSync = forceSync;
		}

		public void setSync(Sync sync) {
			_sync = sync;
		}

		private boolean _forceSync;
		private Sync _sync;

	}

}