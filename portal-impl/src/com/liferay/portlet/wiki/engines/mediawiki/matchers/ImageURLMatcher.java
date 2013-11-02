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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.regex.MatchResult;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ImageURLMatcher extends CallbackMatcher {

	public ImageURLMatcher(String attachmentURLPrefix) {
		_attachmentURLPrefix = attachmentURLPrefix;

		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callBack);
	}

	private static final String _REGEX =
		"<a href=\"[^\"]*?Special:Upload[^\"]*?topic=Image:([^\"]*?)\".*?</a>";

	private String _attachmentURLPrefix;

	private Callback _callBack = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String title = StringUtil.replace(
				matchResult.group(1), "%5F", StringPool.UNDERLINE);

			String url = _attachmentURLPrefix + HttpUtil.encodeURL(title);

			StringBundler sb = new StringBundler(5);

			sb.append("<img alt=\"");
			sb.append(title);
			sb.append("\" class=\"wikiimg\" src=\"");
			sb.append(url);
			sb.append("\" />");

			return sb.toString();
		}

	};

}