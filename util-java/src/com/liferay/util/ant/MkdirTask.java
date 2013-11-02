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

import org.apache.tools.ant.taskdefs.Mkdir;

/**
 * @author Brian Wing Shun Chan
 */
public class MkdirTask {

	public static void mkdir(File dir) {
		Mkdir mkdir = new Mkdir();

		mkdir.setProject(AntUtil.getProject());
		mkdir.setDir(dir);

		mkdir.execute();
	}

	public static void mkdir(String dir) {
		mkdir(new File(dir));
	}

}