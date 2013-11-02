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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class CSSSourceProcessor extends BaseSourceProcessor {

	protected String fixComments(String content) {
		Matcher matcher = _commentPattern.matcher(content);

		while (matcher.find()) {
			String[] words = StringUtil.split(matcher.group(1), CharPool.SPACE);

			for (int i = 1; i < words.length; i++) {
				String previousWord = words[i - 1];

				if (previousWord.endsWith(StringPool.PERIOD) ||
					previousWord.equals(StringPool.SLASH)) {

					continue;
				}

				String word = words[i];

				if ((word.length() > 1) &&
					Character.isUpperCase(word.charAt(0)) &&
					StringUtil.isLowerCase(word.substring(1))) {

					content = StringUtil.replaceFirst(
						content, word, StringUtil.toLowerCase(word),
						matcher.start());
				}
			}
		}

		return content;
	}

	@Override
	protected void format() throws Exception {
		String[] excludes = {
			"**\\.sass-cache\\**", "**\\aui_deprecated.css", "**\\js\\aui\\**",
			"**\\js\\editor\\**", "**\\js\\misc\\**", "**\\VAADIN\\**"
		};
		String[] includes = {"**\\*.css"};

		List<String> fileNames = getFileNames(excludes, includes);

		for (String fileName : fileNames) {
			format(fileName);
		}
	}

	@Override
	protected String format(String fileName) throws Exception {
		File file = new File(BASEDIR + fileName);

		fileName = StringUtil.replace(
			fileName, StringPool.BACK_SLASH, StringPool.SLASH);

		String content = fileUtil.read(file);

		String newContent = trimContent(content, false);

		newContent = fixComments(newContent);

		if (isAutoFix() && (newContent != null) &&
			!content.equals(newContent)) {

			fileUtil.write(file, newContent);

			sourceFormatterHelper.printError(fileName, file);
		}

		return newContent;
	}

	private Pattern _commentPattern = Pattern.compile("/\\* -+(.+)-+ \\*/");

}