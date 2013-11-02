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
import com.liferay.portal.upgrade.v6_0_3.UpgradeAsset;
import com.liferay.portal.upgrade.v6_0_3.UpgradeAssetPublisher;
import com.liferay.portal.upgrade.v6_0_3.UpgradeDocumentLibrary;
import com.liferay.portal.upgrade.v6_0_3.UpgradeLayout;
import com.liferay.portal.upgrade.v6_0_3.UpgradeLookAndFeel;
import com.liferay.portal.upgrade.v6_0_3.UpgradePermission;
import com.liferay.portal.upgrade.v6_0_3.UpgradeSchema;
import com.liferay.portal.upgrade.v6_0_3.UpgradeScopes;
import com.liferay.portal.upgrade.v6_0_3.UpgradeSitemap;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeProcess_6_0_3 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_0_3_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeAsset.class);
		upgrade(UpgradeAssetPublisher.class);
		upgrade(UpgradeDocumentLibrary.class);
		upgrade(UpgradeLayout.class);
		upgrade(UpgradeLookAndFeel.class);
		upgrade(UpgradePermission.class);
		upgrade(UpgradeScopes.class);
		upgrade(UpgradeSitemap.class);
	}

}