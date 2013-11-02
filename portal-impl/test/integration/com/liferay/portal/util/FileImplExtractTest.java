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

package com.liferay.portal.util;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Spasic
 * @see    MimeTypesImplTest
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class FileImplExtractTest {

	@Test
	public void testDoc() {
		String text = extractText("test.doc");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testDocx() {
		String text = extractText("test-2007.docx");

		Assert.assertEquals("Extract test.", text);

		text = extractText("test-2010.docx");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testHtml() {
		String text = extractText("test.html");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testJpg() {
		String text = extractText("test.jpg");

		Assert.assertEquals("", text);
	}

	@Test
	public void testOdt() {
		String text = extractText("test.odt");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testPdf() {
		String text = extractText("test-2010.pdf");

		Assert.assertEquals("Extract test.", text);

		// PDFBOX-890

		//text = _extractText("test.pdf");

		//assertEquals("Extract test.", text);
	}

	@Test
	public void testPpt() {
		String text = extractText("test.ppt");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testPptx() {
		String text = extractText("test-2010.pptx");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testRtf() {
		String text = extractText("test.rtf");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testTxt() {
		String text = extractText("test.txt");

		Assert.assertEquals("Extract test.", text);
	}

	@Test
	public void testXls() {
		String text = extractText("test.xls");

		Assert.assertEquals("Sheet1\n\tExtract test.", text);
	}

	@Test
	public void testXlsx() {
		String text = extractText("test-2010.xlsx");

		Assert.assertEquals("Sheet1\n\tExtract test.", text);
	}

	@Test
	public void testXml() {
		String text = extractText("test.xml");

		Assert.assertEquals("<test>Extract test.</test>", text);
	}

	protected String extractText(String fileName) {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		String text = FileUtil.extractText(inputStream, fileName);

		return text.trim();
	}

}