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

package com.liferay.portlet.blogs.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portlet.blogs.NoSuchStatsUserException;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.model.BlogsStatsUser;
import com.liferay.portlet.blogs.service.base.BlogsStatsUserLocalServiceBaseImpl;
import com.liferay.portlet.blogs.util.comparator.EntryDisplayDateComparator;
import com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
public class BlogsStatsUserLocalServiceImpl
	extends BlogsStatsUserLocalServiceBaseImpl {

	@Override
	public void deleteStatsUser(BlogsStatsUser statsUsers)
		throws SystemException {

		blogsStatsUserPersistence.remove(statsUsers);
	}

	@Override
	public void deleteStatsUser(long statsUserId)
		throws PortalException, SystemException {

		BlogsStatsUser statsUsers = blogsStatsUserPersistence.findByPrimaryKey(
			statsUserId);

		deleteStatsUser(statsUsers);
	}

	@Override
	public void deleteStatsUserByGroupId(long groupId) throws SystemException {
		List<BlogsStatsUser> statsUsers =
			blogsStatsUserPersistence.findByGroupId(groupId);

		for (BlogsStatsUser statsUser : statsUsers) {
			deleteStatsUser(statsUser);
		}
	}

	@Override
	public void deleteStatsUserByUserId(long userId) throws SystemException {
		List<BlogsStatsUser> statsUsers =
			blogsStatsUserPersistence.findByUserId(userId);

		for (BlogsStatsUser statsUser : statsUsers) {
			deleteStatsUser(statsUser);
		}
	}

	@Override
	public List<BlogsStatsUser> getCompanyStatsUsers(
			long companyId, int start, int end)
		throws SystemException {

		return blogsStatsUserPersistence.findByC_NotE(
			companyId, 0, start, end, new StatsUserLastPostDateComparator());
	}

	@Override
	public List<BlogsStatsUser> getCompanyStatsUsers(
			long companyId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return blogsStatsUserPersistence.findByC_NotE(
			companyId, 0, start, end, obc);
	}

	@Override
	public int getCompanyStatsUsersCount(long companyId)
		throws SystemException {

		return blogsStatsUserPersistence.countByC_NotE(companyId, 0);
	}

	@Override
	public List<BlogsStatsUser> getGroupsStatsUsers(
			long companyId, long groupId, int start, int end)
		throws SystemException {

		return blogsStatsUserFinder.findByGroupIds(
			companyId, groupId, start, end);
	}

	@Override
	public List<BlogsStatsUser> getGroupStatsUsers(
			long groupId, int start, int end)
		throws SystemException {

		return blogsStatsUserPersistence.findByG_NotE(
			groupId, 0, start, end, new StatsUserLastPostDateComparator());
	}

	@Override
	public List<BlogsStatsUser> getGroupStatsUsers(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return blogsStatsUserPersistence.findByG_NotE(
			groupId, 0, start, end, obc);
	}

	@Override
	public int getGroupStatsUsersCount(long groupId) throws SystemException {
		return blogsStatsUserPersistence.countByG_NotE(groupId, 0);
	}

	@Override
	public List<BlogsStatsUser> getOrganizationStatsUsers(
			long organizationId, int start, int end)
		throws SystemException {

		return blogsStatsUserFinder.findByOrganizationId(
			organizationId, start, end, new StatsUserLastPostDateComparator());
	}

	@Override
	public List<BlogsStatsUser> getOrganizationStatsUsers(
			long organizationId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return blogsStatsUserFinder.findByOrganizationId(
			organizationId, start, end, obc);
	}

	@Override
	public int getOrganizationStatsUsersCount(long organizationId)
		throws SystemException {

		return blogsStatsUserFinder.countByOrganizationId(organizationId);
	}

	@Override
	public BlogsStatsUser getStatsUser(long groupId, long userId)
		throws PortalException, SystemException {

		BlogsStatsUser statsUser = blogsStatsUserPersistence.fetchByG_U(
			groupId, userId);

		if (statsUser == null) {
			Group group = groupPersistence.findByPrimaryKey(groupId);

			long statsUserId = counterLocalService.increment();

			statsUser = blogsStatsUserPersistence.create(statsUserId);

			statsUser.setCompanyId(group.getCompanyId());
			statsUser.setGroupId(groupId);
			statsUser.setUserId(userId);

			blogsStatsUserPersistence.update(statsUser);
		}

		return statsUser;
	}

	@Override
	public void updateStatsUser(long groupId, long userId)
		throws PortalException, SystemException {

		updateStatsUser(groupId, userId, null);
	}

	@Override
	public void updateStatsUser(long groupId, long userId, Date displayDate)
		throws PortalException, SystemException {

		Date now = new Date();

		int entryCount = blogsEntryPersistence.countByG_U_LtD_S(
			groupId, userId, now, WorkflowConstants.STATUS_APPROVED);

		if (entryCount == 0) {
			try {
				blogsStatsUserPersistence.removeByG_U(groupId, userId);
			}
			catch (NoSuchStatsUserException nssue) {
			}

			return;
		}

		BlogsStatsUser statsUser = getStatsUser(groupId, userId);

		statsUser.setEntryCount(entryCount);

		BlogsEntry blogsEntry = blogsEntryPersistence.findByG_U_LtD_S_First(
			groupId, userId, now, WorkflowConstants.STATUS_APPROVED,
			new EntryDisplayDateComparator());

		Date lastDisplayDate = blogsEntry.getDisplayDate();

		Date lastPostDate = statsUser.getLastPostDate();

		if ((displayDate != null) && displayDate.before(now)) {
			if (lastPostDate == null) {
				statsUser.setLastPostDate(displayDate);
			}
			else if (displayDate.after(lastPostDate)) {
				statsUser.setLastPostDate(displayDate);
			}
			else if (lastDisplayDate.before(lastPostDate)) {
				statsUser.setLastPostDate(lastDisplayDate);
			}
		}
		else if ((lastPostDate == null) ||
				 lastPostDate.before(lastDisplayDate)) {

			statsUser.setLastPostDate(lastDisplayDate);
		}

		blogsStatsUserPersistence.update(statsUser);
	}

}