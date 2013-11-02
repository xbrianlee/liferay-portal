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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Hashimoto
 */
public class BaseConverter {

	public BaseConverter(SeleniumBuilderContext seleniumBuilderContext) {
		this.seleniumBuilderContext = seleniumBuilderContext;

		this.seleniumBuilderFileUtil = new SeleniumBuilderFileUtil(
			seleniumBuilderContext.getBaseDir());
	}

	protected Map<String, Object> getContext() {
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("seleniumBuilderContext", seleniumBuilderContext);
		context.put("seleniumBuilderFileUtil", seleniumBuilderFileUtil);

		return context;
	}

	protected String processTemplate(String name) throws Exception {
		return processTemplate(name, getContext());
	}

	protected String processTemplate(String name, Map<String, Object> context)
		throws Exception {

		return seleniumBuilderFileUtil.processTemplate(name, context);
	}

	protected SeleniumBuilderContext seleniumBuilderContext;
	protected SeleniumBuilderFileUtil seleniumBuilderFileUtil;

}