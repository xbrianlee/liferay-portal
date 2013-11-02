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

package com.liferay.portal.kernel.staging;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.LayoutSetStagingHandler;
import com.liferay.portal.model.LayoutStagingHandler;

/**
 * @author Raymond Augé
 */
public class LayoutStagingUtil {

	public static LayoutRevision getLayoutRevision(Layout layout) {
		return getLayoutStaging().getLayoutRevision(layout);
	}

	public static LayoutSetBranch getLayoutSetBranch(LayoutSet layoutSet) {
		return getLayoutStaging().getLayoutSetBranch(layoutSet);
	}

	public static LayoutSetStagingHandler getLayoutSetStagingHandler(
		LayoutSet layoutSet) {

		return getLayoutStaging().getLayoutSetStagingHandler(layoutSet);
	}

	public static LayoutStaging getLayoutStaging() {
		PortalRuntimePermission.checkGetBeanProperty(LayoutStagingUtil.class);

		return _layoutStaging;
	}

	public static LayoutStagingHandler getLayoutStagingHandler(Layout layout) {
		return getLayoutStaging().getLayoutStagingHandler(layout);
	}

	public static boolean isBranchingLayout(Layout layout) {
		return getLayoutStaging().isBranchingLayout(layout);
	}

	public static boolean isBranchingLayoutSet(
		Group group, boolean privateLayout) {

		return getLayoutStaging().isBranchingLayoutSet(group, privateLayout);
	}

	public void setLayoutStaging(LayoutStaging layoutStaging) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutStaging = layoutStaging;
	}

	private static LayoutStaging _layoutStaging;

}