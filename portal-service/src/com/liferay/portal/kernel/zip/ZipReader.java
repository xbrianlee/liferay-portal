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

import java.io.InputStream;

import java.util.List;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface ZipReader {

	public void close();

	public List<String> getEntries();

	public byte[] getEntryAsByteArray(String name);

	public InputStream getEntryAsInputStream(String name);

	public String getEntryAsString(String name);

	public List<String> getFolderEntries(String path);

}