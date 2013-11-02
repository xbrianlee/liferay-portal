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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.StringUtils;

/**
 * @author Brian Wing Shun Chan
 */
public class SystemLogger extends DefaultLogger {

	@Override
	public void messageLogged(BuildEvent event) {
		int priority = event.getPriority();

		if (priority > msgOutputLevel) {
			return;
		}

		StringBundler sb = new StringBundler();

		try {
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new UnsyncStringReader(event.getMessage()));

			String line = unsyncBufferedReader.readLine();

			boolean first = true;

			while (line != null) {
				if (!first) {
					sb.append(StringUtils.LINE_SEP);
				}

				first = false;

				sb.append("  ");
				sb.append(line);

				line = unsyncBufferedReader.readLine();
			}
		}
		catch (IOException ioe) {
		}

		String msg = sb.toString();

		if (priority != Project.MSG_ERR) {
			printMessage(msg, out, priority);
		}
		else {
			printMessage(msg, err, priority);
		}

		log(msg);
	}

}