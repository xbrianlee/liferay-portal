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

package com.liferay.util.ant.bnd;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/**
 * @author Raymond Augé
 */
public class ReleaseToRepoTask extends BaseBndTask {

	public void setDeployRepo(String name) {
		_deployRepo = name;
	}

	public void setFile(File file) {
		_file = file;
	}

	@Override
	protected void doBeforeExecute() throws Exception {
		super.doBeforeExecute();

		if ((_file == null) || !_file.exists() || _file.isDirectory()) {
			if (_file != null) {
				project.log(
					"file is either missing or is a directory " +
						_file.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new BuildException("file is invalid");
		}
	}

	@Override
	protected void doExecute() throws Exception {
		aQute.bnd.build.Project bndProject = getBndProject();

		try {
			if (_file.isFile() && _file.getName().endsWith(".jar")) {
				if (_deployRepo != null) {
					bndProject.deploy(_deployRepo, _file);
				}
				else {
					bndProject.deploy(_file);
				}
			}
			else {
				project.log(
					"Not a JAR file " + _file.getAbsolutePath(),
					Project.MSG_ERR);
			}
		}
		catch (Exception e) {
			throw new BuildException(
				e.getMessage(), e,
				new org.apache.tools.ant.Location(_file.getAbsolutePath()));
		}

		report(bndProject);

		if (bndProject.getErrors().size() > 0) {
			throw new BuildException(
				"Unable to deploy",
				new org.apache.tools.ant.Location(_file.getAbsolutePath()));
		}
	}

	private String _deployRepo;
	private File _file;

}