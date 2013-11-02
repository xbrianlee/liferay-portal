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

package com.liferay.portal.kernel.uuid;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PortalUUIDUtil {

	public static String fromJsSafeUuid(String jsSafeUuid) {
		return getPortalUUID().fromJsSafeUuid(jsSafeUuid);
	}

	public static String generate() {
		return getPortalUUID().generate();
	}

	public static String generate(byte[] bytes) {
		return getPortalUUID().generate(bytes);
	}

	public static PortalUUID getPortalUUID() {
		PortalRuntimePermission.checkGetBeanProperty(PortalUUIDUtil.class);

		return _portalUUID;
	}

	public static String toJsSafeUuid(String uuid) {
		return getPortalUUID().toJsSafeUuid(uuid);
	}

	public void setPortalUUID(PortalUUID portalUUID) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalUUID = portalUUID;
	}

	private static PortalUUID _portalUUID;

}