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

package com.liferay.portal.kernel.memory;

import java.io.File;

/**
 * @author Shuyang Zhou
 */
public class DeleteFileFinalizeAction implements FinalizeAction {

	public DeleteFileFinalizeAction(String fileName) {
		_fileName = fileName;
	}

	@Override
	public void doFinalize() {
		File file = new File(_fileName);

		file.delete();
	}

	private final String _fileName;

}