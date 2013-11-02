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

package com.liferay.util.ant;

import com.liferay.portal.kernel.util.StringPool;

import java.text.MessageFormat;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Echo;

/**
 * @author Miguel Pastor
 */
public class FormatTask extends Echo {

	@Override
	public void execute() throws BuildException {
		if (message == null) {
			throw new BuildException("The message attribute is mandatory");
		}

		Object[] arguments = _arguments.split(_separator);

		String value = MessageFormat.format(message, arguments);

		if (_property != null) {
			Project project = getProject();

			project.setProperty(_property, value);
		}
		else {
			setMessage(value);

			super.execute();
		}
	}

	public void setArguments(String arguments) {
		_arguments = arguments;
	}

	public void setProperty(String property) {
		_property = property;
	}

	public void setSeparator(String separator) {
		_separator = separator;
	}

	private String _arguments;
	private String _property;
	private String _separator = StringPool.COMMA;

}