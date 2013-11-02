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

package com.liferay.portal.kernel.deploy.auto.context;

import java.io.File;

/**
 * @author Miguel Pastor
 */
public class AutoDeploymentContext {

	public String getAppServerType() {
		return _appServerType;
	}

	public String getContext() {
		return _context;
	}

	public File getDeployDir() {
		return new File(_destDir, _context);
	}

	public String getDestDir() {
		return _destDir;
	}

	public File getFile() {
		return _file;
	}

	public void setAppServerType(String appServerType) {
		_appServerType = appServerType;
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setDestDir(String destDir) {
		_destDir = destDir;
	}

	public void setFile(File file) {
		_file = file;
	}

	private String _appServerType;
	private String _context;
	private String _destDir;
	private File _file;

}