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

import com.liferay.portal.kernel.jmx.model.MBean;
import com.liferay.portal.kernel.management.ManageActionException;

import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Shuyang Zhou
 */
public class LoadMBeanInfoAction extends BaseJMXManageAction<MBean> {

	public LoadMBeanInfoAction(MBean mBean) {
		_mBean = mBean;
	}

	@Override
	public MBean action() throws ManageActionException {
		try {
			ObjectName objectName = _mBean.getObjectName();

			MBeanServer mBeanServer = getMBeanServer();

			MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(objectName);

			return new MBean(objectName, mBeanInfo);
		}
		catch (Exception e) {
			throw new ManageActionException(e);
		}
	}

	private MBean _mBean;

}