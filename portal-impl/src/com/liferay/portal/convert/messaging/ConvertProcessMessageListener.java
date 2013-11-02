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

package com.liferay.portal.convert.messaging;

import com.liferay.portal.convert.ConvertProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.util.ShutdownUtil;

/**
 * @author Alexander Chow
 */
public class ConvertProcessMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.fatal("Unable to process message " + message, e);

			ShutdownUtil.shutdown(0);
		}
	}

	protected void doReceive(Message message) throws Exception {
		String className = (String)message.getPayload();

		ConvertProcess convertProcess = (ConvertProcess)InstancePool.get(
			className);

		convertProcess.convert();
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConvertProcessMessageListener.class);

}