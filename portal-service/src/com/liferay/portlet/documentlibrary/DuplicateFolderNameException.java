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

package com.liferay.portlet.documentlibrary;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateFolderNameException extends PortalException {

	public DuplicateFolderNameException() {
		super();
	}

	public DuplicateFolderNameException(String msg) {
		super(msg);
	}

	public DuplicateFolderNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateFolderNameException(Throwable cause) {
		super(cause);
	}

}