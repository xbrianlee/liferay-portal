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

package com.liferay.mail.model;

import java.io.File;

/**
 * @author Barrie Selack
 * @author Brian Wing Shun Chan
 */
public class FileAttachment {

	public FileAttachment() {
	}

	public FileAttachment(File file, String fileName) {
		_file = file;
		_fileName = fileName;
	}

	public File getFile() {
		return _file;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFile(File file) {
		_file = file;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	private File _file;
	private String _fileName;

}