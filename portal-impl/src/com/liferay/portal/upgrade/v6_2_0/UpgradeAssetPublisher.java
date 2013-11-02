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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.assetpublisher.util.AssetPublisher;
import com.liferay.util.RSSUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Garcia
 * @author Jorge Ferrer
 */
public class UpgradeAssetPublisher extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {"101_INSTANCE_%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradeRss(portletPreferences);
		upgradeScopeIds(portletPreferences);

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	protected void upgradeRss(PortletPreferences portletPreferences)
		throws Exception {

		String rssFormat = GetterUtil.getString(
			portletPreferences.getValue("rssFormat", null));

		if (Validator.isNotNull(rssFormat)) {
			portletPreferences.setValue(
				"rssFeedType",
				RSSUtil.getFeedType(
					RSSUtil.getFormatType(rssFormat),
					RSSUtil.getFormatVersion(rssFormat)));
		}

		portletPreferences.reset("rssFormat");
	}

	protected void upgradeScopeIds(PortletPreferences portletPreferences)
		throws Exception {

		String defaultScope = GetterUtil.getString(
			portletPreferences.getValue("defaultScope", null));

		if (Validator.isNull(defaultScope)) {
			return;
		}

		if (defaultScope.equals("true")) {
			portletPreferences.setValues(
				"scopeIds",
				new String[] {
					AssetPublisher.SCOPE_ID_GROUP_PREFIX +
						GroupConstants.DEFAULT
				});
		}
		else if (!defaultScope.equals("false")) {
			portletPreferences.setValues(
				"scopeIds", new String[] {defaultScope});
		}

		portletPreferences.reset("defaultScope");
	}

}