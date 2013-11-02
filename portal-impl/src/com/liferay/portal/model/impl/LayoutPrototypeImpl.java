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

package com.liferay.portal.model.impl;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class LayoutPrototypeImpl extends LayoutPrototypeBaseImpl {

	public LayoutPrototypeImpl() {
	}

	@Override
	public Group getGroup() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getLayoutPrototypeGroup(
			getCompanyId(), getLayoutPrototypeId());
	}

	@Override
	public long getGroupId() throws PortalException, SystemException {
		Group group = getGroup();

		return group.getGroupId();
	}

	@Override
	public Layout getLayout() throws PortalException, SystemException {
		Group group = getGroup();

		if (group.getPrivateLayoutsPageCount() > 0) {
			List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
				group.getGroupId(), true);

			return layouts.get(0);
		}

		throw new NoSuchLayoutException();
	}

}