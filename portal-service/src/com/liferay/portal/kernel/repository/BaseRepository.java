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

package com.liferay.portal.kernel.repository;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService;

/**
 * @author Mika Koivisto
 */
public interface BaseRepository extends Repository {

	public LocalRepository getLocalRepository();

	public String[] getSupportedConfigurations();

	public String[][] getSupportedParameters();

	public void initRepository() throws PortalException, SystemException;

	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService);

	public void setCompanyId(long companyId);

	public void setCompanyLocalService(CompanyLocalService companyLocalService);

	public void setCounterLocalService(CounterLocalService counterLocalService);

	public void setDLAppHelperLocalService(
		DLAppHelperLocalService dlAppHelperLocalService);

	public void setGroupId(long groupId);

	public void setRepositoryId(long repositoryId);

	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties);

	public void setUserLocalService(UserLocalService userLocalService);

}