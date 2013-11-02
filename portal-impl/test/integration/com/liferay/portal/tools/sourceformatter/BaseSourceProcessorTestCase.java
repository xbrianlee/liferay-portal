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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Tuple;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;

/**
 * @author Hugo Huijser
 */
public class BaseSourceProcessorTestCase {

	@Before
	public void setUp() throws Exception {
		sourceFormatter = SourceFormatterUtil.create(
			false, false, false, false);
	}

	protected void test(String fileName) throws Exception {
		test(fileName, new String[0]);
	}

	protected void test(String fileName, String expectedErrorMessage)
		throws Exception {

		test(fileName, new String[] {expectedErrorMessage});
	}

	protected void test(
			String fileName, String expectedErrorMessage, int lineNumber)
		throws Exception {

		test(
			fileName, new String[] {expectedErrorMessage},
			new Integer[] {lineNumber});
	}

	protected void test(String fileName, String[] expectedErrorMessages)
		throws Exception {

		test(fileName, expectedErrorMessages, null);
	}

	protected void test(
			String fileName, String[] expectedErrorMessages,
			Integer[] lineNumbers)
		throws Exception {

		String fullFileName = _DIR_NAME + StringPool.SLASH + fileName;

		Tuple tuple = sourceFormatter.format(fullFileName);

		if (tuple == null) {
			return;
		}

		List<String> errorMessages = (List<String>)tuple.getObject(1);

		if (!errorMessages.isEmpty() || (expectedErrorMessages.length > 0)) {
			Assert.assertEquals(
				errorMessages.size(), expectedErrorMessages.length);

			for (int i = 0; i < errorMessages.size(); i++) {
				String actualErrorMessage = errorMessages.get(i);
				String expectedErrorMessage = expectedErrorMessages[i];

				StringBundler sb = new StringBundler(5);

				sb.append(expectedErrorMessage);
				sb.append(StringPool.SPACE);
				sb.append(fullFileName);

				if (lineNumbers != null) {
					sb.append(StringPool.SPACE);
					sb.append(lineNumbers[i]);
				}

				Assert.assertEquals(sb.toString(), actualErrorMessage);
			}
		}
		else {
			String actualFormattedContent = (String)tuple.getObject(0);

			try {
				File file = new File(_DIR_NAME + "/expected/" + fileName);

				String expectedFormattedContent = FileUtil.read(file.getPath());

				Assert.assertEquals(
					expectedFormattedContent, actualFormattedContent);
			}
			catch (FileNotFoundException fnfe) {
				Assert.fail();
			}
		}
	}

	protected SourceFormatter sourceFormatter;

	private static final String _DIR_NAME =
		"portal-impl/test/integration/com/liferay/portal/tools/" +
			"sourceformatter/dependencies";

}