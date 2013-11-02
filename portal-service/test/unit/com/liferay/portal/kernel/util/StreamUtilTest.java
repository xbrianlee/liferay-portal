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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import java.nio.channels.FileChannel;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class StreamUtilTest {

	@Test
	public void testTransferFileChannel() throws Exception {
		File fromFile = new File("from-file");

		fromFile.deleteOnExit();

		FileOutputStream fromFileOutputStream = new FileOutputStream(fromFile);

		Random random = new Random();

		byte[] fromBytes = new byte[1024 * 1024];

		random.nextBytes(fromBytes);

		fromFileOutputStream.write(fromBytes);

		fromFileOutputStream.close();

		File toFile = new File("to-file");

		toFile.deleteOnExit();

		FileInputStream fromFileInputStream = new FileInputStream(fromFile);

		byte[] buffer = new byte[fromBytes.length / 2];

		int length = 0;

		while (
			(length += fromFileInputStream.read(
				buffer, length, buffer.length - length)) < buffer.length);

		FileOutputStream toFileOutputStream = new FileOutputStream(toFile);

		toFileOutputStream.write(buffer);

		FileChannel fromFileChannel = fromFileInputStream.getChannel();

		StreamUtil.transferFileChannel(
			fromFileChannel, toFileOutputStream.getChannel(),
			fromBytes.length - buffer.length);

		fromFileChannel.close();

		toFileOutputStream.close();

		RandomAccessFile toRandomAccessFile = new RandomAccessFile(toFile, "r");

		Assert.assertEquals(fromBytes.length, toRandomAccessFile.length());

		byte[] toBytes = new byte[fromBytes.length];

		toRandomAccessFile.readFully(toBytes);

		toRandomAccessFile.close();

		Assert.assertArrayEquals(fromBytes, toBytes);
	}

}