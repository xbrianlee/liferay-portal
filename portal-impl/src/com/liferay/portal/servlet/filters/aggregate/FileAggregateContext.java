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

package com.liferay.portal.servlet.filters.aggregate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

/**
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public class FileAggregateContext extends BaseAggregateContext {

	public FileAggregateContext(String docrootPath, String resourcePath) {
		int pos = resourcePath.lastIndexOf(StringPool.SLASH);

		if (pos > -1) {
			resourcePath = resourcePath.substring(0, pos + 1);
		}

		pushPath(docrootPath);
		pushPath(resourcePath);
	}

	@Override
	public String getContent(String path) {
		try {
			pushPath(path);

			String fullPath = getFullPath(StringPool.BLANK);

			popPath();

			return FileUtil.read(fullPath);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return null;
	}

	@Override
	public String getResourcePath(String path) {
		String docrootPath = shiftPath();

		String fullPath = getFullPath(StringPool.BLANK);

		unshiftPath(docrootPath);

		return fullPath.concat(path);
	}

	private static Log _log = LogFactoryUtil.getLog(FileAggregateContext.class);

}