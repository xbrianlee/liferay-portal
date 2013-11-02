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
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author David Mendez Gonzalez
 */
public class EmailAddressStagedModelDataHandler
	extends BaseStagedModelDataHandler<EmailAddress> {

	public static final String[] CLASS_NAMES = {EmailAddress.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		EmailAddress emailAddress =
			EmailAddressLocalServiceUtil.fetchEmailAddressByUuidAndCompanyId(
				uuid, group.getCompanyId());

		EmailAddressLocalServiceUtil.deleteEmailAddress(emailAddress);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, EmailAddress emailAddress)
		throws Exception {

		Element emailAddressElement = portletDataContext.getExportDataElement(
			emailAddress);

		portletDataContext.addClassedModel(
			emailAddressElement,
			ExportImportPathUtil.getModelPath(emailAddress), emailAddress);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, EmailAddress emailAddress)
		throws Exception {

		long userId = portletDataContext.getUserId(emailAddress.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			emailAddress);

		EmailAddress existingEmailAddress =
			EmailAddressLocalServiceUtil.fetchEmailAddressByUuidAndCompanyId(
				emailAddress.getUuid(), portletDataContext.getCompanyId());

		EmailAddress importedEmailAddress = null;

		if (existingEmailAddress == null) {
			serviceContext.setUuid(emailAddress.getUuid());

			importedEmailAddress = EmailAddressLocalServiceUtil.addEmailAddress(
				userId, emailAddress.getClassName(), emailAddress.getClassPK(),
				emailAddress.getAddress(), emailAddress.getTypeId(),
				emailAddress.isPrimary(), serviceContext);
		}
		else {
			importedEmailAddress =
				EmailAddressLocalServiceUtil.updateEmailAddress(
					existingEmailAddress.getEmailAddressId(),
					emailAddress.getAddress(), emailAddress.getTypeId(),
					emailAddress.isPrimary());
		}

		portletDataContext.importClassedModel(
			emailAddress, importedEmailAddress);
	}

}