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

package com.liferay.portal.tools.seleniumbuilder;

import java.util.Map;

/**
 * @author Michael Hashimoto
 */
public class TestCaseConverter extends BaseConverter {

	public TestCaseConverter(SeleniumBuilderContext seleniumBuilderContext) {
		super(seleniumBuilderContext);
	}

	public void convert(String testCaseName) throws Exception {
		Map<String, Object> context = getContext();

		context.put("macroNameStack", new FreeMarkerStack());
		context.put("testCaseName", testCaseName);

		String javaContent = processTemplate("test_case.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getTestCaseJavaFileName(testCaseName),
			javaContent, true);

		String htmlContent = processTemplate("test_case_html.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getTestCaseHTMLFileName(testCaseName),
			htmlContent, false);
	}

}