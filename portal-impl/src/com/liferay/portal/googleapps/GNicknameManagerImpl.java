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

import com.liferay.portal.kernel.googleapps.GNickname;
import com.liferay.portal.kernel.googleapps.GNicknameManager;
import com.liferay.portal.kernel.googleapps.GoogleAppsException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class GNicknameManagerImpl
	extends GBaseManagerImpl implements GNicknameManager {

	public GNicknameManagerImpl(GoogleApps googleApps) {
		super(googleApps);

		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		StringBundler sb = new StringBundler(4);

		sb.append(APPS_URL);
		sb.append(StringPool.SLASH);
		sb.append(gAuthenticator.getDomain());
		sb.append("/nickname/2.0");

		nicknameURL = sb.toString();
	}

	@Override
	public void addGNickname(long userId, String nickname)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAtomCategory(atomEntryElement, "nickname");

		Element appsLoginElement = atomEntryElement.addElement("apps:login");

		appsLoginElement.addAttribute("userName", String.valueOf(userId));

		Element appsNicknameElement = atomEntryElement.addElement(
			"apps:nickname");

		appsNicknameElement.addAttribute("name", nickname);

		submitAdd(nicknameURL, document);
	}

	@Override
	public void deleteGNickname(String nickname) throws GoogleAppsException {
		submitDelete(getNicknameURL(nickname));
	}

	@Override
	public GNickname getGNickname(String nickname) throws GoogleAppsException {
		Document document = getDocument(getNicknameURL(nickname));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return null;
		}

		Element atomEntryElement = document.getRootElement();

		return getGNickname(atomEntryElement);
	}

	@Override
	public List<GNickname> getGNicknames() throws GoogleAppsException {
		List<GNickname> gNicknames = new ArrayList<GNickname>();

		getGNicknames(gNicknames, nicknameURL);

		return gNicknames;
	}

	protected GNickname getGNickname(Element atomEntryElement) {
		GNickname gNickname = new GNickname();

		Element appsLoginElement = atomEntryElement.element(
			getAppsQName("login"));
		Element appsNicknameElement = atomEntryElement.element(
			getAppsQName("nickname"));

		String nickname = appsNicknameElement.attributeValue("name");

		gNickname.setNickname(nickname);

		long userId = GetterUtil.getLong(
			appsLoginElement.attributeValue("userName"));

		gNickname.setUserId(userId);

		return gNickname;
	}

	protected void getGNicknames(final List<GNickname> gNicknames, String url)
		throws GoogleAppsException {

		Document document = getDocument(url);

		Element atomFeedElement = document.getRootElement();

		List<Element> atomEntryElements = atomFeedElement.elements(
			getAtomQName("entry"));

		for (Element atomEntryElement : atomEntryElements) {
			GNickname gNickname = getGNickname(atomEntryElement);

			gNicknames.add(gNickname);
		}

		new GetNextItems(url, atomFeedElement) {

			@Override
			public void getNextItems(String nextURL)
				throws GoogleAppsException {

				getGNicknames(gNicknames, nextURL);
			}

		};
	}

	protected String getNicknameURL(String nickname) {
		return nicknameURL.concat(StringPool.SLASH).concat(nickname);
	}

	protected String nicknameURL;

	private static Log _log = LogFactoryUtil.getLog(GNicknameManagerImpl.class);

}