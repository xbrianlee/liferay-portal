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
import com.liferay.portal.model.Phone;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author David Mendez Gonzalez
 */
public class PhoneStagedModelDataHandler
	extends BaseStagedModelDataHandler<Phone> {

	public static final String[] CLASS_NAMES = {Phone.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Phone phone = PhoneLocalServiceUtil.fetchPhoneByUuidAndCompanyId(
			uuid, group.getCompanyId());

		if (phone != null) {
			PhoneLocalServiceUtil.deletePhone(phone);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Phone phone)
		throws Exception {

		Element phoneElement = portletDataContext.getExportDataElement(phone);

		portletDataContext.addClassedModel(
			phoneElement, ExportImportPathUtil.getModelPath(phone), phone);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Phone phone)
		throws Exception {

		long userId = portletDataContext.getUserId(phone.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			phone);

		Phone existingPhone =
			PhoneLocalServiceUtil.fetchPhoneByUuidAndCompanyId(
				phone.getUuid(), portletDataContext.getCompanyId());

		Phone importedPhone = null;

		if (existingPhone == null) {
			serviceContext.setUuid(phone.getUuid());

			importedPhone = PhoneLocalServiceUtil.addPhone(
				userId, phone.getClassName(), phone.getClassPK(),
				phone.getNumber(), phone.getExtension(), phone.getTypeId(),
				phone.isPrimary(), serviceContext);
		}
		else {
			importedPhone = PhoneLocalServiceUtil.updatePhone(
				existingPhone.getPhoneId(), phone.getNumber(),
				phone.getExtension(), phone.getTypeId(), phone.isPrimary());
		}

		portletDataContext.importClassedModel(phone, importedPhone);
	}

}