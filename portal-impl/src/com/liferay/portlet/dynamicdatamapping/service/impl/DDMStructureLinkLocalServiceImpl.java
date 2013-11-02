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
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureLink;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMStructureLinkLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Basto
 */
public class DDMStructureLinkLocalServiceImpl
	extends DDMStructureLinkLocalServiceBaseImpl {

	@Override
	public DDMStructureLink addStructureLink(
			long classNameId, long classPK, long structureId,
			ServiceContext serviceContext)
		throws SystemException {

		long structureLinkId = counterLocalService.increment();

		DDMStructureLink structureLink = ddmStructureLinkPersistence.create(
			structureLinkId);

		structureLink.setClassNameId(classNameId);
		structureLink.setClassPK(classPK);
		structureLink.setStructureId(structureId);

		ddmStructureLinkPersistence.update(structureLink);

		return structureLink;
	}

	@Override
	public void deleteClassStructureLink(long classPK)
		throws PortalException, SystemException {

		DDMStructureLink structureLink =
			ddmStructureLinkPersistence.findByClassPK(classPK);

		deleteStructureLink(structureLink);
	}

	@Override
	public void deleteStructureLink(DDMStructureLink structureLink)
		throws SystemException {

		ddmStructureLinkPersistence.remove(structureLink);
	}

	@Override
	public void deleteStructureLink(long structureLinkId)
		throws PortalException, SystemException {

		DDMStructureLink structureLink =
			ddmStructureLinkPersistence.findByPrimaryKey(structureLinkId);

		deleteStructureLink(structureLink);
	}

	@Override
	public void deleteStructureStructureLinks(long structureId)
		throws SystemException {

		List<DDMStructureLink> structureLinks =
			ddmStructureLinkPersistence.findByStructureId(structureId);

		for (DDMStructureLink structureLink : structureLinks) {
			deleteStructureLink(structureLink);
		}
	}

	@Override
	public DDMStructureLink getClassStructureLink(long classPK)
		throws PortalException, SystemException {

		return ddmStructureLinkPersistence.findByClassPK(classPK);
	}

	@Override
	public List<DDMStructureLink> getClassStructureLinks(long classNameId)
		throws SystemException {

		return ddmStructureLinkPersistence.findByStructureId(classNameId);
	}

	@Override
	public DDMStructureLink getStructureLink(long structureLinkId)
		throws PortalException, SystemException {

		return ddmStructureLinkPersistence.findByPrimaryKey(structureLinkId);
	}

	@Override
	public List<DDMStructureLink> getStructureLinks(
			long structureId, int start, int end)
		throws SystemException {

		return ddmStructureLinkPersistence.findByStructureId(
			structureId, start, end);
	}

	@Override
	public DDMStructureLink updateStructureLink(
			long structureLinkId, long classNameId, long classPK,
			long structureId)
		throws PortalException, SystemException {

		DDMStructureLink structureLink =
			ddmStructureLinkPersistence.findByPrimaryKey(structureLinkId);

		structureLink.setClassNameId(classNameId);
		structureLink.setClassPK(classPK);
		structureLink.setStructureId(structureId);

		ddmStructureLinkPersistence.update(structureLink);

		return structureLink;
	}

}