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

package com.liferay.portlet.documentlibrary.model;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Samuel Kong
 */
public class DLFileEntryConstants {

	public static final int DEFAULT_READ_COUNT = 0;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		PropsUtil.get("lock.expiration.time." + getClassName()));

	public static final String PRIVATE_WORKING_COPY_VERSION = "PWC";

	public static final String VERSION_DEFAULT = "1.0";

	public static String getClassName() {
		return DLFileEntry.class.getName();
	}

}