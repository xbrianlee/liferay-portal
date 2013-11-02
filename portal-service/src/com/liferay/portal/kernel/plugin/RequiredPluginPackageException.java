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

package com.liferay.portal.kernel.plugin;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class RequiredPluginPackageException extends PortalException {

	public RequiredPluginPackageException() {
		super();
	}

	public RequiredPluginPackageException(String msg) {
		super(msg);
	}

	public RequiredPluginPackageException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredPluginPackageException(Throwable cause) {
		super(cause);
	}

	public String getContext() {
		return _context;
	}

	public void setContext(String context) {
		_context = context;
	}

	private String _context;

}