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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public interface UploadRequest extends HttpServletRequest {

	public void cleanUp();

	public String getContentType(String name);

	public File getFile(String name);

	public File getFile(String name, boolean forceCreate);

	public InputStream getFileAsStream(String name) throws IOException;

	public InputStream getFileAsStream(String name, boolean deleteOnClose)
		throws IOException;

	public String getFileName(String name);

	public String[] getFileNames(String name);

	public File[] getFiles(String name);

	public InputStream[] getFilesAsStream(String name) throws IOException;

	public InputStream[] getFilesAsStream(String name, boolean deleteOnClose)
		throws IOException;

	public String getFullFileName(String name);

	public Map<String, FileItem[]> getMultipartParameterMap();

	public Map<String, List<String>> getRegularParameterMap();

	public Long getSize(String name);

	public Boolean isFormField(String name);

}