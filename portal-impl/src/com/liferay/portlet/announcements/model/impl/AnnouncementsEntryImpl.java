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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class AnnouncementsEntryImpl extends AnnouncementsEntryBaseImpl {

	public AnnouncementsEntryImpl() {
	}

	@Override
	public long getGroupId() throws PortalException, SystemException {
		long groupId = 0;

		long classPK = getClassPK();

		if (classPK > 0) {
			String className = getClassName();

			if (className.equals(Group.class.getName())) {
				Group group = GroupLocalServiceUtil.getGroup(classPK);

				groupId = group.getGroupId();
			}
			else if (className.equals(Organization.class.getName())) {
				Organization organization =
					OrganizationLocalServiceUtil.getOrganization(classPK);

				groupId = organization.getGroupId();
			}
		}

		return groupId;
	}

}