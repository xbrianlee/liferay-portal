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

import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.regex.MatchResult;

/**
 * @author Shinn Lok
 */
public class ImageTagMatcher extends CallbackMatcher {

	public ImageTagMatcher() {
		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callBack);
	}

	private static final String _REGEX = "\\[\\[Image:[^\\]]+\\]\\]";

	private Callback _callBack = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String title = matchResult.group(0);

			title = StringUtil.replace(title, StringPool.UNDERLINE, "%5F");

			return title;
		}

	};

}