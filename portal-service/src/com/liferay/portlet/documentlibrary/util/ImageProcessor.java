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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.xml.Element;

import java.io.InputStream;

import java.util.Set;

/**
 * @author Sergio González
 */
public interface ImageProcessor {

	public void cleanUp(FileEntry fileEntry);

	public void cleanUp(FileVersion fileVersion);

	public void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception;

	public void generateImages(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception;

	public Set<String> getImageMimeTypes();

	public InputStream getPreviewAsStream(FileVersion fileVersion)
		throws Exception;

	public long getPreviewFileSize(FileVersion fileVersion)
		throws Exception;

	public String getPreviewType(FileVersion fileVersion);

	public InputStream getThumbnailAsStream(FileVersion fileVersion, int index)
		throws Exception;

	public long getThumbnailFileSize(FileVersion fileVersion, int index)
		throws Exception;

	public String getThumbnailType(FileVersion fileVersion);

	public boolean hasImages(FileVersion fileVersion);

	public void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception;

	public boolean isImageSupported(FileVersion fileVersion);

	public boolean isImageSupported(String mimeType);

	public boolean isSupported(String mimeType);

	public void storeThumbnail(
			long companyId, long groupId, long fileEntryId, long fileVersionId,
			long custom1ImageId, long custom2ImageId, InputStream is,
			String type)
		throws Exception;

	public void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion);

}