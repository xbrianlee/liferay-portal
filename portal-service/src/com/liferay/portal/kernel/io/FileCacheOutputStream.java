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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Raymond Augé
 */
public class FileCacheOutputStream extends OutputStream {

	public FileCacheOutputStream() throws IOException {
		_tempFile = File.createTempFile(
			PortalUUIDUtil.generate() + StringPool.DASH, _EXTENSION);

		_ubos = new UnsyncBufferedOutputStream(
			new FileOutputStream(_tempFile), _BUFFER);
	}

	public void cleanUp() {
		try {
			flush();
			close();

			if (_fis != null) {
				_fis.close();
			}

			FileUtil.delete(_tempFile);
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioe.getMessage());
			}
		}
	}

	@Override
	public void close() throws IOException {
		_ubos.close();
	}

	@Override
	public void flush() throws IOException {
		_ubos.flush();
	}

	public byte[] getBytes() throws IOException {
		flush();
		close();

		return FileUtil.getBytes(_tempFile);
	}

	public File getFile() throws IOException {
		flush();
		close();

		return _tempFile;
	}

	public FileInputStream getFileInputStream() throws IOException {
		if (_fis == null) {
			flush();
			close();

			_fis = new FileInputStream(_tempFile);
		}

		return _fis;
	}

	public long getSize() {
		return _tempFile.length();
	}

	@Override
	public void write(byte[] b) throws IOException {
		_ubos.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		_ubos.write(b, off, len);
	}

	@Override
	public void write(int b) throws IOException {
		_ubos.write(b);
	}

	private static final int _BUFFER = 2048;

	private static final String _EXTENSION = ".fcos";

	private static Log _log = LogFactoryUtil.getLog(
		FileCacheOutputStream.class);

	private FileInputStream _fis;
	private File _tempFile;
	private UnsyncBufferedOutputStream _ubos;

}