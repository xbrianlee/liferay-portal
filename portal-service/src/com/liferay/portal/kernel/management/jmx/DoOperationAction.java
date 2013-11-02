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

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Shuyang Zhou
 */
public class DoOperationAction extends BaseJMXManageAction<Object> {

	public DoOperationAction(
		ObjectName objectName, String operationName, Object[] parameters,
		String[] signature) {

		_objectName = objectName;
		_operationName = operationName;
		_parameters = parameters;
		_signature = signature;
	}

	@Override
	public Object action() throws ManageActionException {
		try {
			MBeanServer mBeanServer = getMBeanServer();

			return mBeanServer.invoke(
				_objectName, _operationName, _parameters, _signature);
		}
		catch (Exception e) {
			throw new ManageActionException(e);
		}
	}

	private ObjectName _objectName;
	private String _operationName;
	private Object[] _parameters;
	private String[] _signature;

}