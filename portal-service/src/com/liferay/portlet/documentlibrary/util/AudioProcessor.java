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
public interface AudioProcessor {

	public static final String PREVIEW_TYPE = "mp3";

	public void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception;

	public void generateAudio(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception;

	public Set<String> getAudioMimeTypes();

	public InputStream getPreviewAsStream(FileVersion fileVersion, String type)
		throws Exception;

	public long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception;

	public boolean hasAudio(FileVersion fileVersion);

	public void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception;

	public boolean isAudioSupported(FileVersion fileVersion);

	public boolean isAudioSupported(String mimeType);

	public boolean isSupported(String mimeType);

	public void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion);

}