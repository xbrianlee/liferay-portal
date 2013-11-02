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

package com.liferay.portal.model.impl;

/**
 * Stores the actions a role is permitted to perform on the resources in a
 * resource block.
 *
 * <p>
 * The <code>actionIds</code> attribute stores the bitwise IDs of all the
 * actions allowed by this permission.
 * </p>
 *
 * @author Connor McKay
 */
public class ResourceBlockPermissionImpl
	extends ResourceBlockPermissionBaseImpl {

	public ResourceBlockPermissionImpl() {
	}

}