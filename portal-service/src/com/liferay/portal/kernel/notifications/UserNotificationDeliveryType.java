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

package com.liferay.portal.kernel.notifications;

/**
 * @author Jonathan Lee
 */
public class UserNotificationDeliveryType {

	public UserNotificationDeliveryType(
		String name, int type, boolean defaultValue, boolean modifiable) {

		_default = defaultValue;
		_modifiable = modifiable;
		_name = name;
		_type = type;
	}

	public String getName() {
		return _name;
	}

	public int getType() {
		return _type;
	}

	public boolean isDefault() {
		return _default;
	}

	public boolean isModifiable() {
		return _modifiable;
	}

	private boolean _default;
	private boolean _modifiable;
	private String _name;
	private int _type;

}