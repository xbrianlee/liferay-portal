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

import com.liferay.portal.kernel.util.StringPool;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class WriterOutputStreamTest {

	@Test
	public void testACSIIOutput() throws IOException {

		// Auto flush

		Writer writer = new StringWriter();

		OutputStream outputStream = new WriterOutputStream(
			writer, StringPool.UTF8, true);

		String[] asciiOutput = {"This ", "is ", "an ", "ACSII ", " test"};
		byte[][] asciiInput = {
			asciiOutput[0].getBytes(StringPool.UTF8),
			asciiOutput[1].getBytes(StringPool.UTF8),
			asciiOutput[2].getBytes(StringPool.UTF8),
			asciiOutput[3].getBytes(StringPool.UTF8),
			asciiOutput[4].getBytes(StringPool.UTF8)};

		String expectedResult = asciiOutput[0];

		outputStream.write(asciiInput[0]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += asciiOutput[1];

		outputStream.write(asciiInput[1]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += asciiOutput[2];

		outputStream.write(asciiInput[2]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += asciiOutput[3];

		outputStream.write(asciiInput[3]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += asciiOutput[4];

		outputStream.write(asciiInput[4]);

		Assert.assertEquals(expectedResult, writer.toString());

		// Do not auto flush

		writer = new StringWriter();

		outputStream = new WriterOutputStream(writer, StringPool.UTF8, false);

		outputStream.write(asciiInput[0]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(asciiInput[1]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(asciiInput[2]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(asciiInput[3]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(asciiInput[4]);

		Assert.assertEquals("", writer.toString());

		outputStream.flush();

		Assert.assertEquals(expectedResult, writer.toString());
	}

	@Test
	public void testChineseOutput() throws IOException {

		// Auto flush

		Writer writer = new StringWriter();

		OutputStream outputStream = new WriterOutputStream(
			writer, StringPool.UTF8, true);

		String[] chineseOutput = {"这是", "一个", "中文", "解码 ", "测试"};
		byte[][] chineseInput = {
			chineseOutput[0].getBytes(StringPool.UTF8),
			chineseOutput[1].getBytes(StringPool.UTF8),
			chineseOutput[2].getBytes(StringPool.UTF8),
			chineseOutput[3].getBytes(StringPool.UTF8),
			chineseOutput[4].getBytes(StringPool.UTF8)};

		String expectedResult = chineseOutput[0];

		outputStream.write(chineseInput[0]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += chineseOutput[1];

		outputStream.write(chineseInput[1]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += chineseOutput[2];

		outputStream.write(chineseInput[2]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += chineseOutput[3];

		outputStream.write(chineseInput[3]);

		Assert.assertEquals(expectedResult, writer.toString());

		expectedResult += chineseOutput[4];

		outputStream.write(chineseInput[4]);

		Assert.assertEquals(expectedResult, writer.toString());

		// Do not auto flush

		writer = new StringWriter();

		outputStream = new WriterOutputStream(writer, StringPool.UTF8, false);

		outputStream.write(chineseInput[0]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(chineseInput[1]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(chineseInput[2]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(chineseInput[3]);

		Assert.assertEquals("", writer.toString());

		outputStream.write(chineseInput[4]);

		Assert.assertEquals("", writer.toString());

		outputStream.flush();

		Assert.assertEquals(expectedResult, writer.toString());
	}

	@Test
	public void testNonAlignOutput() throws IOException {
		CharArrayWriter charArrayWriter = new CharArrayWriter();

		WriterOutputStream writerOutputStream = new WriterOutputStream(
			charArrayWriter, StringPool.UTF8, true);

		int charNumber = 0;

		String nonAlignOutput = "非对齐测试中文输出";
		byte[] nonAlignInput = nonAlignOutput.getBytes(StringPool.UTF8);

		for (byte b : nonAlignInput) {
			writerOutputStream.write(b);

			int currentCharNumber = charArrayWriter.size();

			if (currentCharNumber > charNumber) {
				charNumber = currentCharNumber;

				Assert.assertEquals(
					nonAlignOutput.charAt(charNumber - 1),
					charArrayWriter.toCharArray()[charNumber - 1]);
			}
		}
	}

}