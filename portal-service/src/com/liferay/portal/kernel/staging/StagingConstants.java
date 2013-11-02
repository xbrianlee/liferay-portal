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

package com.liferay.portal.kernel.staging;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Raymond Augé
 */
public class StagingConstants {

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		PropsUtil.get("lock.expiration.time." + Staging.class.getName()));

	public static final String STAGED_PORTLET = "staged-portlet_";

	public static final int TYPE_LOCAL_STAGING = 1;

	public static final int TYPE_NOT_STAGED = 0;

	public static final int TYPE_REMOTE_STAGING = 2;

}