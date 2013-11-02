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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ClusterGroup;
import com.liferay.portal.service.base.ClusterGroupLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ClusterGroupLocalServiceImpl
	extends ClusterGroupLocalServiceBaseImpl {

	@Override
	public ClusterGroup addClusterGroup(
			String name, List<String> clusterNodeIds)
		throws SystemException {

		long clusterGroupId = counterLocalService.increment();

		ClusterGroup clusterGroup = clusterGroupPersistence.create(
			clusterGroupId);

		clusterGroup.setName(name);
		clusterGroup.setClusterNodeIds(StringUtil.merge(clusterNodeIds));

		return clusterGroupPersistence.update(clusterGroup);
	}

	@Override
	public ClusterGroup addWholeClusterGroup(String name)
		throws SystemException {

		long clusterGroupId = counterLocalService.increment();

		ClusterGroup clusterGroup = clusterGroupPersistence.create(
			clusterGroupId);

		clusterGroup.setName(name);
		clusterGroup.setWholeCluster(true);

		return clusterGroupPersistence.update(clusterGroup);
	}

}