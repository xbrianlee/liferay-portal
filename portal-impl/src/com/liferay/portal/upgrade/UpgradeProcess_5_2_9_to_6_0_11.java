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

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.upgrade.v5_2_9_to_6_0_11.UpgradeDocumentLibrary;
import com.liferay.portal.upgrade.v5_2_9_to_6_0_11.UpgradeSchema;
import com.liferay.portal.upgrade.v6_0_0.UpgradeAsset;
import com.liferay.portal.upgrade.v6_0_0.UpgradeAssetPublisher;
import com.liferay.portal.upgrade.v6_0_0.UpgradeBlogs;
import com.liferay.portal.upgrade.v6_0_0.UpgradeExpando;
import com.liferay.portal.upgrade.v6_0_0.UpgradePolls;
import com.liferay.portal.upgrade.v6_0_0.UpgradePortletId;
import com.liferay.portal.upgrade.v6_0_0.UpgradeShopping;
import com.liferay.portal.upgrade.v6_0_2.UpgradeNestedPortlets;
import com.liferay.portal.upgrade.v6_0_3.UpgradeLookAndFeel;
import com.liferay.portal.upgrade.v6_0_3.UpgradePermission;
import com.liferay.portal.upgrade.v6_0_3.UpgradeScopes;
import com.liferay.portal.upgrade.v6_0_3.UpgradeSitemap;
import com.liferay.portal.upgrade.v6_0_5.UpgradeJournal;
import com.liferay.portal.upgrade.v6_0_5.UpgradeLayout;
import com.liferay.portal.upgrade.v6_0_6.UpgradeRSS;

/**
 * @author Douglas Wong
 */
public class UpgradeProcess_5_2_9_to_6_0_11 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_0_11_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeAsset.class);
		upgrade(UpgradeAssetPublisher.class);
		upgrade(UpgradeBlogs.class);
		upgrade(UpgradeDocumentLibrary.class);
		upgrade(UpgradeExpando.class);
		upgrade(UpgradePolls.class);
		upgrade(UpgradePortletId.class);
		upgrade(UpgradeShopping.class);

		upgrade(com.liferay.portal.upgrade.v6_0_2.UpgradeExpando.class);
		upgrade(UpgradeNestedPortlets.class);

		upgrade(com.liferay.portal.upgrade.v6_0_3.UpgradeAsset.class);
		upgrade(com.liferay.portal.upgrade.v6_0_3.UpgradeAssetPublisher.class);
		upgrade(com.liferay.portal.upgrade.v6_0_3.UpgradeDocumentLibrary.class);
		upgrade(UpgradeLookAndFeel.class);
		upgrade(UpgradePermission.class);
		upgrade(UpgradeScopes.class);
		upgrade(UpgradeSitemap.class);

		upgrade(UpgradeJournal.class);
		upgrade(UpgradeLayout.class);

		upgrade(UpgradeRSS.class);

		upgrade(
			com.liferay.portal.upgrade.v6_0_11.UpgradeDocumentLibrary.class);
	}

}