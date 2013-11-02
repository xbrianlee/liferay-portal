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

import java.util.Date;
import java.util.Map;

/**
 * @author     Bruno Farache
 * @deprecated As of 6.1.0, replaced by {@link
 *             com.liferay.portal.messaging.LayoutsRemotePublisherRequest}
 */
public class LayoutsRemotePublisherRequest
	extends com.liferay.portal.messaging.LayoutsRemotePublisherRequest {

	public LayoutsRemotePublisherRequest() {
	}

	public LayoutsRemotePublisherRequest(
		long userId, long sourceGroupId, boolean privateLayout,
		Map<Long, Boolean> layoutIdMap, Map<String, String[]> parameterMap,
		String remoteAddress, int remotePort, boolean secureConnection,
		long remoteGroupId, boolean remotePrivateLayout, Date startDate,
		Date endDate) {

		super(
			userId, sourceGroupId, privateLayout, layoutIdMap, parameterMap,
			remoteAddress, remotePort, null, secureConnection, remoteGroupId,
			remotePrivateLayout, startDate, endDate);
	}

}