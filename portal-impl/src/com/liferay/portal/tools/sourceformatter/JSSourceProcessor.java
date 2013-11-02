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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JSSourceProcessor extends BaseSourceProcessor {

	@Override
	protected void format() throws Exception {
		String[] excludes = {
			"**\\js\\aui\\**", "**\\js\\editor\\**", "**\\js\\misc\\**",
			"**\\tools\\**", "**\\VAADIN\\**"
		};
		String[] includes = {"**\\*.js"};

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

		newContent = StringUtil.replace(
			newContent,
			new String[] {
				"else{", "for(", "function (", "if(", "while(", "){\n",
				"= new Array();", "= new Object();"
			},
			new String[] {
				"else {", "for (", "function(", "if (", "while (", ") {\n",
				"= [];", "= {};"
			});

		while (true) {
			Matcher matcher = _multipleVarsOnSingleLinePattern.matcher(
				newContent);

			if (!matcher.find()) {
				break;
			}

			String match = matcher.group();

			int pos = match.indexOf("var ");

			StringBundler sb = new StringBundler(4);

			sb.append(match.substring(0, match.length() - 2));
			sb.append(StringPool.SEMICOLON);
			sb.append("\n");
			sb.append(match.substring(0, pos + 4));

			newContent = StringUtil.replace(newContent, match, sb.toString());
		}

		if (newContent.endsWith("\n")) {
			newContent = newContent.substring(0, newContent.length() - 1);
		}

		checkLanguageKeys(fileName, newContent, languageKeyPattern);

		if (isAutoFix() && (newContent != null) &&
			!content.equals(newContent)) {

			fileUtil.write(file, newContent);

			sourceFormatterHelper.printError(fileName, file);
		}

		return newContent;
	}

	private Pattern _multipleVarsOnSingleLinePattern = Pattern.compile(
		"\t+var \\w+\\, ");

}