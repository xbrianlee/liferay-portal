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

package com.liferay.portlet.wiki.translators;

import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseTranslator {

	public String translate(String content) {
		_protectedMap.clear();

		content = preProcess(content);
		content = runRegexps(content);
		content = postProcess(content);

		return content;
	}

	protected String postProcess(String content) {
		return unprotectNowikiText(content);
	}

	protected String preProcess(String content) {
		content = _normalizeLineBreaks(content);

		for (String regexp : nowikiRegexps) {
			content = protectText(content, regexp);
		}

		return content;
	}

	protected String protectText(String content, String markupRegex) {
		Pattern pattern = Pattern.compile(
			markupRegex, Pattern.MULTILINE | Pattern.DOTALL);

		Matcher matcher = pattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String protectedText = matcher.group();

			String hash = DigesterUtil.digest(protectedText);

			matcher.appendReplacement(sb, "$1" + hash + "$3");

			_protectedMap.put(hash, matcher.group(2));
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String runRegexp(
		String content, String regexp, String replacement) {

		Pattern pattern = Pattern.compile(regexp, Pattern.MULTILINE);

		Matcher matcher = pattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(sb, replacement);
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String runRegexps(String content) {
		for (Map.Entry<String, String> entry : regexps.entrySet()) {
			String regexp = entry.getKey();
			String replacement = entry.getValue();

			content = runRegexp(content, regexp, replacement);
		}

		return content;
	}

	protected String unprotectNowikiText(String content) {
		for (Map.Entry<String, String> entry : _protectedMap.entrySet()) {
			String hash = entry.getKey();
			String protectedMarkup = entry.getValue();

			content = content.replace(hash, protectedMarkup);
		}

		return content;
	}

	protected List<String> nowikiRegexps = new LinkedList<String>();
	protected Map<String, String> regexps = new LinkedHashMap<String, String>();

	private String _normalizeLineBreaks(String content) {
		content = StringUtil.replace(
			content,
			new String[] {StringPool.RETURN_NEW_LINE, StringPool.RETURN},
			new String[] {StringPool.NEW_LINE, StringPool.NEW_LINE});

		return content;
	}

	private Map<String, String> _protectedMap =
		new LinkedHashMap<String, String>();

}