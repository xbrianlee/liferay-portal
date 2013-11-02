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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsUtilTask extends Task {

	@Override
	public void execute() throws BuildException {
		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		try {
			Class<?> clazz = getClass();

			ClassLoaderUtil.setContextClassLoader(clazz.getClassLoader());

			Project project = getProject();

			project.setUserProperty(_result, PropsUtil.get(_key));
		}
		finally {
			ClassLoaderUtil.setContextClassLoader(contextClassLoader);
		}
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setResult(String result) {
		_result = result;
	}

	private String _key;
	private String _result;

}