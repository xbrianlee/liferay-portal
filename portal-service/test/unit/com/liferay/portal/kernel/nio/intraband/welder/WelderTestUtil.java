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

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.junit.Assert;

/**
 * @author Shuyang Zhou
 */
public class WelderTestUtil {

	public static void assertConnectted(
			final ScatteringByteChannel scatteringByteChannel,
			final GatheringByteChannel gatheringByteChannel)
		throws Exception {

		Random random = new Random();

		final byte[] data = new byte[1024 * 1024];

		random.nextBytes(data);

		FutureTask<Void> writeFutureTask = new FutureTask<Void>(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					ByteBuffer byteBuffer = ByteBuffer.wrap(data);

					while (byteBuffer.hasRemaining()) {
						gatheringByteChannel.write(byteBuffer);
					}

					return null;
				}
			});

		Thread writeThread = new Thread(writeFutureTask);

		writeThread.start();

		FutureTask<byte[]> readFutureTask = new FutureTask<byte[]>(
			new Callable<byte[]>() {

				@Override
				public byte[] call() throws Exception {
					ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);

					while (byteBuffer.hasRemaining()) {
						scatteringByteChannel.read(byteBuffer);
					}

					return byteBuffer.array();
				}
			});

		Thread readThread = new Thread(readFutureTask);

		readThread.start();

		writeFutureTask.get();

		Assert.assertArrayEquals(data, readFutureTask.get());
	}

	public static <T extends Welder> T transform(T welder) throws Exception {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			unsyncByteArrayOutputStream);

		objectOutputStream.writeObject(welder);

		objectOutputStream.close();

		ByteBuffer byteBuffer =
			unsyncByteArrayOutputStream.unsafeGetByteBuffer();

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(
				byteBuffer.array(), 0, byteBuffer.remaining());

		ObjectInputStream objectInputStream = new ObjectInputStream(
			unsyncByteArrayInputStream);

		return (T)objectInputStream.readObject();
	}

}