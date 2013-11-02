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

package com.liferay.portal.kernel.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface ZipWriter {

	public void addEntry(String name, byte[] bytes) throws IOException;

	public void addEntry(String name, InputStream inputStream)
		throws IOException;

	public void addEntry(String name, String s) throws IOException;

	public void addEntry(String name, StringBuilder sb)
		throws IOException;

	public byte[] finish() throws IOException;

	public File getFile();

	public String getPath();

}