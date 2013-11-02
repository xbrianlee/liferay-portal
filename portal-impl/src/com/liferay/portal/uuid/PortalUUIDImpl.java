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

package com.liferay.portal.uuid;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.util.UUID;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class PortalUUIDImpl implements PortalUUID {

	@Override
	public String fromJsSafeUuid(String jsSafeUuid) {
		return StringUtil.replace(
			jsSafeUuid, StringPool.DOUBLE_UNDERLINE, StringPool.DASH);
	}

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String generate(byte[] bytes) {
		return UUID.nameUUIDFromBytes(bytes).toString();
	}

	@Override
	public String toJsSafeUuid(String uuid) {
		return StringUtil.replace(
			uuid, StringPool.DASH, StringPool.DOUBLE_UNDERLINE);
	}

}