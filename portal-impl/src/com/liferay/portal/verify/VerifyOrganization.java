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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.persistence.OrganizationActionableDynamicQuery;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Daniel Kocsis
 */
public class VerifyOrganization extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		rebuildTree();

		updateOrganizationAssets();

		updateOrganizationAssetEntries();
	}

	protected void rebuildTree() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			OrganizationLocalServiceUtil.rebuildTree(companyId);
		}
	}

	protected void updateOrganizationAssetEntries() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new OrganizationActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) {
				Organization organization = (Organization)object;

				try {
					AssetEntry assetEntry =
						AssetEntryLocalServiceUtil.getEntry(
							Organization.class.getName(),
							organization.getOrganizationId());

					if (Validator.isNotNull(assetEntry.getClassUuid())) {
						return;
					}

					assetEntry.setClassUuid(organization.getUuid());

					AssetEntryLocalServiceUtil.updateAssetEntry(assetEntry);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to update asset entry for organization " +
								organization.getOrganizationId(),
							e);
					}
				}
			}

		};

		actionableDynamicQuery.performActions();
	}

	protected void updateOrganizationAssets() throws Exception {
		List<Organization> organizations =
			OrganizationLocalServiceUtil.getNoAssetOrganizations();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Processing " + organizations.size() + " organizations with " +
					"no asset");
		}

		for (Organization organization : organizations) {
			try {
				OrganizationLocalServiceUtil.updateAsset(
					organization.getUserId(), organization, null, null);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to update asset for organization " +
							organization.getOrganizationId() + ": " +
								e.getMessage());
				}
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Assets verified for organizations");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyOrganization.class);

}