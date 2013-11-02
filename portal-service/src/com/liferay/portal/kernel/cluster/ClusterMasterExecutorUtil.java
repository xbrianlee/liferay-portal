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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.MethodHandler;

import java.util.concurrent.Future;

/**
 * @author Michael C. Han
 */
public class ClusterMasterExecutorUtil {

	public static <T> Future<T> executeOnMaster(MethodHandler methodHandler)
		throws SystemException {

		ClusterMasterExecutor clusterMasterExecutor =
			getClusterMasterExecutor();

		if (clusterMasterExecutor == null) {
			return null;
		}

		return _clusterMasterExecutor.executeOnMaster(methodHandler);
	}

	public static ClusterMasterExecutor getClusterMasterExecutor() {
		return _clusterMasterExecutor;
	}

	public static void initialize() {
		ClusterMasterExecutor clusterMasterExecutor =
			getClusterMasterExecutor();

		if (clusterMasterExecutor == null) {
			return;
		}

		_clusterMasterExecutor.initialize();
	}

	public static boolean isMaster() {
		ClusterMasterExecutor clusterMasterExecutor =
			getClusterMasterExecutor();

		if (clusterMasterExecutor == null) {
			return false;
		}

		return _clusterMasterExecutor.isMaster();
	}

	public static void registerClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {

		ClusterMasterExecutor clusterMasterExecutor =
			getClusterMasterExecutor();

		if (clusterMasterExecutor == null) {
			return;
		}

		_clusterMasterExecutor.registerClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener);
	}

	public static void unregisterClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {

		ClusterMasterExecutor clusterMasterExecutor =
			getClusterMasterExecutor();

		if (clusterMasterExecutor == null) {
			return;
		}

		_clusterMasterExecutor.unregisterClusterMasterTokenTransitionListener(
			clusterMasterTokenTransitionListener);
	}

	public void setClusterMasterExecutor(
		ClusterMasterExecutor clusterMasterExecutor) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_clusterMasterExecutor = clusterMasterExecutor;
	}

	private static ClusterMasterExecutor _clusterMasterExecutor;

}