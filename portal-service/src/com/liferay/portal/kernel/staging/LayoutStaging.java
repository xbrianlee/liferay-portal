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
public interface LayoutStaging {

	public LayoutRevision getLayoutRevision(Layout layout);

	public LayoutSetBranch getLayoutSetBranch(LayoutSet layoutSet);

	public LayoutSetStagingHandler getLayoutSetStagingHandler(
		LayoutSet layoutSet);

	public LayoutStagingHandler getLayoutStagingHandler(Layout layout);

	public boolean isBranchingLayout(Layout layout);

	public boolean isBranchingLayoutSet(Group group, boolean privateLayout);

}