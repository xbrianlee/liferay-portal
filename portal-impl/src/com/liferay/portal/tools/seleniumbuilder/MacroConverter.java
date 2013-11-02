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
public class MacroConverter extends BaseConverter {

	public MacroConverter(SeleniumBuilderContext seleniumBuilderContext) {
		super(seleniumBuilderContext);
	}

	public void convert(String macroName) throws Exception {
		Map<String, Object> context = getContext();

		context.put("macroElementsStack", new FreeMarkerStack());
		context.put("macroNameStack", new FreeMarkerStack());
		context.put("macroName", macroName);

		String content = processTemplate("macro.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getMacroJavaFileName(macroName), content,
			true);
	}

}