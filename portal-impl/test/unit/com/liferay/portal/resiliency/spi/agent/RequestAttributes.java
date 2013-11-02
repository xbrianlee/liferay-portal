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

package com.liferay.portal.resiliency.spi.agent;

import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Direction;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Distributed;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.DistributedRegistry;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.MatchType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class RequestAttributes {

	@Distributed(direction = Direction.DUPLEX, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_1 = "ATTRIBUTE_1";

	@Distributed(direction = Direction.REQUEST, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_2 = "ATTRIBUTE_2";

	@Distributed(
		direction = Direction.RESPONSE, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_3 = "ATTRIBUTE_3";

	public static void setRequestAttributes(HttpServletRequest request) {
		DistributedRegistry.registerDistributed(RequestAttributes.class);

		request.setAttribute(ATTRIBUTE_1, ATTRIBUTE_1);
		request.setAttribute(ATTRIBUTE_2, ATTRIBUTE_2);
		request.setAttribute(ATTRIBUTE_3, ATTRIBUTE_3);
	}

}