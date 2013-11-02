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

package com.liferay.portal.security.ac;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifierResult;

import java.lang.annotation.Annotation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public interface AccessControl {

	public void initAccessControlContext(
		HttpServletRequest request, HttpServletResponse response,
		Map<String, Object> settings);

	public void initContextUser(long userId) throws AuthException;

	public AuthVerifierResult.State verifyRequest()
		throws PortalException, SystemException;

	public AccessControlled NULL_ACCESS_CONTROLLED = new AccessControlled() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return AccessControlled.class;
		}

		@Override
		public boolean guestAccessEnabled() {
			return false;
		}

		@Override
		public boolean hostAllowedValidationEnabled() {
			return false;
		}

	};

}