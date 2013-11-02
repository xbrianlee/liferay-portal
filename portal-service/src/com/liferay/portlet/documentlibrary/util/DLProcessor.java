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

/**
 * Common interface for all the processors of the document library. All document
 * library processors must implement this interface.
 *
 * @author Alexander Chow
 * @author Mika Koivisto
 * @see    AudioProcessor
 * @see    DLPreviewableProcessor
 * @see    ImageProcessor
 * @see    PDFProcessor
 * @see    RawMetadataProcessor
 * @see    VideoProcessor
 */
public interface DLProcessor {

	public void afterPropertiesSet() throws Exception;

	public void cleanUp(FileEntry fileEntry);

	public void cleanUp(FileVersion fileVersion);

	public void copy(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion);

	public void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception;

	public void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception;

	public boolean isSupported(FileVersion fileVersion);

	public boolean isSupported(String mimeType);

	/**
	 * Launches the processor's work with respect to the given file version.
	 *
	 * @param sourceFileVersion the file version to copy previews and thumbnails
	 *        from (optionally <code>null</code>)
	 * @param destinationFileVersion the latest file version to process
	 */
	public void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion);

}