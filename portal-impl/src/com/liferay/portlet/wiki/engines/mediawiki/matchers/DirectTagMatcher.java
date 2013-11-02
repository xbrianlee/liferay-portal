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
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.regex.MatchResult;

/**
 * @author Kenneth Chang
 */
public class DirectTagMatcher extends CallbackMatcher {

	public DirectTagMatcher(WikiPage page) {
		_page = page;

		setRegex(_REGEX);
	}

	public String replaceMatches(CharSequence charSequence) {
		return replaceMatches(charSequence, _callBack);
	}

	private static final String _REGEX = "\\[\\[([^\\]]+)\\]\\]";

	private Callback _callBack = new Callback() {

		@Override
		public String foundMatch(MatchResult matchResult) {
			String fileName = matchResult.group(1);

			if (!fileName.contains(StringPool.UNDERLINE)) {
				return null;
			}

			if (fileName.indexOf(CharPool.PIPE) >= 0) {
				fileName = StringUtil.extractFirst(fileName, CharPool.PIPE);
			}

			try {
				for (FileEntry fileEntry : _page.getAttachmentsFileEntries()) {
					if (!fileName.equals(fileEntry.getTitle())) {
						continue;
					}

					fileName = StringUtil.replace(
						fileName, StringPool.UNDERLINE, "%5F");

					return StringUtil.replace(
						matchResult.group(0), matchResult.group(1), fileName);
				}
			}
			catch (Exception e) {
			}

			return null;
		}

	};

	private WikiPage _page;

}