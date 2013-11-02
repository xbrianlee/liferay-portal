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

import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.model.ClusterGroup;
import com.liferay.portal.service.ClusterGroupLocalServiceUtil;

import java.lang.reflect.Method;

/**
 * @author Shuyang Zhou
 * @author Raymond Augé
 */
public class PortalManagerUtil {

	public static MethodHandler createManageActionMethodHandler(
		ManageAction<?> manageAction) {

		return new MethodHandler(_manageMethod, manageAction);
	}

	public static PortalManager getPortalManager() {
		PortalRuntimePermission.checkGetBeanProperty(PortalManagerUtil.class);

		return _portalManager;
	}

	public static FutureClusterResponses manage(
			ClusterGroup clusterGroup, ManageAction<?> manageAction)
		throws ManageActionException {

		ManageAction<FutureClusterResponses> manageActionWrapper =
			new ClusterManageActionWrapper(clusterGroup, manageAction);

		return getPortalManager().manage(manageActionWrapper);
	}

	public static <T> T manage(ManageAction<T> manageAction)
		throws ManageActionException {

		return getPortalManager().manage(manageAction);
	}

	public static void manageAsync(
			ClusterNode clusterNode, ManageAction<?> manageAction)
		throws Exception {

		ClusterGroup clusterGroup =
			ClusterGroupLocalServiceUtil.createClusterGroup(0);

		clusterGroup.setClusterNodeIds(clusterNode.getClusterNodeId());

		manage(clusterGroup, manageAction);
	}

	public static <T> T manageSync(
			ClusterNode clusterNode, ManageAction<T> manageAction)
		throws Exception {

		ClusterGroup clusterGroup =
			ClusterGroupLocalServiceUtil.createClusterGroup(0);

		clusterGroup.setClusterNodeIds(clusterNode.getClusterNodeId());

		FutureClusterResponses futureClusterResponses = manage(
			clusterGroup, manageAction);

		ClusterNodeResponses clusterNodeResponses =
			futureClusterResponses.get();

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNode);

		return (T)clusterNodeResponse.getResult();
	}

	public void setPortalManager(PortalManager portalManager) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalManager = portalManager;
	}

	private static Log _log = LogFactoryUtil.getLog(PortalManagerUtil.class);

	private static Method _manageMethod;
	private static PortalManager _portalManager;

	static {
		try {
			_manageMethod = PortalManagerUtil.class.getDeclaredMethod(
				"manage", ManageAction.class);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

}