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

package com.liferay.portlet.usersadmin.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.WebsiteLocalServiceUtil;

/**
 * @author David Mendez Gonzalez
 */
public class WebsiteStagedModelDataHandler
	extends BaseStagedModelDataHandler<Website> {

	public static final String[] CLASS_NAMES = {Website.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Website website =
			WebsiteLocalServiceUtil.fetchWebsiteByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (website != null) {
			WebsiteLocalServiceUtil.deleteWebsite(website);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Website website)
		throws Exception {

		Element websiteElement = portletDataContext.getExportDataElement(
			website);

		portletDataContext.addClassedModel(
			websiteElement, ExportImportPathUtil.getModelPath(website),
			website);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Website website)
		throws Exception {

		long userId = portletDataContext.getUserId(website.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			website);

		Website existingWebsite =
			WebsiteLocalServiceUtil.fetchWebsiteByUuidAndCompanyId(
				website.getUuid(), portletDataContext.getCompanyId());

		Website importedWebsite = null;

		if (existingWebsite == null) {
			serviceContext.setUuid(website.getUuid());

			importedWebsite = WebsiteLocalServiceUtil.addWebsite(
				userId, website.getClassName(), website.getClassPK(),
				website.getUrl(), website.getTypeId(), website.isPrimary(),
				serviceContext);
		}
		else {
			importedWebsite = WebsiteLocalServiceUtil.updateWebsite(
				existingWebsite.getWebsiteId(), website.getUrl(),
				website.getTypeId(), website.isPrimary());
		}

		portletDataContext.importClassedModel(website, importedWebsite);
	}

}