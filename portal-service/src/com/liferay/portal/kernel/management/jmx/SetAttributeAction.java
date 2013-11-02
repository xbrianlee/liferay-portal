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

package com.liferay.portal.kernel.management.jmx;

import com.liferay.portal.kernel.management.ManageActionException;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Shuyang Zhou
 */
public class SetAttributeAction extends BaseJMXManageAction<Void> {

	public SetAttributeAction(
		ObjectName objectName, String name, Object value) {

		_objectName = objectName;
		_name = name;
		_value = value;
	}

	@Override
	public Void action() throws ManageActionException {
		try {
			MBeanServer mBeanServer = getMBeanServer();

			mBeanServer.setAttribute(_objectName, new Attribute(_name, _value));

			return null;
		}
		catch (Exception e) {
			throw new ManageActionException(e);
		}
	}

	private String _name;
	private ObjectName _objectName;
	private Object _value;

}