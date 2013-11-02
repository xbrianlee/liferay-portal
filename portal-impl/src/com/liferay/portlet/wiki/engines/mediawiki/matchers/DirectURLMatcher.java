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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.CallbackMatcher;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.regex.MatchResult;

/**
 * @author Kenneth Chang
 */
public class DirectURLMatcher extends CallbackMatcher {

	public DirectURLMatcher(WikiPage page, String attachmentURLPrefix) {
		_page = page;
		_attachmentURLPrefix = attachmentURLPrefix;

		setRegex(_URL_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callBack);
	}

	private static final String _URL_REGEX =
		"<a href=\"[^\"]*?Special:Edit[^\"]*?topic=[^\"]*?\".*?title=\"" +
			"([^\"]*?)\".*?>(.*?)</a>";

	private String _attachmentURLPrefix;

	private Callback _callBack = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String fileName = StringUtil.replace(
				matchResult.group(1), "%5F", StringPool.UNDERLINE);
			String title = StringUtil.replace(
				matchResult.group(2), "%5F", StringPool.UNDERLINE);

			if (Validator.isNull(title)) {
				title = fileName;
			}

			String url = _attachmentURLPrefix + HttpUtil.encodeURL(fileName);

			try {
				for (FileEntry fileEntry : _page.getAttachmentsFileEntries()) {
					if (!fileName.equals(fileEntry.getTitle())) {
						continue;
					}

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"");
					sb.append(url);
					sb.append("\">");
					sb.append(title);
					sb.append("</a>");

					return sb.toString();
				}
			}
			catch (Exception e) {
			}

			return null;
		}

	};

	private WikiPage _page;

}