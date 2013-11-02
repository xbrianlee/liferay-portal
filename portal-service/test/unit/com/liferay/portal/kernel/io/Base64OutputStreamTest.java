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

import com.liferay.portal.kernel.util.CharPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tina Tian
 */
public class Base64OutputStreamTest {

	@Test
	public void testClose() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.write('A');

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		byte[] bytes = new byte[4];

		byteArrayInputStream.read(bytes);

		byteArrayInputStream.close();

		if ((bytes[3] != CharPool.EQUAL) || (bytes[2] != CharPool.EQUAL)) {
			Assert.fail();
		}
	}

	@Test
	public void testEncodeUnit1Byte() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.encodeUnit((byte)'A');

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

	@Test
	public void testEncodeUnit2Bytes() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.encodeUnit((byte)'A', (byte)'B');

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

	@Test
	public void testEncodeUnit3Args() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.encodeUnit((byte)'A', (byte)'B', (byte)'C');

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

	@Test
	public void testFlush() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.write('A');

		base64OutputStream.flush();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();

		byteArrayOutputStream = new ByteArrayOutputStream();

		base64OutputStream = new Base64OutputStream(byteArrayOutputStream);

		base64OutputStream.write('A');
		base64OutputStream.write('B');

		base64OutputStream.flush();

		byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

	@Test
	public void testGetChar() {
		try {
			Base64OutputStream base64OutputStream = new Base64OutputStream(
				new ByteArrayOutputStream());

			Assert.assertEquals('A', base64OutputStream.getChar(0));
			Assert.assertEquals('?', base64OutputStream.getChar(64));

			base64OutputStream.close();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWrite3Args() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		byte[] bytes = {'A', 'B', 'C', 'A', 'B', 'C'};

		base64OutputStream.write(bytes, 0, 1);
		base64OutputStream.write(bytes, 1, 2);
		base64OutputStream.write(bytes, 3, 3);

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(8, byteArrayInputStream.read(new byte[8]));

		byteArrayInputStream.close();
	}

	@Test
	public void testWriteByteArray() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.write(new byte[] {'A', 'B', 'C'});

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

	@Test
	public void testWriteInt() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Base64OutputStream base64OutputStream = new Base64OutputStream(
			byteArrayOutputStream);

		base64OutputStream.write('A');
		base64OutputStream.write('A');
		base64OutputStream.write('A');

		base64OutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(4, byteArrayInputStream.read(new byte[4]));

		byteArrayInputStream.close();
	}

}