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

import aQute.bnd.ant.BndTask;
import aQute.bnd.build.Workspace;

import java.io.File;

import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/**
 * @author Raymond Augé
 */
public abstract class BaseBndTask extends BndTask {

	@Override
	public void execute() throws BuildException {
		try {
			project = getProject();

			doBeforeExecute();
			doExecute();
		}
		catch (Exception e) {
			if (e instanceof BuildException) {
				throw (BuildException)e;
			}

			throw new BuildException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public aQute.bnd.build.Project getBndProject() throws Exception {
		File bndRootFile = getBndRootFile();

		Workspace workspace = Workspace.getWorkspace(
			bndRootFile.getParentFile(), getBndDirName());

		aQute.bnd.build.Project bndProject = new aQute.bnd.build.Project(
			workspace, bndRootFile.getParentFile());

		bndProject.setFileMustExist(true);
		bndProject.setProperties(bndRootFile);

		Properties properties = new Properties();

		properties.putAll(project.getProperties());
		properties.putAll(bndProject.getProperties());

		bndProject.setProperties(properties);

		return bndProject;
	}

	public File getBndRootFile() {
		return _bndRootFile;
	}

	public void setBndRootFile(File bndRootFile) {
		_bndRootFile = bndRootFile;
	}

	protected void doBeforeExecute() throws Exception {
		if ((_bndRootFile == null) || !_bndRootFile.exists() ||
			_bndRootFile.isDirectory()) {

			if (_bndRootFile != null) {
				project.log(
					"bndRootFile is either missing or is a directory " +
						_bndRootFile.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new Exception("bndRootFile is invalid");
		}

		_bndRootFile = _bndRootFile.getAbsoluteFile();
	}

	protected abstract void doExecute() throws Exception;

	protected String getBndDirName() {
		if (_bndDirName != null) {
			return _bndDirName;
		}

		_bndDirName = project.getProperty("baseline.jar.bnddir.name");

		if (_bndDirName == null) {
			_bndDirName = _BND_DIR;
		}

		return _bndDirName;
	}

	protected Project project;

	private static final String _BND_DIR = ".bnd";

	private String _bndDirName;
	private File _bndRootFile;

}