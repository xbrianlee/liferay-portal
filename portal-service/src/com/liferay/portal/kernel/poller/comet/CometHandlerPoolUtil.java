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

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Edward Han
 * @author Raymond Augé
 */
public class CometHandlerPoolUtil {

	public static void closeCometHandler(String sessionId)
		throws CometException {

		getCometHandlerPool().closeCometHandler(sessionId);
	}

	public static void closeCometHandlers() throws CometException {
		getCometHandlerPool().closeCometHandlers();
	}

	public static CometHandler getCometHandler(String sessionId) {
		return getCometHandlerPool().getCometHandler(sessionId);
	}

	public static CometHandlerPool getCometHandlerPool() {
		PortalRuntimePermission.checkGetBeanProperty(
			CometHandlerPoolUtil.class);

		return _cometHandlerPool;
	}

	public static void startCometHandler(
			CometSession cometSession, CometHandler cometHandler)
		throws CometException {

		getCometHandlerPool().startCometHandler(cometSession, cometHandler);
	}

	public void setCometHandlerPool(CometHandlerPool cometHandlerPool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_cometHandlerPool = cometHandlerPool;
	}

	private static CometHandlerPool _cometHandlerPool;

}