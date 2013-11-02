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
import org.apache.tools.ant.taskdefs.War;

/**
 * @author Brian Wing Shun Chan
 */
public class WarTask {

	public static void war(
		File baseDir, File destination, String excludes, File webxml) {

		Project project = AntUtil.getProject();

		War war = new War();

		war.setBasedir(baseDir);
		war.setDestFile(destination);
		war.setExcludes(excludes);

		File manifestFile = new File(
			baseDir.getAbsolutePath() + "/META-INF/MANIFEST.MF");

		if (manifestFile.exists()) {
			war.setManifest(manifestFile);
		}

		war.setProject(project);
		war.setWebxml(webxml);

		war.execute();
	}

	public static void war(
		String baseDir, String destination, String excludes, String webxml) {

		war(
			new File(baseDir), new File(destination), excludes,
			new File(webxml));
	}

}