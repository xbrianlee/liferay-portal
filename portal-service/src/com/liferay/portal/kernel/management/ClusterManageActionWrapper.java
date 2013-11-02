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

package com.liferay.portal.kernel.management;

import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.model.ClusterGroup;

import java.util.Iterator;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class ClusterManageActionWrapper
	implements ManageAction<FutureClusterResponses> {

	public ClusterManageActionWrapper(
		ClusterGroup clusterGroup, ManageAction<?> manageAction) {

		_clusterGroup = clusterGroup;
		_manageAction = manageAction;
	}

	@Override
	public FutureClusterResponses action() throws ManageActionException {
		try {
			return doAction();
		}
		catch (SystemException se) {
			throw new ManageActionException(
				"Failed to execute cluster manage action", se);
		}
	}

	protected FutureClusterResponses doAction()
		throws ManageActionException, SystemException {

		MethodHandler manageActionMethodHandler =
			PortalManagerUtil.createManageActionMethodHandler(_manageAction);

		ClusterRequest clusterRequest = null;

		if (_clusterGroup.isWholeCluster()) {
			clusterRequest = ClusterRequest.createMulticastRequest(
				manageActionMethodHandler);
		}
		else {
			verifyClusterGroup();

			clusterRequest = ClusterRequest.createUnicastRequest(
				manageActionMethodHandler,
				_clusterGroup.getClusterNodeIdsArray());
		}

		return ClusterExecutorUtil.execute(clusterRequest);
	}

	protected void verifyClusterGroup() throws ManageActionException {
		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		String[] requiredClusterNodesIds =
			_clusterGroup.getClusterNodeIdsArray();

		for (String requiredClusterNodeId : requiredClusterNodesIds) {
			boolean verified = false;

			Iterator<ClusterNode> itr = clusterNodes.iterator();

			while (itr.hasNext()) {
				ClusterNode clusterNode = itr.next();

				String clusterNodeId = clusterNode.getClusterNodeId();

				if (clusterNodeId.equals(requiredClusterNodeId)) {
					itr.remove();

					verified = true;

					break;
				}
			}

			if (!verified) {
				throw new ManageActionException(
					"Cluster node " + requiredClusterNodeId +
						" is not available");
			}
		}
	}

	private ClusterGroup _clusterGroup;
	private ManageAction<?> _manageAction;

}