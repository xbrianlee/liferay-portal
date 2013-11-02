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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Tina Tian
 * @author Raymond Augé
 */
public class ClusterExecutorUtil {

	public static void addClusterEventListener(
		ClusterEventListener clusterEventListener) {

		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.addClusterEventListener(clusterEventListener);
	}

	public static void destroy() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.destroy();
	}

	public static FutureClusterResponses execute(ClusterRequest clusterRequest)
		throws SystemException {

		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return null;
		}

		return clusterExecutor.execute(clusterRequest);
	}

	public static void execute(
			ClusterRequest clusterRequest,
			ClusterResponseCallback clusterResponseCallback)
		throws SystemException {

		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.execute(clusterRequest, clusterResponseCallback);
	}

	public static void execute(
			ClusterRequest clusterRequest,
			ClusterResponseCallback clusterResponseCallback, long timeout,
			TimeUnit timeUnit)
		throws SystemException {

		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.execute(
			clusterRequest, clusterResponseCallback, timeout, timeUnit);
	}

	public static ClusterExecutor getClusterExecutor() {
		PortalRuntimePermission.checkGetBeanProperty(ClusterExecutorUtil.class);

		if ((_clusterExecutor == null) || !_clusterExecutor.isEnabled()) {
			if (_log.isWarnEnabled()) {
				_log.warn("ClusterExecutorUtil has not been initialized");
			}

			return null;
		}

		return _clusterExecutor;
	}

	public static List<Address> getClusterNodeAddresses() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return Collections.emptyList();
		}

		return clusterExecutor.getClusterNodeAddresses();
	}

	public static List<ClusterNode> getClusterNodes() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return Collections.emptyList();
		}

		return clusterExecutor.getClusterNodes();
	}

	public static ClusterNode getLocalClusterNode() throws SystemException {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return null;
		}

		return clusterExecutor.getLocalClusterNode();
	}

	public static Address getLocalClusterNodeAddress() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return null;
		}

		return clusterExecutor.getLocalClusterNodeAddress();
	}

	public static void initialize() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.initialize();
	}

	public static boolean isClusterNodeAlive(Address address) {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return false;
		}

		return clusterExecutor.isClusterNodeAlive(address);
	}

	public static boolean isClusterNodeAlive(String clusterNodeId) {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return false;
		}

		return clusterExecutor.isClusterNodeAlive(clusterNodeId);
	}

	public static boolean isEnabled() {
		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return false;
		}

		return true;
	}

	public static void removeClusterEventListener(
		ClusterEventListener clusterEventListener) {

		ClusterExecutor clusterExecutor = getClusterExecutor();

		if (clusterExecutor == null) {
			return;
		}

		clusterExecutor.removeClusterEventListener(clusterEventListener);
	}

	public void setClusterExecutor(ClusterExecutor clusterExecutor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_clusterExecutor = clusterExecutor;
	}

	private static Log _log = LogFactoryUtil.getLog(ClusterExecutorUtil.class);

	private static ClusterExecutor _clusterExecutor;

}