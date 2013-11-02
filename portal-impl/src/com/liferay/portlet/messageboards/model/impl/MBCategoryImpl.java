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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MBCategoryImpl extends MBCategoryBaseImpl {

	public MBCategoryImpl() {
	}

	@Override
	public List<Long> getAncestorCategoryIds()
		throws PortalException, SystemException {

		List<Long> ancestorCategoryIds = new ArrayList<Long>();

		MBCategory category = this;

		while (!category.isRoot()) {
			category = MBCategoryLocalServiceUtil.getCategory(
				category.getParentCategoryId());

			ancestorCategoryIds.add(category.getCategoryId());
		}

		return ancestorCategoryIds;
	}

	@Override
	public List<MBCategory> getAncestors()
		throws PortalException, SystemException {

		List<MBCategory> ancestors = new ArrayList<MBCategory>();

		MBCategory category = this;

		while (!category.isRoot()) {
			category = category.getParentCategory();

			ancestors.add(category);
		}

		return ancestors;
	}

	@Override
	public MBCategory getParentCategory()
		throws PortalException, SystemException {

		long parentCategoryId = getParentCategoryId();

		if ((parentCategoryId ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ||
			(parentCategoryId == MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			return null;
		}

		return MBCategoryLocalServiceUtil.getCategory(getParentCategoryId());
	}

	@Override
	public boolean isRoot() {
		if (getParentCategoryId() ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			return true;
		}

		return false;
	}

}