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

package com.liferay.portal.zip;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.memory.DeleteFileFinalizeAction;
import com.liferay.portal.kernel.memory.FinalizeManager;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.zip.ZipWriter;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.ArchiveException;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.File;
import de.schlichtherle.io.FileInputStream;
import de.schlichtherle.io.FileOutputStream;
import de.schlichtherle.io.archive.zip.ZipDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Raymond Augé
 */
public class ZipWriterImpl implements ZipWriter {

	static {
		File.setDefaultArchiveDetector(
			new DefaultArchiveDetector(
				ArchiveDetector.ALL, "lar|" + ArchiveDetector.ALL.getSuffixes(),
				new ZipDriver()));
	}

	public ZipWriterImpl() {
		_file = new File(
			SystemProperties.get(SystemProperties.TMP_DIR) + StringPool.SLASH +
				PortalUUIDUtil.generate() + ".zip");

		_file.mkdir();

		FinalizeManager.register(
			_file, new DeleteFileFinalizeAction(_file.getAbsolutePath()));
	}

	public ZipWriterImpl(java.io.File file) {
		_file = new File(file);

		_file.mkdir();
	}

	@Override
	public void addEntry(String name, byte[] bytes) throws IOException {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(bytes);

		try {
			addEntry(name, unsyncByteArrayInputStream);
		}
		finally {
			unsyncByteArrayInputStream.close();
		}
	}

	@Override
	public void addEntry(String name, InputStream inputStream)
		throws IOException {

		if (name.startsWith(StringPool.SLASH)) {
			name = name.substring(1);
		}

		if (inputStream == null) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Adding " + name);
		}

		FileUtil.mkdirs(getPath());

		OutputStream outputStream = new FileOutputStream(
			new File(getPath() + StringPool.SLASH + name));

		try {
			File.cat(inputStream, outputStream);
		}
		finally {
			outputStream.close();
		}
	}

	@Override
	public void addEntry(String name, String s) throws IOException {
		addEntry(name, s.getBytes(StringPool.UTF8));
	}

	@Override
	public void addEntry(String name, StringBuilder sb) throws IOException {
		addEntry(name, sb.toString());
	}

	@Override
	public byte[] finish() throws IOException {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		InputStream inputStream = new FileInputStream(_file);

		try {
			File.cat(inputStream, unsyncByteArrayOutputStream);
		}
		finally {
			unsyncByteArrayOutputStream.close();
			inputStream.close();
		}

		return unsyncByteArrayOutputStream.toByteArray();
	}

	@Override
	public java.io.File getFile() {
		try {
			File.umount(_file);
		}
		catch (ArchiveException ae) {
			_log.error(ae, ae);
		}

		return _file.getDelegate();
	}

	@Override
	public String getPath() {
		return _file.getPath();
	}

	private static Log _log = LogFactoryUtil.getLog(ZipWriterImpl.class);

	private File _file;

}