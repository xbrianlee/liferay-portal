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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.InputStream;

import java.util.Set;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class MimeTypesUtil {

	/**
	 * Returns the content type from the file.
	 *
	 * @param  file the file of the content
	 * @return the content type if it is a supported format or
	 *         "application/octet-stream" if it is an unsupported format
	 */
	public static String getContentType(File file) {
		return getMimeTypes().getContentType(file);
	}

	/**
	 * Returns the content type from the file and file name.
	 *
	 * @param  file the file of the content (optionally <code>null</code>)
	 * @param  fileName the full name or extension of the file (e.g.,
	 *         "Test.doc", ".doc")
	 * @return the content type if it is a supported format or
	 *         "application/octet-stream" if it is an unsupported format
	 */
	public static String getContentType(File file, String fileName) {
		return getMimeTypes().getContentType(file, fileName);
	}

	/**
	 * Returns the content type from the input stream and file name.
	 *
	 * <p>
	 * The input stream is not reset upon return of this method. This needs to
	 * be handled by the caller if the input stream is to be reused.
	 * Alternatively, use the method {@link #getContentType(File, String)}.
	 * </p>
	 *
	 * @param  inputStream the input stream of the content (optionally
	 *         <code>null</code>)
	 * @param  fileName the full name or extension of the file (e.g.,
	 *         "Test.doc", ".doc")
	 * @return the content type if it is a supported format or
	 *         "application/octet-stream" if it is an unsupported format
	 */
	public static String getContentType(
		InputStream inputStream, String fileName) {

		return getMimeTypes().getContentType(inputStream, fileName);
	}

	/**
	 * Returns the content type from the file name.
	 *
	 * @param  fileName the full name or extension of the file (e.g.,
	 *         "Test.doc", ".doc")
	 * @return the content type if it is a supported format or
	 *         "application/octet-stream" if it is an unsupported format
	 */
	public static String getContentType(String fileName) {
		return getMimeTypes().getContentType(fileName);
	}

	/**
	 * Returns the content type from the file extension.
	 *
	 * @param  extension the extension of the file (e.g., "doc")
	 * @return the content type if it is a supported format or
	 *         "application/octet-stream" if it is an unsupported format
	 */
	public static String getExtensionContentType(String extension) {
		return getMimeTypes().getExtensionContentType(extension);
	}

	/**
	 * Returns the possible file extensions for the content type.
	 *
	 * @param  contentType the content type of the file (e.g., "image/jpeg")
	 * @return the set of extensions if it is a known content type or an empty
	 *         set if it is an unknown content type
	 */
	public static Set<String> getExtensions(String contentType) {
		return getMimeTypes().getExtensions(contentType);
	}

	public static MimeTypes getMimeTypes() {
		PortalRuntimePermission.checkGetBeanProperty(MimeTypesUtil.class);

		return _mimeTypes;
	}

	public static boolean isWebImage(String mimeType) {
		return getMimeTypes().isWebImage(mimeType);
	}

	public void setMimeTypes(MimeTypes mimeTypes) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_mimeTypes = mimeTypes;
	}

	private static MimeTypes _mimeTypes;

}