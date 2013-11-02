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

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tina Tian
 */
public class Base64InputStreamTest {

	@Test
	public void testAvailable() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		int returnValue = base64InputStream.available();

		Assert.assertEquals(3, returnValue);

		base64InputStream.close();
	}

	@Test
	public void testDecode() {
		try {
			byte[] bytes = {'a', 'b', 'c', 'd'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			byte[] outputBuffer = new byte[3];
			int position = 0;

			Assert.assertEquals(
				3, base64InputStream.decode(bytes, outputBuffer, position, 0));
			Assert.assertEquals(
				2, base64InputStream.decode(bytes, outputBuffer, position, 1));
			Assert.assertEquals(
				1, base64InputStream.decode(bytes, outputBuffer, position, 2));
			Assert.assertEquals(
				-1, base64InputStream.decode(bytes, outputBuffer, position, 3));

			base64InputStream.close();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDecodeUnit() throws Exception {
		byte[] bytes = {
			'a', 'b', 'c', 'd', 'e', 'f', 'h', '=', 'e', 'f', '=', '=', 'e',
			'=', 'e', 'f', '=', 'a'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		byte[] outputBuffer = new byte[3];
		int position = 0;

		Assert.assertEquals(
			3, base64InputStream.decodeUnit(outputBuffer, position));
		Assert.assertEquals(
			2, base64InputStream.decodeUnit(outputBuffer, position));
		Assert.assertEquals(
			1, base64InputStream.decodeUnit(outputBuffer, position));
		Assert.assertEquals(
			-1, base64InputStream.decodeUnit(outputBuffer, position));
		Assert.assertEquals(
			-1, base64InputStream.decodeUnit(outputBuffer, position));
		Assert.assertEquals(
			-1, base64InputStream.decodeUnit(outputBuffer, position));

		base64InputStream.close();
	}

	@Test
	public void testGetByte() {
		try {
			byte[] bytes = {'a'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			Assert.assertEquals(0, base64InputStream.getByte('A'));
			Assert.assertEquals(0, base64InputStream.getByte('='));
			Assert.assertEquals(-1, base64InputStream.getByte('\n'));
			Assert.assertEquals(62, base64InputStream.getByte('+'));

			base64InputStream.close();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetEncodedByte() throws Exception {
		try {
			byte[] bytes = {'A', '=', 'B', '\n'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			Assert.assertEquals(0, base64InputStream.getEncodedByte());
			Assert.assertEquals(-2, base64InputStream.getEncodedByte());
			Assert.assertEquals(1, base64InputStream.getEncodedByte());
			Assert.assertEquals(-1, base64InputStream.getEncodedByte());

			base64InputStream.close();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRead_0args() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		Assert.assertEquals(105, base64InputStream.read());

		base64InputStream.read();
		base64InputStream.read();

		Assert.assertEquals(-1, base64InputStream.read());

		base64InputStream.close();
	}

	@Test
	public void testRead_3args() throws Exception {
		byte[] bytes = {
			'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd', 'e', 'f', 'g', '='};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		byte[] buffer = new byte[5];
		int offset = 0;

		Assert.assertEquals(1, base64InputStream.read(buffer, offset, 1));
		Assert.assertEquals(2, base64InputStream.read(buffer, offset, 2));
		Assert.assertEquals(5, base64InputStream.read(buffer, offset, 6));
		Assert.assertEquals(-1, base64InputStream.read(buffer, offset, 3));
		Assert.assertEquals(-1, base64InputStream.read(buffer, offset, 1));
		Assert.assertEquals(-1, base64InputStream.read(buffer, offset, 0));

		base64InputStream.close();
	}

	@Test
	public void testSkip() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		Assert.assertEquals(3L, base64InputStream.skip(4L));

		base64InputStream.close();
	}

}