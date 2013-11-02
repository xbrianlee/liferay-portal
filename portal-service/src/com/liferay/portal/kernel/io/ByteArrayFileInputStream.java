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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuyang Zhou
 */
public class ByteArrayFileInputStream extends InputStream {

	public ByteArrayFileInputStream(File file, int threshold) {
		this(file, threshold, false);
	}

	public ByteArrayFileInputStream(
		File file, int threshold, boolean deleteOnClose) {

		if (!file.exists() || !file.isFile()) {
			throw new IllegalArgumentException(
				"File " + file.getAbsolutePath() + " does not exist");
		}

		this.file = file;
		fileSize = file.length();
		this.threshold = threshold;
		this.deleteOnClose = deleteOnClose;
	}

	@Override
	public int available() throws IOException {
		if (data != null) {
			return data.length - index;
		}
		else if (fileInputStream != null) {
			return fileInputStream.available();
		}
		else {
			return 0;
		}
	}

	@Override
	public void close() throws IOException {
		try {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
		finally {
			data = null;
			fileInputStream = null;

			if (deleteOnClose) {
				file.delete();
			}

			file = null;
		}
	}

	public File getFile() {
		return file;
	}

	@Override
	public void mark(int readLimit) {
		markIndex = index;
	}

	@Override
	public boolean markSupported() {
		return fileSize < threshold;
	}

	@Override
	public int read() throws IOException {
		if (fileSize < threshold) {
			initData();

			if (index < data.length) {
				return data[index++] & 0xff;
			}
			else {
				return -1;
			}
		}
		else {
			initFileInputStream();

			return fileInputStream.read();
		}
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		return read(bytes, 0, bytes.length);
	}

	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException {
		if (length <= 0) {
			return 0;
		}

		if (fileSize < threshold) {
			initData();

			if (index >= data.length) {
				return -1;
			}

			int read = length;

			if ((index + read) > data.length) {
				read = data.length - index;
			}

			System.arraycopy(data, index, bytes, offset, read);

			index += read;

			return read;
		}

		initFileInputStream();

		return fileInputStream.read(bytes, offset, length);
	}

	@Override
	public void reset() throws IOException {
		if (data != null) {
			index = markIndex;
		}
		else if (fileInputStream != null) {
			fileInputStream.close();

			fileInputStream = null;
		}
	}

	@Override
	public long skip(long skip) throws IOException {
		if (skip < 0) {
			return 0;
		}

		if (fileSize < threshold) {
			initData();

			if ((skip + index) > data.length) {
				skip = data.length - index;
			}

			index += skip;

			return skip;
		}

		initFileInputStream();

		return fileInputStream.skip(skip);
	}

	protected void initData() throws IOException {
		if (data != null) {
			return;
		}

		int arraySize = (int)this.fileSize;

		data = new byte[arraySize];

		FileInputStream fileInputStream = new FileInputStream(file);

		int offset = 0;
		int length = 0;

		try {
			while (offset < arraySize) {
				length = fileInputStream.read(data, offset, arraySize - offset);

				offset += length;
			}
		}
		finally {
			fileInputStream.close();
		}
	}

	protected void initFileInputStream() throws IOException {
		if (fileInputStream == null) {
			fileInputStream = new FileInputStream(file);
		}
	}

	protected byte[] data;
	protected boolean deleteOnClose;
	protected File file;
	protected FileInputStream fileInputStream;
	protected long fileSize;
	protected int index;
	protected int markIndex;
	protected int threshold;

}