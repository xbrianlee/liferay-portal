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
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author David Mendez Gonzalez
 */
public class AddressStagedModelDataHandler
	extends BaseStagedModelDataHandler<Address> {

	public static final String[] CLASS_NAMES = {Address.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Address address =
			AddressLocalServiceUtil.fetchAddressByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (address != null) {
			AddressLocalServiceUtil.deleteAddress(address);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Address address)
		throws Exception {

		Element addressElement = portletDataContext.getExportDataElement(
			address);

		portletDataContext.addClassedModel(
			addressElement, ExportImportPathUtil.getModelPath(address),
			address);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Address address)
		throws Exception {

		long userId = portletDataContext.getUserId(address.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			address);

		Address existingAddress =
			AddressLocalServiceUtil.fetchAddressByUuidAndCompanyId(
				address.getUuid(), portletDataContext.getCompanyId());

		Address importedAddress = null;

		if (existingAddress == null) {
			serviceContext.setUuid(address.getUuid());

			importedAddress = AddressLocalServiceUtil.addAddress(
				userId, address.getClassName(), address.getClassPK(),
				address.getStreet1(), address.getStreet2(),
				address.getStreet3(), address.getCity(), address.getZip(),
				address.getRegionId(), address.getCountryId(),
				address.getTypeId(), address.getMailing(), address.isPrimary(),
				serviceContext);
		}
		else {
			importedAddress = AddressLocalServiceUtil.updateAddress(
				existingAddress.getAddressId(), address.getStreet1(),
				address.getStreet2(), address.getStreet3(), address.getCity(),
				address.getZip(), address.getRegionId(), address.getCountryId(),
				address.getTypeId(), address.getMailing(), address.isPrimary());
		}

		portletDataContext.importClassedModel(address, importedAddress);
	}

}