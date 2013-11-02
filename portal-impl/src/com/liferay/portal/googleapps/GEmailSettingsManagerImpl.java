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

package com.liferay.portal.googleapps;

import com.liferay.portal.kernel.googleapps.GEmailSettingsManager;
import com.liferay.portal.kernel.googleapps.GoogleAppsException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class GEmailSettingsManagerImpl
	extends GBaseManagerImpl implements GEmailSettingsManager {

	public GEmailSettingsManagerImpl(GoogleApps googleApps) {
		super(googleApps);

		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		emailSettingsURL = APPS_URL.concat(
			"/emailsettings/2.0/").concat(gAuthenticator.getDomain());
	}

	@Override
	public void addSendAs(long userId, String fullName, String emailAddress)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAppsProperty(atomEntryElement, "name", fullName);
		addAppsProperty(atomEntryElement, "address", emailAddress);
		addAppsProperty(
			atomEntryElement, "makeDefault", Boolean.TRUE.toString());

		submitAdd(getEmailSettingsURL(userId).concat("/sendas"), document);
	}

	protected String getEmailSettingsURL(long userId) {
		return emailSettingsURL.concat(StringPool.SLASH).concat(
			String.valueOf(userId));
	}

	protected String emailSettingsURL;

}