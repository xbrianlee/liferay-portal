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

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.UpToDate;

/**
 * @author Brian Wing Shun Chan
 */
public class UpToDateTask {

	public static boolean isUpToDate(File source, File target) {
		if (!source.exists() || !target.exists()) {
			return false;
		}

		Project project = AntUtil.getProject();

		UpToDate upToDate = new UpToDate();

		upToDate.setProject(project);
		upToDate.setProperty("uptodate");
		upToDate.setSrcfile(source);
		upToDate.setTargetFile(target);

		upToDate.execute();

		if (project.getProperty("uptodate") != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isUpToDate(String source, String target) {
		return isUpToDate(new File(source), new File(target));
	}

}