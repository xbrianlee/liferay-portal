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

import com.liferay.portal.service.persistence.UserGroupGroupRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.UserGroupGroupRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.http.UserGroupGroupRoleServiceSoap
 * @generated
 */
public class UserGroupGroupRoleSoap implements Serializable {
	public static UserGroupGroupRoleSoap toSoapModel(UserGroupGroupRole model) {
		UserGroupGroupRoleSoap soapModel = new UserGroupGroupRoleSoap();

		soapModel.setUserGroupId(model.getUserGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static UserGroupGroupRoleSoap[] toSoapModels(
		UserGroupGroupRole[] models) {
		UserGroupGroupRoleSoap[] soapModels = new UserGroupGroupRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserGroupGroupRoleSoap[][] toSoapModels(
		UserGroupGroupRole[][] models) {
		UserGroupGroupRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserGroupGroupRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserGroupGroupRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserGroupGroupRoleSoap[] toSoapModels(
		List<UserGroupGroupRole> models) {
		List<UserGroupGroupRoleSoap> soapModels = new ArrayList<UserGroupGroupRoleSoap>(models.size());

		for (UserGroupGroupRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserGroupGroupRoleSoap[soapModels.size()]);
	}

	public UserGroupGroupRoleSoap() {
	}

	public UserGroupGroupRolePK getPrimaryKey() {
		return new UserGroupGroupRolePK(_userGroupId, _groupId, _roleId);
	}

	public void setPrimaryKey(UserGroupGroupRolePK pk) {
		setUserGroupId(pk.userGroupId);
		setGroupId(pk.groupId);
		setRoleId(pk.roleId);
	}

	public long getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _userGroupId;
	private long _groupId;
	private long _roleId;
}