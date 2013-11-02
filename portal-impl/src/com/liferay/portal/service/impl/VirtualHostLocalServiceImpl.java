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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.VirtualHost;
import com.liferay.portal.service.base.VirtualHostLocalServiceBaseImpl;

/**
 * @author Alexander Chow
 */
public class VirtualHostLocalServiceImpl
	extends VirtualHostLocalServiceBaseImpl {

	@Override
	public VirtualHost fetchVirtualHost(long companyId, long layoutSetId)
		throws SystemException {

		return virtualHostPersistence.fetchByC_L(companyId, layoutSetId);
	}

	@Override
	public VirtualHost fetchVirtualHost(String hostname)
		throws SystemException {

		return virtualHostPersistence.fetchByHostname(hostname);
	}

	@Override
	public VirtualHost getVirtualHost(long companyId, long layoutSetId)
		throws PortalException, SystemException {

		return virtualHostPersistence.findByC_L(companyId, layoutSetId);
	}

	@Override
	public VirtualHost getVirtualHost(String hostname)
		throws PortalException, SystemException {

		return virtualHostPersistence.findByHostname(hostname);
	}

	@Override
	public VirtualHost updateVirtualHost(
			long companyId, long layoutSetId, String hostname)
		throws SystemException {

		VirtualHost virtualHost = virtualHostPersistence.fetchByC_L(
			companyId, layoutSetId);

		if (virtualHost == null) {
			long virtualHostId = counterLocalService.increment();

			virtualHost = virtualHostPersistence.create(virtualHostId);

			virtualHost.setCompanyId(companyId);
			virtualHost.setLayoutSetId(layoutSetId);
		}

		virtualHost.setHostname(hostname);

		virtualHostPersistence.update(virtualHost);

		Company company = companyPersistence.fetchByPrimaryKey(companyId);

		if (company != null) {
			companyPersistence.clearCache(company);
		}

		LayoutSet layoutSet = layoutSetPersistence.fetchByPrimaryKey(
			layoutSetId);

		if (layoutSet != null) {
			layoutSetPersistence.clearCache(layoutSet);
		}

		return virtualHost;
	}

}