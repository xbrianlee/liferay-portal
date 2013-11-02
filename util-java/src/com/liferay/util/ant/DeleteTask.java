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

import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.types.FileSet;

/**
 * @author Brian Wing Shun Chan
 */
public class DeleteTask {

	public static void deleteDirectory(File dir) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setDir(dir);
		delete.setFailOnError(false);

		delete.execute();
	}

	public static void deleteDirectory(String dir) {
		deleteDirectory(new File(dir));
	}

	public static void deleteFile(File file) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setFile(file);
		delete.setFailOnError(false);

		delete.execute();
	}

	public static void deleteFile(String file) {
		deleteFile(new File(file));
	}

	public static void deleteFiles(File dir, String includes, String excludes) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setFailOnError(false);

		FileSet fileSet = new FileSet();

		fileSet.setDir(dir);
		fileSet.setIncludes(includes);
		fileSet.setExcludes(excludes);

		delete.addFileset(fileSet);

		delete.execute();
	}

	public static void deleteFiles(
		String dir, String includes, String excludes) {

		deleteFiles(new File(dir), includes, excludes);
	}

}