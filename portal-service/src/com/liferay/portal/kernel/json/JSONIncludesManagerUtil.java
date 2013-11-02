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

package com.liferay.portal.kernel.json;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Igor Spasic
 */
public class JSONIncludesManagerUtil {

	public static JSONIncludesManager getJSONIncludesManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			JSONIncludesManagerUtil.class);

		return _jsonIncludesManager;
	}

	public static String[] lookupExcludes(Class<?> type) {
		return getJSONIncludesManager().lookupExcludes(type);
	}

	public static String[] lookupIncludes(Class<?> type) {
		return getJSONIncludesManager().lookupIncludes(type);
	}

	public void setJSONIncludesManager(
		JSONIncludesManager jsonIncludesManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_jsonIncludesManager = jsonIncludesManager;
	}

	private static JSONIncludesManager _jsonIncludesManager;

}