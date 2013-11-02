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

import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author Hugo Huijser
 */
public class SHSourceProcessor extends BaseSourceProcessor {

	@Override
	protected void format() throws Exception {
		format("ext/create.sh");
		format("hooks/create.sh");
		format("layouttpl/create.sh");
		format("portlets/create.sh");
		format("themes/create.sh");
	}

	@Override
	protected String format(String fileName) throws IOException {
		File file = new File(fileName);

		if (!file.exists()) {
			return null;
		}

		String content = fileUtil.read(new File(fileName), true);

		if (content.contains("\r")) {
			processErrorMessage(fileName, "Invalid new line character");

			if (isAutoFix()) {
				content = StringUtil.replace(content, "\r", "");

				fileUtil.write(fileName, content);
			}
		}

		return content;
	}

}