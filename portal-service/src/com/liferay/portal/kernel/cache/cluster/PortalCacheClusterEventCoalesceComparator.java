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

package com.liferay.portal.kernel.cache.cluster;

import com.liferay.portal.kernel.util.Validator;

import java.util.Comparator;

/**
 * @author Shuyang Zhou
 */
public class PortalCacheClusterEventCoalesceComparator
	implements Comparator<PortalCacheClusterEvent> {

	@Override
	public int compare(
		PortalCacheClusterEvent portalCacheClusterEvent1,
		PortalCacheClusterEvent portalCacheClusterEvent2) {

		if ((portalCacheClusterEvent1 == null) ||
			(portalCacheClusterEvent2 == null)) {

			return 1;
		}

		if (Validator.equals(
				portalCacheClusterEvent1.getCacheName(),
				portalCacheClusterEvent2.getCacheName()) &&
			Validator.equals(
				portalCacheClusterEvent1.getElementKey(),
				portalCacheClusterEvent2.getElementKey()) &&
			(portalCacheClusterEvent1.getEventType() ==
				portalCacheClusterEvent2.getEventType())) {

			portalCacheClusterEvent1.setElementValue(
				portalCacheClusterEvent2.getElementValue());

			return 0;
		}

		return -1;
	}

}