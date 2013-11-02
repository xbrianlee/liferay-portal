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

package com.liferay.portlet.flags.service.impl;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.flags.messaging.FlagsRequest;
import com.liferay.portlet.flags.service.base.FlagsEntryServiceBaseImpl;

/**
 * @author Julio Camarero
 */
public class FlagsEntryServiceImpl extends FlagsEntryServiceBaseImpl {

	@Override
	public void addEntry(
		String className, long classPK, String reporterEmailAddress,
		long reportedUserId, String contentTitle, String contentURL,
		String reason, ServiceContext serviceContext) {

		FlagsRequest flagsRequest = new FlagsRequest(
			className, classPK, reporterEmailAddress, reportedUserId,
			contentTitle, contentURL, reason, serviceContext);

		MessageBusUtil.sendMessage(DestinationNames.FLAGS, flagsRequest);
	}

}