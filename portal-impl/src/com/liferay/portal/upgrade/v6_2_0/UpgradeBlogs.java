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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.upgrade.v6_2_0.util.BlogsEntryTable;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.RSSUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Sergio González
 * @author Eduardo Garcia
 */
public class UpgradeBlogs extends BaseUpgradePortletPreferences {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		updateEntries();
	}

	@Override
	protected String[] getPortletIds() {
		return new String[] {"33"};
	}

	protected void updateEntries() throws Exception {
		try {
			runSQL("alter_column_type BlogsEntry description STRING null");
		}
		catch (Exception e) {
			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				BlogsEntryTable.TABLE_NAME, BlogsEntryTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(BlogsEntryTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(BlogsEntryTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}
	}

	protected void upgradeDisplayStyle(PortletPreferences portletPreferences)
		throws Exception {

		String pageDisplayStyle = GetterUtil.getString(
			portletPreferences.getValue("pageDisplayStyle", null));

		if (Validator.isNotNull(pageDisplayStyle)) {
			portletPreferences.setValue("displayStyle", pageDisplayStyle);
		}

		portletPreferences.reset("pageDisplayStyle");
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradeDisplayStyle(portletPreferences);
		upgradeRss(portletPreferences);

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	protected void upgradeRss(PortletPreferences portletPreferences)
		throws Exception {

		String rssFormat = GetterUtil.getString(
			portletPreferences.getValue("rssFormat", null));

		if (Validator.isNotNull(rssFormat)) {
			String rssFormatType = RSSUtil.getFormatType(rssFormat);
			double rssFormatVersion = RSSUtil.getFormatVersion(rssFormat);

			String rssFeedType = RSSUtil.getFeedType(
				rssFormatType, rssFormatVersion);

			portletPreferences.setValue("rssFeedType", rssFeedType);
		}

		portletPreferences.reset("rssFormat");
	}

}