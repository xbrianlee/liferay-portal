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

package com.liferay.portlet.documentlibrary.webdav;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileEntryResourceImpl extends BaseResourceImpl {

	public DLFileEntryResourceImpl(
		WebDAVRequest webDAVRequest, FileEntry fileEntry, String parentPath,
		String name) {

		super(
			parentPath, name, fileEntry.getTitle(), fileEntry.getCreateDate(),
			fileEntry.getModifiedDate(), fileEntry.getSize());

		setModel(fileEntry);
		setClassName(DLFileEntry.class.getName());
		setPrimaryKey(fileEntry.getPrimaryKey());

		//_webDAVRequest = webDAVRequest;
		_fileEntry = fileEntry;
	}

	@Override
	public InputStream getContentAsStream() throws WebDAVException {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getContentStream(false);
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public String getContentType() {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getMimeType();
		}
		catch (Exception e) {
			return _fileEntry.getMimeType();
		}
	}

	@Override
	public Lock getLock() {
		try {
			return _fileEntry.getLock();
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public long getSize() {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getSize();
		}
		catch (Exception e) {
			return _fileEntry.getSize();
		}
	}

	@Override
	public boolean isCollection() {
		return false;
	}

	@Override
	public boolean isLocked() {
		try {
			return _fileEntry.hasLock();
		}
		catch (Exception e) {
		}

		return false;
	}

	private FileEntry _fileEntry;

}