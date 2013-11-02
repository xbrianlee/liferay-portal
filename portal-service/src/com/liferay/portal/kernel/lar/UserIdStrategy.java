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

package com.liferay.portal.kernel.lar;

/**
 * @author Bruno Farache
 */
public interface UserIdStrategy {

	public static final String ALWAYS_CURRENT_USER_ID =
		"ALWAYS_CURRENT_USER_ID";

	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

	public long getUserId(String userUuid);

}