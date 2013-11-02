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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Shuyang Zhou
 */
public class AutoDeleteFileInputStream extends FileInputStream {

	public AutoDeleteFileInputStream(File file) throws FileNotFoundException {
		super(file);

		_file = file;
	}

	@Override
	public void close() throws IOException {
		super.close();

		if (!_file.delete()) {
			_file.deleteOnExit();
		}
	}

	private final File _file;

}