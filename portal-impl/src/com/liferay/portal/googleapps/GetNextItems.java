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

import com.liferay.portal.kernel.googleapps.GoogleAppsException;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class GetNextItems {

	public GetNextItems(String url, Element atomFeedElement)
		throws GoogleAppsException {

		List<Element> atomLinkElements = atomFeedElement.elements(
			GHelperUtil.getAtomQName("link"));

		for (Element atomLinkElement : atomLinkElements) {
			String rel = atomLinkElement.attributeValue("rel");

			if (rel.equals("next")) {
				String href = atomLinkElement.attributeValue("href");

				if (!href.equals(url)) {
					getNextItems(href);
				}

				break;
			}
		}
	}

	public abstract void getNextItems(String nextURL)
		throws GoogleAppsException;

}