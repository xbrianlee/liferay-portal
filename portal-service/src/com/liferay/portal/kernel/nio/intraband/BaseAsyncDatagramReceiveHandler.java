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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.executor.PortalExecutorManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.concurrent.Executor;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseAsyncDatagramReceiveHandler
	implements DatagramReceiveHandler {

	public BaseAsyncDatagramReceiveHandler() {
		Class<? extends BaseAsyncDatagramReceiveHandler> clazz = getClass();

		_executor = PortalExecutorManagerUtil.getPortalExecutor(
			clazz.getName());
	}

	@Override
	public void receive(
		RegistrationReference registrationReference, Datagram datagram) {

		_executor.execute(new DispatchJob(registrationReference, datagram));
	}

	protected abstract void doReceive(
			RegistrationReference registrationReference, Datagram datagram)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(
		BaseAsyncDatagramReceiveHandler.class);

	private final Executor _executor;

	private class DispatchJob implements Runnable {

		public DispatchJob(
			RegistrationReference registrationReference, Datagram datagram) {

			_datagram = datagram;
			_registrationReference = registrationReference;
		}

		@Override
		public void run() {
			try {
				doReceive(_registrationReference, _datagram);
			}
			catch (Exception e) {
				_log.error("Unable to dispatch", e);
			}
		}

		private final Datagram _datagram;
		private final RegistrationReference _registrationReference;

	}

}