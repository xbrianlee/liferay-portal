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

package com.liferay.portal.kernel.io.unsync;

import java.io.IOException;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class UnsyncStringReaderTest {

	@Test
	public void testBlockRead() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdefg");

		char[] chars = new char[4];

		Assert.assertEquals(4, unsyncStringReader.read(chars));
		Assert.assertEquals(4, unsyncStringReader.index);
		Assert.assertTrue(Arrays.equals("abcd".toCharArray(), chars));

		Assert.assertEquals(3, unsyncStringReader.read(chars));
		Assert.assertEquals('e', chars[0]);
		Assert.assertEquals('f', chars[1]);
		Assert.assertEquals('g', chars[2]);

		Assert.assertEquals(-1, unsyncStringReader.read(chars));
	}

	@Test
	public void testClose() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdefg");

		unsyncStringReader.close();

		Assert.assertTrue(unsyncStringReader.string == null);

		try {
			unsyncStringReader.mark(0);

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.read();

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.read(new char[5]);

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.ready();

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.reset();

			Assert.fail();
		}
		catch (IOException ioe) {
		}

		unsyncStringReader.close();
	}

	@Test
	public void testConstructor() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		Assert.assertEquals("abc", unsyncStringReader.string);
		Assert.assertEquals(3, unsyncStringReader.stringLength);

		unsyncStringReader = new UnsyncStringReader("defg");

		Assert.assertEquals("defg", unsyncStringReader.string);
		Assert.assertEquals(4, unsyncStringReader.stringLength);
	}

	@Test
	public void testMarkAndReset() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		Assert.assertEquals('a', unsyncStringReader.read());

		unsyncStringReader.mark(-1);

		Assert.assertEquals('b', unsyncStringReader.read());
		Assert.assertEquals('c', unsyncStringReader.read());
		Assert.assertEquals(-1, unsyncStringReader.read());

		unsyncStringReader.reset();

		Assert.assertEquals('b', unsyncStringReader.read());
		Assert.assertEquals('c', unsyncStringReader.read());
		Assert.assertEquals(-1, unsyncStringReader.read());
	}

	@Test
	public void testMarkSupported() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		Assert.assertTrue(unsyncStringReader.markSupported());
	}

	@Test
	public void testRead() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		Assert.assertEquals('a', unsyncStringReader.read());
		Assert.assertEquals('b', unsyncStringReader.read());
		Assert.assertEquals('c', unsyncStringReader.read());
		Assert.assertEquals(-1, unsyncStringReader.read());
	}

	@Test
	public void testSkip() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdef");

		Assert.assertEquals('a', unsyncStringReader.read());
		Assert.assertEquals(2, unsyncStringReader.skip(2));
		Assert.assertEquals('d', unsyncStringReader.read());
		Assert.assertEquals(2, unsyncStringReader.skip(3));
		Assert.assertEquals(-1, unsyncStringReader.read());
	}

}