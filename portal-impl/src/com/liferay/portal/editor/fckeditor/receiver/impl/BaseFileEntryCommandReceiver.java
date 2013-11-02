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

package com.liferay.portal.editor.fckeditor.receiver.impl;

import com.liferay.portal.editor.fckeditor.command.CommandArgument;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.xuggler.XugglerUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Juan Gonzalez
 * @author Roberto Díaz
 * @author Brian Wing Shun Chan
 */
public abstract class BaseFileEntryCommandReceiver
	extends DocumentCommandReceiver {

	@Override
	protected String fileUpload(
		CommandArgument commandArgument, String fileName,
		InputStream inputStream, String contentType, long size) {

		if (!XugglerUtil.isEnabled()) {
			return String.valueOf(getXugglerDisabledFileUploadReturnValue());
		}

		return super.fileUpload(
			commandArgument, fileName, inputStream, contentType, size);
	}

	@Override
	protected Element getFileElement(
			CommandArgument commandArgument, Element fileElement,
			FileEntry fileEntry)
		throws Exception {

		fileElement = super.getFileElement(
			commandArgument, fileElement, fileEntry);

		if (!hasFileEntryPreview(fileEntry.getFileVersion())) {
			fileElement.setAttribute(
				"errorMessage",
				LanguageUtil.get(
					commandArgument.getLocale(),
					getUnavaiablePreviewErrorMessage()));
		}

		return fileElement;
	}

	@Override
	protected List<Element> getFileElements(
			CommandArgument commandArgument, Document document, Folder folder)
		throws Exception {

		List<Element> fileElements = new ArrayList<Element>();

		List<FileEntry> fileEntries = null;

		String[] fileEntryMimeTypes = getFileEntryMimeTypes();

		if (fileEntryMimeTypes != null) {
			fileEntries = DLAppServiceUtil.getFileEntries(
				folder.getRepositoryId(), folder.getFolderId(),
				fileEntryMimeTypes);
		}
		else {
			fileEntries = DLAppServiceUtil.getFileEntries(
				folder.getRepositoryId(), folder.getFolderId());
		}

		for (FileEntry fileEntry : fileEntries) {
			Element fileElement = document.createElement("File");

			fileElement = getFileElement(
				commandArgument, fileElement, fileEntry);

			fileElements.add(fileElement);
		}

		return fileElements;
	}

	protected abstract String[] getFileEntryMimeTypes();

	protected abstract String getUnavaiablePreviewErrorMessage();

	protected abstract int getXugglerDisabledFileUploadReturnValue();

	protected abstract boolean hasFileEntryPreview(FileVersion fileVersion);

}