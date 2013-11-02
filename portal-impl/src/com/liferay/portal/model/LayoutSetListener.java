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

package com.liferay.portal.model;

import com.liferay.portal.servlet.filters.cache.CacheUtil;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {

	@Override
	public void onAfterRemove(LayoutSet layoutSet) {
		clearCache(layoutSet);
	}

	@Override
	public void onAfterUpdate(LayoutSet layoutSet) {
		clearCache(layoutSet);
	}

	protected void clearCache(LayoutSet layoutSet) {
		if (layoutSet == null) {
			return;
		}

		if (!layoutSet.isPrivateLayout()) {
			CacheUtil.clearCache(layoutSet.getCompanyId());
		}
	}

}