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
import com.liferay.portal.upgrade.v6_2_0.UpgradeAnnouncements;
import com.liferay.portal.upgrade.v6_2_0.UpgradeAssetPublisher;
import com.liferay.portal.upgrade.v6_2_0.UpgradeBlogs;
import com.liferay.portal.upgrade.v6_2_0.UpgradeBlogsAggregator;
import com.liferay.portal.upgrade.v6_2_0.UpgradeCalendar;
import com.liferay.portal.upgrade.v6_2_0.UpgradeCompany;
import com.liferay.portal.upgrade.v6_2_0.UpgradeCustomizablePortlets;
import com.liferay.portal.upgrade.v6_2_0.UpgradeDocumentLibrary;
import com.liferay.portal.upgrade.v6_2_0.UpgradeDynamicDataListDisplay;
import com.liferay.portal.upgrade.v6_2_0.UpgradeDynamicDataMapping;
import com.liferay.portal.upgrade.v6_2_0.UpgradeGroup;
import com.liferay.portal.upgrade.v6_2_0.UpgradeImageGallery;
import com.liferay.portal.upgrade.v6_2_0.UpgradeJournal;
import com.liferay.portal.upgrade.v6_2_0.UpgradeLayout;
import com.liferay.portal.upgrade.v6_2_0.UpgradeLayoutFriendlyURL;
import com.liferay.portal.upgrade.v6_2_0.UpgradeLayoutRevision;
import com.liferay.portal.upgrade.v6_2_0.UpgradeLayoutSet;
import com.liferay.portal.upgrade.v6_2_0.UpgradeLayoutSetBranch;
import com.liferay.portal.upgrade.v6_2_0.UpgradeMessageBoards;
import com.liferay.portal.upgrade.v6_2_0.UpgradeMessageBoardsAttachments;
import com.liferay.portal.upgrade.v6_2_0.UpgradePortletItem;
import com.liferay.portal.upgrade.v6_2_0.UpgradePortletPreferences;
import com.liferay.portal.upgrade.v6_2_0.UpgradeRepository;
import com.liferay.portal.upgrade.v6_2_0.UpgradeSchema;
import com.liferay.portal.upgrade.v6_2_0.UpgradeSearch;
import com.liferay.portal.upgrade.v6_2_0.UpgradeSocial;
import com.liferay.portal.upgrade.v6_2_0.UpgradeUser;
import com.liferay.portal.upgrade.v6_2_0.UpgradeWiki;
import com.liferay.portal.upgrade.v6_2_0.UpgradeWikiAttachments;

/**
 * @author Raymond Augé
 * @author Juan Fernández
 */
public class UpgradeProcess_6_2_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_2_0_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeAnnouncements.class);
		upgrade(UpgradeAssetPublisher.class);
		upgrade(UpgradeBlogs.class);
		upgrade(UpgradeBlogsAggregator.class);
		upgrade(UpgradeCalendar.class);
		upgrade(UpgradeCompany.class);
		upgrade(UpgradeCustomizablePortlets.class);
		upgrade(UpgradeDocumentLibrary.class);
		upgrade(UpgradeDynamicDataListDisplay.class);
		upgrade(UpgradeDynamicDataMapping.class);
		upgrade(UpgradeGroup.class);
		upgrade(UpgradeImageGallery.class);
		upgrade(UpgradeJournal.class);
		upgrade(UpgradeLayout.class);
		upgrade(UpgradeLayoutFriendlyURL.class);
		upgrade(UpgradeLayoutRevision.class);
		upgrade(UpgradeLayoutSet.class);
		upgrade(UpgradeLayoutSetBranch.class);
		upgrade(UpgradeMessageBoards.class);
		upgrade(UpgradeMessageBoardsAttachments.class);
		upgrade(UpgradePortletItem.class);
		upgrade(UpgradePortletPreferences.class);
		upgrade(UpgradeRepository.class);
		upgrade(UpgradeSearch.class);
		upgrade(UpgradeSocial.class);
		upgrade(UpgradeUser.class);
		upgrade(UpgradeWiki.class);
		upgrade(UpgradeWikiAttachments.class);
	}

}