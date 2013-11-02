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

package com.liferay.portal.kernel.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public interface FileItem {

	public void delete();

	public String getContentType();

	public String getEncodedString();

	public String getFieldName();

	public String getFileName();

	public String getFileNameExtension();

	public String getFullFileName();

	public InputStream getInputStream() throws IOException;

	public long getSize();

	public int getSizeThreshold();

	public File getStoreLocation();

	public String getString();

	public boolean isFormField();

	public boolean isInMemory();

	public void setString(String encode);

}