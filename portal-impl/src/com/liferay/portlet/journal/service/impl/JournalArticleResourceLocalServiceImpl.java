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

package com.liferay.portlet.journal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.base.JournalArticleResourceLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalArticleResourceLocalServiceImpl
	extends JournalArticleResourceLocalServiceBaseImpl {

	@Override
	public void deleteArticleResource(long groupId, String articleId)
		throws PortalException, SystemException {

		journalArticleResourcePersistence.removeByG_A(groupId, articleId);
	}

	@Override
	public JournalArticleResource fetchArticleResource(
			long groupId, String articleId)
		throws SystemException {

		return journalArticleResourcePersistence.fetchByG_A(groupId, articleId);
	}

	@Override
	public JournalArticleResource fetchArticleResource(
			String uuid, long groupId)
		throws SystemException {

		return journalArticleResourcePersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public JournalArticleResource getArticleResource(
			long articleResourcePrimKey)
		throws PortalException, SystemException {

		return journalArticleResourcePersistence.findByPrimaryKey(
			articleResourcePrimKey);
	}

	@Override
	public long getArticleResourcePrimKey(long groupId, String articleId)
		throws SystemException {

		return getArticleResourcePrimKey(null, groupId, articleId);
	}

	@Override
	public long getArticleResourcePrimKey(
			String uuid, long groupId, String articleId)
		throws SystemException {

		JournalArticleResource articleResource = null;

		if (Validator.isNotNull(uuid)) {
			articleResource = journalArticleResourcePersistence.fetchByUUID_G(
				uuid, groupId);
		}

		if (articleResource == null) {
			articleResource = journalArticleResourcePersistence.fetchByG_A(
				groupId, articleId);
		}

		if (articleResource == null) {
			long articleResourcePrimKey = counterLocalService.increment();

			articleResource = journalArticleResourcePersistence.create(
				articleResourcePrimKey);

			if (Validator.isNotNull(uuid)) {
				articleResource.setUuid(uuid);
			}

			articleResource.setGroupId(groupId);
			articleResource.setArticleId(articleId);

			journalArticleResourcePersistence.update(articleResource);
		}

		return articleResource.getResourcePrimKey();
	}

	@Override
	public List<JournalArticleResource> getArticleResources(long groupId)
		throws SystemException {

		return journalArticleResourcePersistence.findByGroupId(groupId);
	}

}