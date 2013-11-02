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

package com.liferay.portlet.wiki.engines.mediawiki.matchers;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.CharPool;

import java.util.regex.MatchResult;

import javax.portlet.PortletURL;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class PortletURLMatcher extends CallbackMatcher {

	public PortletURLMatcher(PortletURL portletURL) {
		_portletURL = portletURL;

		LiferayPortletURL liferayPortletURL = (LiferayPortletURL)portletURL;

		liferayPortletURL.setParameter("title", _TITLE_PLACEHOLDER, false);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callBack);
	}

	private static final String _TITLE_PLACEHOLDER = "__TITLE_PLACEHOLDER__";

	private Callback _callBack = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String portletURLString = _portletURL.toString();

			String title = matchResult.group(1);

			title = title.replace(CharPool.UNDERLINE, CharPool.PLUS);

			String url = portletURLString.replace(_TITLE_PLACEHOLDER, title);

			return "<a href=\"" + url + "\"";
		}

	};

	private PortletURL _portletURL;

}