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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.model.JournalFeed;
import com.liferay.portlet.journal.service.base.JournalFeedServiceBaseImpl;
import com.liferay.portlet.journal.service.permission.JournalFeedPermission;
import com.liferay.portlet.journal.service.permission.JournalPermission;

/**
 * @author Raymond Augé
 */
public class JournalFeedServiceImpl extends JournalFeedServiceBaseImpl {

	@Override
	public JournalFeed addFeed(
			long groupId, String feedId, boolean autoFeedId, String name,
			String description, String type, String structureId,
			String templateId, String rendererTemplateId, int delta,
			String orderByCol, String orderByType,
			String targetLayoutFriendlyUrl, String targetPortletId,
			String contentField, String feedType, double feedVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_FEED);

		return journalFeedLocalService.addFeed(
			getUserId(), groupId, feedId, autoFeedId, name, description, type,
			structureId, templateId, rendererTemplateId, delta, orderByCol,
			orderByType, targetLayoutFriendlyUrl, targetPortletId, contentField,
			feedType, feedVersion, serviceContext);
	}

	@Override
	public void deleteFeed(long feedId)
		throws PortalException, SystemException {

		JournalFeedPermission.check(
			getPermissionChecker(), feedId, ActionKeys.DELETE);

		journalFeedLocalService.deleteFeed(feedId);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #deleteFeed(long, String)}
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public void deleteFeed(long groupId, long feedId)
		throws PortalException, SystemException {

		deleteFeed(groupId, String.valueOf(feedId));
	}

	@Override
	public void deleteFeed(long groupId, String feedId)
		throws PortalException, SystemException {

		JournalFeedPermission.check(
			getPermissionChecker(), groupId, feedId, ActionKeys.DELETE);

		journalFeedLocalService.deleteFeed(groupId, feedId);
	}

	@Override
	public JournalFeed getFeed(long feedId)
		throws PortalException, SystemException {

		JournalFeedPermission.check(
			getPermissionChecker(), feedId, ActionKeys.VIEW);

		return journalFeedLocalService.getFeed(feedId);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getFeed(long, String)}
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public JournalFeed getFeed(long groupId, long feedId)
		throws PortalException, SystemException {

		return getFeed(groupId, String.valueOf(feedId));
	}

	@Override
	public JournalFeed getFeed(long groupId, String feedId)
		throws PortalException, SystemException {

		JournalFeedPermission.check(
			getPermissionChecker(), groupId, feedId, ActionKeys.VIEW);

		return journalFeedLocalService.getFeed(groupId, feedId);
	}

	@Override
	public JournalFeed updateFeed(
			long groupId, String feedId, String name, String description,
			String type, String structureId, String templateId,
			String rendererTemplateId, int delta, String orderByCol,
			String orderByType, String targetLayoutFriendlyUrl,
			String targetPortletId, String contentField, String feedType,
			double feedVersion, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalFeedPermission.check(
			getPermissionChecker(), groupId, feedId, ActionKeys.UPDATE);

		return journalFeedLocalService.updateFeed(
			groupId, feedId, name, description, type, structureId, templateId,
			rendererTemplateId, delta, orderByCol, orderByType,
			targetLayoutFriendlyUrl, targetPortletId, contentField, feedType,
			feedVersion, serviceContext);
	}

}