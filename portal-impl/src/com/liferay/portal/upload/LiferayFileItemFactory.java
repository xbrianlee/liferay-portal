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

package com.liferay.portal.upload;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayFileItemFactory extends DiskFileItemFactory {

	public static final int DEFAULT_SIZE = 1024;

	public LiferayFileItemFactory(File tempDir) {
		_tempDir = tempDir;
	}

	@Override
	public FileItem createItem(
		String fieldName, String contentType, boolean isFormField,
		String fileName) {

		return new LiferayFileItem(
			fieldName, contentType, isFormField, fileName, DEFAULT_SIZE,
			_tempDir);
	}

	private File _tempDir;

}