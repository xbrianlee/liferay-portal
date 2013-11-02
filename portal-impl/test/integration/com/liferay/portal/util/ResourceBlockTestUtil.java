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

package com.liferay.portal.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.ResourceBlock;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;

/**
 * @author Alberto Chaparro
 */
public class ResourceBlockTestUtil {

	public static ResourceBlock addResourceBlock(long groupId, String name)
		throws Exception {

		long resourceBlockId = CounterLocalServiceUtil.increment(
			ResourceBlock.class.getName());

		ResourceBlock resourceBlock =
			ResourceBlockLocalServiceUtil.createResourceBlock(resourceBlockId);

		resourceBlock.setCompanyId(TestPropsValues.getCompanyId());
		resourceBlock.setGroupId(groupId);
		resourceBlock.setName(name);
		resourceBlock.setPermissionsHash(ServiceTestUtil.randomString());
		resourceBlock.setReferenceCount(0);

		return ResourceBlockLocalServiceUtil.addResourceBlock(resourceBlock);
	}

	public static ResourceBlock addResourceBlock(String name) throws Exception {
		return addResourceBlock(ServiceTestUtil.nextLong(), name);
	}

}