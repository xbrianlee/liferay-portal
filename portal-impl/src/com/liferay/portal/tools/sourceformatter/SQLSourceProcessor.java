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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class SQLSourceProcessor extends BaseSourceProcessor {

	@Override
	protected void format() throws Exception {
		String[] includes = new String[] {"**\\sql\\*.sql"};

		List<String> fileNames = getFileNames(new String[0], includes);

		for (String fileName : fileNames) {
			format(fileName);
		}
	}

	@Override
	protected String format(String fileName) throws Exception {
		File file = new File(BASEDIR + fileName);

		String content = fileUtil.read(file);

		String newContent = formatSQL(content);

		if (isAutoFix() && (newContent != null) &&
			!content.equals(newContent)) {

			fileUtil.write(file, newContent);

			fileName = StringUtil.replace(
				fileName, StringPool.BACK_SLASH, StringPool.SLASH);

			sourceFormatterHelper.printError(fileName, file);
		}

		return newContent;
	}

	protected String formatSQL(String content) throws IOException {
		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(content));

		String line = null;

		String previousLineSqlCommand = StringPool.BLANK;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			line = trimLine(line, false);

			if (Validator.isNotNull(line) && !line.startsWith(StringPool.TAB)) {
				String sqlCommand = StringUtil.split(line, CharPool.SPACE)[0];

				if (Validator.isNotNull(previousLineSqlCommand) &&
					!previousLineSqlCommand.equals(sqlCommand)) {

					sb.append("\n");
				}

				previousLineSqlCommand = sqlCommand;
			}
			else {
				previousLineSqlCommand = StringPool.BLANK;
			}

			String strippedQuotesLine = stripQuotes(line, CharPool.APOSTROPHE);

			if (strippedQuotesLine.contains(StringPool.QUOTE)) {
				line = StringUtil.replace(
					line, StringPool.QUOTE, StringPool.APOSTROPHE);
			}

			sb.append(line);
			sb.append("\n");
		}

		unsyncBufferedReader.close();

		content = sb.toString();

		if (content.endsWith("\n")) {
			content = content.substring(0, content.length() - 1);
		}

		return content;
	}

}