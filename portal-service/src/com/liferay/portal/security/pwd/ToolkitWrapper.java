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

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PasswordPolicy;

/**
 * @author Brian Wing Shun Chan
 */
public class ToolkitWrapper implements Toolkit {

	public ToolkitWrapper(Toolkit toolkit) {
		_originalToolkit = toolkit;
		_toolkit = toolkit;
	}

	@Override
	public String generate(PasswordPolicy passwordPolicy) {
		return _toolkit.generate(passwordPolicy);
	}

	public void setToolkit(Toolkit toolkit) {
		if (toolkit == null) {
			_toolkit = _originalToolkit;
		}
		else {
			_toolkit = toolkit;
		}
	}

	@Override
	public void validate(
			long userId, String password1, String password2,
			PasswordPolicy passwordPolicy)
		throws PortalException, SystemException {

		_toolkit.validate(userId, password1, password2, passwordPolicy);
	}

	@Override
	public void validate(
			String password1, String password2, PasswordPolicy passwordPolicy)
		throws PortalException, SystemException {

		_toolkit.validate(password1, password2, passwordPolicy);
	}

	private Toolkit _originalToolkit;
	private Toolkit _toolkit;

}