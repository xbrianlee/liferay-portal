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

import org.apache.tools.ant.taskdefs.Expand;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpandTask {

	public static void expand(File source, File destination) {
		Expand expand = new Expand();

		expand.setDest(destination);
		expand.setProject(AntUtil.getProject());
		expand.setSrc(source);

		expand.execute();
	}

	public static void expand(String source, String destination) {
		expand(new File(source), new File(destination));
	}

}