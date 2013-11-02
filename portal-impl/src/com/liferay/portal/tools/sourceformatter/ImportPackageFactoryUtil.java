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

import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carlos Sierra Andrés
 */
public class ImportPackageFactoryUtil {

	public static ImportPackage create(String line) {
		if (Validator.isNull(line)) {
			return null;
		}

		Matcher javaMatcher = _javaImportPattern.matcher(line);

		if (javaMatcher.find()) {
			return new ImportPackage(javaMatcher.group(1), line);
		}

		Matcher jspMatcher = _jspImportPattern.matcher(line);

		if (jspMatcher.find()) {
			return new ImportPackage(jspMatcher.group(1), line);
		}

		return null;
	}

	private static final Pattern _javaImportPattern = Pattern.compile(
		"import ([^\\s;]+)");
	private static final Pattern _jspImportPattern = Pattern.compile(
		"import=\"([^\\s\"]+)\"");

}