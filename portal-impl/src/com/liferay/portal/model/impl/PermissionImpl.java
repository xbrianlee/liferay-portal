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

import com.liferay.portal.model.Permission;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class PermissionImpl implements Permission, Serializable {

	public PermissionImpl() {
	}

	@Override
	public String getActionId() {
		return _actionId;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPrimKey() {
		return _primKey;
	}

	@Override
	public int getScope() {
		return _scope;
	}

	@Override
	public void setActionId(String actionId) {
		_actionId = actionId;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	@Override
	public void setScope(int scope) {
		_scope = scope;
	}

	private String _actionId;
	private String _name;
	private String _primKey;
	private int _scope;

}