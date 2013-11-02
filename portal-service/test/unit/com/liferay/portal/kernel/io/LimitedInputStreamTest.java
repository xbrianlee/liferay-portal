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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class LimitedInputStreamTest {

	@Test
	public void testAvailable() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 3);

		Assert.assertEquals(3, limitedInputStream.available());

		limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		Assert.assertEquals(5, limitedInputStream.available());
	}

	@Test
	public void testClose() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new BufferedInputStream(
				new ByteArrayInputStream(new byte[10])), 5, 3);

		limitedInputStream.close();

		try {
			limitedInputStream.skip(1);

			Assert.fail();
		}
		catch (IOException ioe) {
		}
	}

	@Test
	public void testConstructor() throws IOException {

		// Negative offset

		try {
			new LimitedInputStream(
				new ByteArrayInputStream(new byte[10]), -1, 10);

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
		}

		// Negative length

		try {
			new LimitedInputStream(
				new ByteArrayInputStream(new byte[10]), 5, -1);

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
		}

		// Not enough data to skip offset

		try {
			new LimitedInputStream(
				new ByteArrayInputStream(new byte[10]), 50, 10);

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		// Normal

		new LimitedInputStream(new ByteArrayInputStream(new byte[10]), 5, 5);
	}

	@Test
	public void testMarkSupported() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		Assert.assertFalse(limitedInputStream.markSupported());
	}

	@Test
	public void testRead() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		for (int i = 0; i < 5; i++) {
			Assert.assertEquals(0, limitedInputStream.read());
		}

		Assert.assertEquals(-1, limitedInputStream.read());

		limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 3);

		for (int i = 0; i < 3; i++) {
			Assert.assertEquals(0, limitedInputStream.read());
		}

		Assert.assertEquals(-1, limitedInputStream.read());
	}

	@Test
	public void testReadBlock() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		byte[] buffer = new byte[4];

		Assert.assertEquals(4, limitedInputStream.read(buffer));
		Assert.assertEquals(1, limitedInputStream.read(buffer));
		Assert.assertEquals(-1, limitedInputStream.read(buffer));

		limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 3);

		Assert.assertEquals(3, limitedInputStream.read(buffer));
		Assert.assertEquals(-1, limitedInputStream.read(buffer));
	}

	@Test
	public void testReadBlockWithRange() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		byte[] buffer = new byte[6];

		Assert.assertEquals(4, limitedInputStream.read(buffer, 1, 4));
		Assert.assertEquals(1, limitedInputStream.read(buffer, 1, 4));
		Assert.assertEquals(-1, limitedInputStream.read(buffer, 1, 4));

		limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 3);

		Assert.assertEquals(3, limitedInputStream.read(buffer, 1, 4));
		Assert.assertEquals(-1, limitedInputStream.read(buffer, 1, 4));
	}

	@Test
	public void testSkip() throws IOException {
		LimitedInputStream limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 3);

		Assert.assertEquals(3, limitedInputStream.skip(5));
		Assert.assertEquals(0, limitedInputStream.skip(5));

		limitedInputStream = new LimitedInputStream(
			new ByteArrayInputStream(new byte[10]), 5, 5);

		Assert.assertEquals(5, limitedInputStream.skip(5));
		Assert.assertEquals(0, limitedInputStream.skip(5));
	}

}