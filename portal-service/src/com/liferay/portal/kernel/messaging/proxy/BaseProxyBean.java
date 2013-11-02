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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationSynchronousMessageSender;

import java.util.Map;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public abstract class BaseProxyBean {

	public void send(ProxyRequest proxyRequest) {
		_singleDestinationMessageSender.send(buildMessage(proxyRequest));
	}

	public void setSingleDestinationMessageSender(
		SingleDestinationMessageSender singleDestinationMessageSender) {

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	public void setSingleDestinationSynchronousMessageSender(
		SingleDestinationSynchronousMessageSender
		singleDestinationSynchronousMessageSender) {

		_singleDestinationSynchronousMessageSender =
			singleDestinationSynchronousMessageSender;
	}

	public Object synchronousSend(ProxyRequest proxyRequest) throws Exception {
		ProxyResponse proxyResponse =
			(ProxyResponse)_singleDestinationSynchronousMessageSender.send(
				buildMessage(proxyRequest));

		if (proxyResponse == null) {
			return proxyRequest.execute(this);
		}
		else if (proxyResponse.hasError()) {
			throw proxyResponse.getException();
		}
		else {
			return proxyResponse.getResult();
		}
	}

	protected Message buildMessage(ProxyRequest proxyRequest) {
		Message message = new Message();

		message.setPayload(proxyRequest);

		Map<String, Object> values = MessageValuesThreadLocal.getValues();

		if (!values.isEmpty()) {
			for (String key : values.keySet()) {
				message.put(key, values.get(key));
			}
		}

		if (proxyRequest.isLocal()) {
			message.put(MessagingProxy.LOCAL_MESSAGE, Boolean.TRUE);
		}

		return message;
	}

	private SingleDestinationMessageSender _singleDestinationMessageSender;
	private SingleDestinationSynchronousMessageSender
		_singleDestinationSynchronousMessageSender;

}