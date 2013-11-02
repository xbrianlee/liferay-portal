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

package com.liferay.portlet.dynamicdatamapping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMStorageLinkLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDMStorageLinkLocalServiceImpl
	extends DDMStorageLinkLocalServiceBaseImpl {

	@Override
	public DDMStorageLink addStorageLink(
			long classNameId, long classPK, long structureId,
			ServiceContext serviceContext)
		throws SystemException {

		long storageLinkId = counterLocalService.increment();

		DDMStorageLink storageLink = ddmStorageLinkPersistence.create(
			storageLinkId);

		storageLink.setClassNameId(classNameId);
		storageLink.setClassPK(classPK);
		storageLink.setStructureId(structureId);

		ddmStorageLinkPersistence.update(storageLink);

		return storageLink;
	}

	@Override
	public void deleteClassStorageLink(long classPK)
		throws PortalException, SystemException {

		DDMStorageLink storageLink = ddmStorageLinkPersistence.findByClassPK(
			classPK);

		deleteStorageLink(storageLink);
	}

	@Override
	public void deleteStorageLink(DDMStorageLink storageLink)
		throws SystemException {

		ddmStorageLinkPersistence.remove(storageLink);
	}

	@Override
	public void deleteStorageLink(long storageLinkId)
		throws PortalException, SystemException {

		DDMStorageLink storageLink = ddmStorageLinkPersistence.findByPrimaryKey(
			storageLinkId);

		deleteStorageLink(storageLink);
	}

	@Override
	public void deleteStructureStorageLinks(long structureId)
		throws SystemException {

		List<DDMStorageLink> storageLinks =
			ddmStorageLinkPersistence.findByStructureId(structureId);

		for (DDMStorageLink storageLink : storageLinks) {
			deleteStorageLink(storageLink);
		}
	}

	@Override
	public DDMStorageLink getClassStorageLink(long classPK)
		throws PortalException, SystemException {

		return ddmStorageLinkPersistence.findByClassPK(classPK);
	}

	@Override
	public DDMStorageLink getStorageLink(long storageLinkId)
		throws PortalException, SystemException {

		return ddmStorageLinkPersistence.findByPrimaryKey(storageLinkId);
	}

	@Override
	public List<DDMStorageLink> getStructureStorageLinks(long structureId)
		throws SystemException {

		return ddmStorageLinkPersistence.findByStructureId(structureId);
	}

	@Override
	public int getStructureStorageLinksCount(long structureId)
		throws SystemException {

		return ddmStorageLinkPersistence.countByStructureId(structureId);
	}

	@Override
	public DDMStorageLink updateStorageLink(
			long storageLinkId, long classNameId, long classPK)
		throws PortalException, SystemException {

		DDMStorageLink storageLink = ddmStorageLinkPersistence.findByPrimaryKey(
			storageLinkId);

		storageLink.setClassNameId(classNameId);
		storageLink.setClassPK(classPK);

		ddmStorageLinkPersistence.update(storageLink);

		return storageLink;
	}

}