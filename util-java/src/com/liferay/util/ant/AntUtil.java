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
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;

/**
 * @author Brian Wing Shun Chan
 */
public class AntUtil {

	public static Project getProject() {
		Project project = new Project();

		BuildLogger buildLogger = new DefaultLogger() {

			@Override
			public void messageLogged(BuildEvent buildEvent) {
				int priority = buildEvent.getPriority();

				if (priority > msgOutputLevel) {
					return;
				}

				StringBundler sb = new StringBundler();

				try {
					boolean first = true;

					UnsyncBufferedReader unsyncBufferedReader =
						new UnsyncBufferedReader(
							new UnsyncStringReader(buildEvent.getMessage()));

					String line = unsyncBufferedReader.readLine();

					while (line != null) {
						if (!first) {
							sb.append(StringPool.OS_EOL);
						}

						first = false;

						sb.append(StringPool.DOUBLE_SPACE);
						sb.append(line);

						line = unsyncBufferedReader.readLine();
					}
				}
				catch (IOException ioe) {
				}

				String message = sb.toString();

				if (priority != Project.MSG_ERR) {
					printMessage(message, out, priority);
				}
				else {
					printMessage(message, err, priority);
				}

				log(message);
			}

		};

		buildLogger.setErrorPrintStream(System.err);
		buildLogger.setMessageOutputLevel(Project.MSG_INFO);
		buildLogger.setOutputPrintStream(System.out);

		project.addBuildListener(buildLogger);

		return project;
	}

}