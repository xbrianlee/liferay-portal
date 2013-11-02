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

package com.liferay.portal.model;

/**
 * Contains constants used for resource permissions and permission scoping.
 *
 * @author Brian Wing Shun Chan
 */
public interface ResourceConstants {

	public static final long PRIMKEY_DNE = -1;

	public static final int SCOPE_COMPANY = 1;

	public static final int SCOPE_GROUP = 2;

	public static final int SCOPE_GROUP_TEMPLATE = 3;

	public static final int SCOPE_INDIVIDUAL = 4;

	public static final int[] SCOPES = {
		ResourceConstants.SCOPE_COMPANY, ResourceConstants.SCOPE_GROUP,
		ResourceConstants.SCOPE_GROUP_TEMPLATE,
		ResourceConstants.SCOPE_INDIVIDUAL
	};

}