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

package com.liferay.portal.tools.jspc.common;

import java.io.File;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Minhchau Dang
 */
public class TimestampUpdater {

	public static void main(String[] args) {
		if (args.length == 1) {
			new TimestampUpdater(args[0]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public TimestampUpdater(String classDirName) {
		DirectoryScanner directoryScanner = new DirectoryScanner();

		directoryScanner.setBasedir(classDirName);
		directoryScanner.setIncludes(new String[] {"**\\*.java"});

		directoryScanner.scan();

		String[] fileNames = directoryScanner.getIncludedFiles();

		for (String fileName : fileNames) {
			File javaFile = new File(classDirName, fileName);

			String fileNameWithoutExtension = fileName.substring(
				0, fileName.length() - 5);

			String classFileName = fileNameWithoutExtension.concat(".class");

			File classFile = new File(classDirName, classFileName);

			classFile.setLastModified(javaFile.lastModified());
		}
	}

}