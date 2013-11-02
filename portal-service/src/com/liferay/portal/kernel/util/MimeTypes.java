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

package com.liferay.portal.kernel.util;

import java.io.File;
import java.io.InputStream;

import java.util.Set;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public interface MimeTypes {

	public String getContentType(File file);

	public String getContentType(File file, String fileName);

	public String getContentType(InputStream inputStream, String fileName);

	public String getContentType(String fileName);

	public String getExtensionContentType(String extension);

	public Set<String> getExtensions(String contentType);

	public boolean isWebImage(String mimeType);

}