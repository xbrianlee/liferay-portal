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

package com.liferay.portlet.trash.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.trash.model.TrashVersion;
import com.liferay.portlet.trash.service.base.TrashVersionLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Zsolt Berentey
 */
public class TrashVersionLocalServiceImpl
	extends TrashVersionLocalServiceBaseImpl {

	@Override
	public void addTrashVersion(
			long trashEntryId, String className, long classPK, int status,
			UnicodeProperties typeSettingsProperties)
		throws SystemException {

		long versionId = counterLocalService.increment();

		TrashVersion trashVersion = trashVersionPersistence.create(versionId);

		trashVersion.setEntryId(trashEntryId);
		trashVersion.setClassName(className);
		trashVersion.setClassPK(classPK);

		if (typeSettingsProperties != null) {
			trashVersion.setTypeSettingsProperties(typeSettingsProperties);
		}

		trashVersion.setStatus(status);

		trashVersionPersistence.update(trashVersion);
	}

	@Override
	public TrashVersion deleteTrashVersion(
			long entryId, String className, long classPK)
		throws SystemException {

		TrashVersion trashVersion = fetchVersion(entryId, className, classPK);

		if (trashVersion != null) {
			return deleteTrashVersion(trashVersion);
		}

		return null;
	}

	@Override
	public TrashVersion fetchVersion(
			long entryId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return trashVersionPersistence.fetchByE_C_C(
			entryId, classNameId, classPK);
	}

	@Override
	public List<TrashVersion> getVersions(long entryId) throws SystemException {
		return trashVersionPersistence.findByEntryId(entryId);
	}

	@Override
	public List<TrashVersion> getVersions(long entryId, String className)
		throws SystemException {

		if (Validator.isNull(className)) {
			return trashVersionPersistence.findByEntryId(entryId);
		}

		long classNameId = PortalUtil.getClassNameId(className);

		return trashVersionPersistence.findByE_C(entryId, classNameId);
	}

	/**
	 * Returns all the trash versions associated with the trash entry.
	 *
	 * @param  className the class name of the trash entity
	 * @param  classPK the primary key of the trash entity
	 * @return all the trash versions associated with the trash entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TrashVersion> getVersions(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return trashVersionPersistence.findByC_C(classNameId, classPK);
	}

}