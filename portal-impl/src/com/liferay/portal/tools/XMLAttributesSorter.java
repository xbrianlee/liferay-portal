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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.tools.sourceformatter.XMLSourceProcessor;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.xml.SAXReaderImpl;

import java.io.File;

import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Hugo Huijser
 */
public class XMLAttributesSorter {

	public static void main(String[] args) {
		try {
			new XMLAttributesSorter(args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public XMLAttributesSorter(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		String fileName = arguments.get("sort.xml.file.name");

		if (Validator.isNull(fileName) || fileName.startsWith("$")) {
			System.out.println("Specify file name");

			return;
		}

		/*if (!fileName.endsWith(".xml")) {
			System.out.println("Specify file with .xml extension");

			return;
		}*/

		String basedir = "./";

		DirectoryScanner directoryScanner = new DirectoryScanner();

		directoryScanner.setBasedir(basedir);
		directoryScanner.setIncludes(new String[] {"**\\" + fileName});

		directoryScanner.scan();

		String[] fileNames = directoryScanner.getIncludedFiles();

		if (fileNames.length == 0) {
			System.out.println("No files found with name=" + fileName);

			return;
		}

		for (String fullFileName : fileNames) {
			File file = new File(basedir + fullFileName);

			fullFileName = StringUtil.replace(
				fullFileName, StringPool.BACK_SLASH, StringPool.SLASH);

			String content = _fileUtil.read(file);

			String newContent = sortAttributes(content);

			newContent = XMLSourceProcessor.formatXML(newContent);

			if (!content.equals(newContent)) {
				_fileUtil.write(file, newContent);

				System.out.println(fullFileName);
			}
		}
	}

	protected String sortAttributes(String content) throws Exception {
		Document document = _saxReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		rootElement.sortAttributes(true);

		return document.formattedString();
	}

	private static FileImpl _fileUtil = FileImpl.getInstance();
	private static SAXReaderImpl _saxReaderUtil = SAXReaderImpl.getInstance();

}