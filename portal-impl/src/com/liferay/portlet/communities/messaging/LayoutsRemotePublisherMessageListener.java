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

package com.liferay.portlet.communities.messaging;

import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author     Bruno Farache
 * @deprecated As of 6.1.0, replaced by {@link
 *             com.liferay.portal.messaging.LayoutsRemotePublisherMessageListener}
 */
public class LayoutsRemotePublisherMessageListener
	extends com.liferay.portal.messaging.LayoutsRemotePublisherMessageListener {

	public LayoutsRemotePublisherMessageListener() {
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public LayoutsRemotePublisherMessageListener(
		SingleDestinationMessageSender statusSender,
		MessageSender responseSender) {

		super(statusSender, responseSender);
	}

}