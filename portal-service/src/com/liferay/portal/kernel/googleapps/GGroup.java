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

package com.liferay.portal.kernel.googleapps;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroup {

	public String getDescription() {
		return _description;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getEmailPermission() {
		return _emailPermission;
	}

	public String getName() {
		return _name;
	}

	public String getPermissionPreset() {
		return _permissionPreset;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEmailPermission(String emailPermission) {
		_emailPermission = emailPermission;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPermissionPreset(String permissionPreset) {
		_permissionPreset = permissionPreset;
	}

	private String _description;
	private String _emailAddress;
	private String _emailPermission;
	private String _name;
	private String _permissionPreset;

}