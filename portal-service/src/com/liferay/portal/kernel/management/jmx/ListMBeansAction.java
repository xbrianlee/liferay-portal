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

import java.util.HashSet;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author Shuyang Zhou
 */
public class ListMBeansAction extends BaseJMXManageAction<Set<MBean>> {

	public ListMBeansAction(String domainName) {
		_domainName = domainName;
	}

	@Override
	public Set<MBean> action() throws ManageActionException {
		try {
			MBeanServer mBeanServer = getMBeanServer();

			Set<ObjectName> objectNames = mBeanServer.queryNames(
				null, new ObjectName(_domainName.concat(":*")));

			Set<MBean> mBeans = new HashSet<MBean>(objectNames.size());

			for (ObjectName objectName : objectNames) {
				mBeans.add(new MBean(objectName));
			}

			return mBeans;
		}
		catch (MalformedObjectNameException mone) {
			throw new ManageActionException(mone);
		}
	}

	private String _domainName;

}