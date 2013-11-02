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

package com.liferay.portlet.wiki.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.base.WikiPageResourceLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class WikiPageResourceLocalServiceImpl
	extends WikiPageResourceLocalServiceBaseImpl {

	@Override
	public WikiPageResource addPageResource(long nodeId, String title)
		throws SystemException {

		long pageResourcePrimKey = counterLocalService.increment();

		WikiPageResource pageResource = wikiPageResourcePersistence.create(
			pageResourcePrimKey);

		pageResource.setNodeId(nodeId);
		pageResource.setTitle(title);

		wikiPageResourcePersistence.update(pageResource);

		return pageResource;
	}

	@Override
	public void deletePageResource(long nodeId, String title)
		throws PortalException, SystemException {

		wikiPageResourcePersistence.removeByN_T(nodeId, title);
	}

	@Override
	public WikiPageResource fetchPageResource(long nodeId, String title)
		throws SystemException {

		return wikiPageResourcePersistence.fetchByN_T(nodeId, title);
	}

	@Override
	public WikiPageResource fetchPageResource(String uuid)
		throws SystemException {

		return wikiPageResourcePersistence.fetchByUuid_First(uuid, null);
	}

	@Override
	public WikiPageResource getPageResource(long pageResourcePrimKey)
		throws PortalException, SystemException {

		return wikiPageResourcePersistence.findByPrimaryKey(
			pageResourcePrimKey);
	}

	@Override
	public WikiPageResource getPageResource(long nodeId, String title)
		throws PortalException, SystemException {

		return wikiPageResourcePersistence.findByN_T(nodeId, title);
	}

	@Override
	public long getPageResourcePrimKey(long nodeId, String title)
		throws SystemException {

		WikiPageResource pageResource = wikiPageResourcePersistence.fetchByN_T(
			nodeId, title);

		if (pageResource == null) {
			long pageResourcePrimKey = counterLocalService.increment();

			pageResource = wikiPageResourcePersistence.create(
				pageResourcePrimKey);

			pageResource.setNodeId(nodeId);
			pageResource.setTitle(title);

			wikiPageResourcePersistence.update(pageResource);
		}

		return pageResource.getResourcePrimKey();
	}

}