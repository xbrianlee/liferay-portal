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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.security.pacl.PACLConstants;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletBagPool {

	public static PortletBag get(String portletId) {
		PortalRuntimePermission.checkPortletBagPool(portletId);

		return _instance._get(portletId);
	}

	public static void put(String portletId, PortletBag portletBag) {
		PortalRuntimePermission.checkPortletBagPool(portletId);

		_instance._put(portletId, portletBag);
	}

	public static PortletBag remove(String portletId) {
		PortalRuntimePermission.checkPortletBagPool(portletId);

		return _instance._remove(portletId);
	}

	public static void reset() {
		PortalRuntimePermission.checkPortletBagPool(
			PACLConstants.
				PORTAL_RUNTIME_PERMISSION_PORTLET_BAG_POOL_ALL_PORTLETS);

		_instance._reset();
	}

	private PortletBagPool() {
		_portletBagPool = new ConcurrentHashMap<String, PortletBag>();
	}

	private PortletBag _get(String portletId) {
		return _portletBagPool.get(portletId);
	}

	private void _put(String portletId, PortletBag portletBag) {
		_portletBagPool.put(portletId, portletBag);
	}

	private PortletBag _remove(String portletId) {
		return _portletBagPool.remove(portletId);
	}

	private void _reset() {
		_portletBagPool.clear();
	}

	private static PortletBagPool _instance = new PortletBagPool();

	private Map<String, PortletBag> _portletBagPool;

}