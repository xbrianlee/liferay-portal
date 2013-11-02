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
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.RepositoryEntryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Michael C. Han
 * @author Mate Thurzo
 */
public class RepositoryEntryLocalServiceImpl
	extends RepositoryEntryLocalServiceBaseImpl {

	@Override
	public RepositoryEntry addRepositoryEntry(
			long userId, long groupId, long repositoryId, String mappedId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long repositoryEntryId = counterLocalService.increment();

		RepositoryEntry repositoryEntry = repositoryEntryPersistence.create(
			repositoryEntryId);

		repositoryEntry.setUuid(serviceContext.getUuid());
		repositoryEntry.setGroupId(groupId);
		repositoryEntry.setCompanyId(user.getCompanyId());
		repositoryEntry.setUserId(userId);
		repositoryEntry.setUserName(user.getFullName());
		repositoryEntry.setCreateDate(serviceContext.getCreateDate(now));
		repositoryEntry.setModifiedDate(serviceContext.getModifiedDate(now));
		repositoryEntry.setRepositoryId(repositoryId);
		repositoryEntry.setMappedId(mappedId);

		repositoryEntryPersistence.update(repositoryEntry);

		return repositoryEntry;
	}

	@Override
	public List<RepositoryEntry> getRepositoryEntries(long repositoryId)
		throws SystemException {

		return repositoryEntryPersistence.findByRepositoryId(repositoryId);
	}

	@Override
	public RepositoryEntry updateRepositoryEntry(
			long repositoryEntryId, String mappedId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry =
			repositoryEntryPersistence.findByPrimaryKey(repositoryEntryId);

		repositoryEntry.setModifiedDate(new Date());
		repositoryEntry.setMappedId(mappedId);

		repositoryEntryPersistence.update(repositoryEntry);

		return repositoryEntry;
	}

}