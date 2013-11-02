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

package com.liferay.portal.kernel.io;

import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class FileFilter implements java.io.FileFilter {

	public FileFilter() {
	}

	public FileFilter(String regex) {
		_pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File file) {
		if (file.isFile()) {
			if (_pattern == null) {
				return true;
			}

			Matcher matcher = _pattern.matcher(file.getName());

			return matcher.matches();
		}
		else {
			return false;
		}
	}

	private Pattern _pattern;

}