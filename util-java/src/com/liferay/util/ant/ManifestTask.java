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

import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.ManifestException;

/**
 * @author Brian Wing Shun Chan
 */
public class ManifestTask {

	public static void manifest(File file, Manifest.Attribute[] attributes)
		throws ManifestException {

		String parentFile = file.getParent();

		if (parentFile != null) {
			MkdirTask.mkdir(parentFile);
		}

		org.apache.tools.ant.taskdefs.ManifestTask manifest =
			new org.apache.tools.ant.taskdefs.ManifestTask();

		manifest.setProject(AntUtil.getProject());
		manifest.setFile(file);

		for (int i = 0; i < attributes.length; i++) {
			manifest.addConfiguredAttribute(attributes[i]);
		}

		manifest.execute();
	}

	public static void manifest(String file, Manifest.Attribute[] attributes)
		throws ManifestException {

		manifest(new File(file), attributes);
	}

}