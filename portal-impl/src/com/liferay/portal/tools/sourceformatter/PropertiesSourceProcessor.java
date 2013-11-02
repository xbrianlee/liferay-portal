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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.ContentUtil;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class PropertiesSourceProcessor extends BaseSourceProcessor {

	@Override
	protected void format() throws Exception {
		formatPortalProperties();
	}

	protected void formatPortalPortalProperties(File file, String fileName)
		throws Exception {

		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(_portalPortalProperties));

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			line = trimLine(line, true);

			if (line.startsWith(StringPool.TAB)) {
				line = line.replaceFirst(
					StringPool.TAB, StringPool.FOUR_SPACES);
			}

			sb.append(line);
			sb.append("\n");
		}

		unsyncBufferedReader.close();

		String newContent = sb.toString();

		if (newContent.endsWith("\n")) {
			newContent = newContent.substring(0, newContent.length() - 1);
		}

		if (isAutoFix() && (newContent != null) &&
			!_portalPortalProperties.equals(newContent)) {

			fileUtil.write(file, newContent);

			sourceFormatterHelper.printError(fileName, file);
		}
	}

	protected void formatPortalProperties() throws Exception {
		if (portalSource) {
			String portalPortalPropertiesfileName =
				"portal-impl/src/portal.properties";

			File portalPortalPropertiesFile = new File(
				BASEDIR + portalPortalPropertiesfileName);

			_portalPortalProperties = fileUtil.read(portalPortalPropertiesFile);

			formatPortalPortalProperties(
				portalPortalPropertiesFile, portalPortalPropertiesfileName);
		}
		else {
			_portalPortalProperties = ContentUtil.get("portal.properties");
		}

		String[] excludes = null;
		String[] includes = null;

		if (portalSource) {
			excludes = new String[] {"**\\bin\\**", "**\\classes\\**"};
			includes = new String[] {
				"**\\portal-ext.properties", "**\\portal-legacy-*.properties"
			};
		}
		else {
			excludes = new String[0];
			includes = new String[] {
				"**\\portal.properties", "**\\portal-ext.properties"
			};
		}

		List<String> fileNames = getFileNames(excludes, includes);

		for (String fileName : fileNames) {
			File file = new File(BASEDIR + fileName);

			fileName = StringUtil.replace(
				fileName, StringPool.BACK_SLASH, StringPool.SLASH);

			String content = fileUtil.read(file);

			formatPortalProperties(fileName, content);
		}
	}

	protected void formatPortalProperties(String fileName, String content)
		throws IOException {

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(content));

		int lineCount = 0;

		String line = null;

		int previousPos = -1;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			lineCount++;

			int pos = line.indexOf(StringPool.EQUAL);

			if (pos == -1) {
				continue;
			}

			String property = line.substring(0, pos + 1);

			property = property.trim();

			pos = _portalPortalProperties.indexOf(
				StringPool.FOUR_SPACES + property);

			if (pos == -1) {
				continue;
			}

			if (pos < previousPos) {
				processErrorMessage(
					fileName, "sort " + fileName + " " + lineCount);
			}

			previousPos = pos;
		}
	}

	private String _portalPortalProperties;

}