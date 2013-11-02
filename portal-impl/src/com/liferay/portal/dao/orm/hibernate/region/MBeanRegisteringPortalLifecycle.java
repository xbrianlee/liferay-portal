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

package com.liferay.portal.dao.orm.hibernate.region;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;

import javax.management.MBeanServer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

/**
 * @author Shuyang Zhou
 */
public class MBeanRegisteringPortalLifecycle extends BasePortalLifecycle {

	public MBeanRegisteringPortalLifecycle(CacheManager cacheManager) {
		_cacheManager = cacheManager;
	}

	@Override
	protected void doPortalDestroy() {
		_managementService.dispose();
	}

	@Override
	protected void doPortalInit() throws Exception {
		MBeanServer mBeanServer = (MBeanServer)PortalBeanLocatorUtil.locate(
			_MBEAN_SERVER_BEAN_NAME);

		_managementService = new ManagementService(
			_cacheManager, mBeanServer, true, true, true, true);

		_managementService.init();
	}

	private static final String _MBEAN_SERVER_BEAN_NAME =
		"registryAwareMBeanServer";

	private CacheManager _cacheManager;
	private ManagementService _managementService;

}