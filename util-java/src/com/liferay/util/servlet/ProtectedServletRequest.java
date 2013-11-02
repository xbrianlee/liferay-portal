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

package com.liferay.util.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0, moved to {@link
 *             com.liferay.portal.kernel.servlet.ProtectedServletRequest}
 */
public class ProtectedServletRequest
	extends com.liferay.portal.kernel.servlet.ProtectedServletRequest {

	public ProtectedServletRequest(
		HttpServletRequest request, String remoteUser) {

		super(request, remoteUser);
	}

}