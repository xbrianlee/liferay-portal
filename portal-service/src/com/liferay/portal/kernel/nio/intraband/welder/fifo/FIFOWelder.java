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

package com.liferay.portal.kernel.nio.intraband.welder.fifo;

import com.liferay.portal.kernel.io.AutoDeleteFileInputStream;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.BaseWelder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public class FIFOWelder extends BaseWelder {

	public FIFOWelder() throws IOException {
		String tempFolderName = System.getProperty("java.io.tmpdir");

		long id = idCounter.getAndIncrement();

		inputFIFOFile = new File(tempFolderName, "FIFO-INPUT-" + id);
		outputFIFOFile = new File(tempFolderName, "FIFO-OUTPUT-" + id);

		try {
			FIFOUtil.createFIFO(inputFIFOFile);
			FIFOUtil.createFIFO(outputFIFOFile);
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	protected void doDestroy() throws IOException {
		readFileChannel.close();
		writeFileChannel.close();
	}

	@Override
	protected RegistrationReference weldClient(Intraband intraband)
		throws IOException {

		// Write, then read

		FileOutputStream fileOutputStream = new FileOutputStream(inputFIFOFile);

		writeFileChannel = fileOutputStream.getChannel();

		FileInputStream fileInputStream = new AutoDeleteFileInputStream(
			outputFIFOFile);

		readFileChannel = fileInputStream.getChannel();

		return intraband.registerChannel(readFileChannel, writeFileChannel);
	}

	@Override
	protected RegistrationReference weldServer(Intraband intraband)
		throws IOException {

		// Read, then write

		FileInputStream fileInputStream = new AutoDeleteFileInputStream(
			inputFIFOFile);

		readFileChannel = fileInputStream.getChannel();

		FileOutputStream fileOutputStream = new FileOutputStream(
			outputFIFOFile);

		writeFileChannel = fileOutputStream.getChannel();

		return intraband.registerChannel(readFileChannel, writeFileChannel);
	}

	protected static final AtomicLong idCounter = new AtomicLong(
		System.currentTimeMillis());

	protected final File inputFIFOFile;
	protected final File outputFIFOFile;
	protected transient FileChannel readFileChannel;
	protected transient FileChannel writeFileChannel;

}