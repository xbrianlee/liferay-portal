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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.management.ManageAction;

import java.util.concurrent.atomic.AtomicReference;

import javax.management.MBeanServer;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseJMXManageAction<T> implements ManageAction<T> {

	protected MBeanServer getMBeanServer() {
		MBeanServer mBeanServer = _mBeanServerReference.get();

		if (mBeanServer == null) {
			mBeanServer = (MBeanServer)PortalBeanLocatorUtil.locate(
				"mBeanServer");

			_mBeanServerReference.compareAndSet(null, mBeanServer);
		}

		return mBeanServer;
	}

	private static AtomicReference<MBeanServer> _mBeanServerReference =
		new AtomicReference<MBeanServer>();

}