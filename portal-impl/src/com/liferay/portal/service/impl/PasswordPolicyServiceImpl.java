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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.PasswordPolicyServiceBaseImpl;
import com.liferay.portal.service.permission.PasswordPolicyPermissionUtil;
import com.liferay.portal.service.permission.PortalPermissionUtil;

/**
 * @author Scott Lee
 */
public class PasswordPolicyServiceImpl extends PasswordPolicyServiceBaseImpl {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #addPasswordPolicy(String,
	 *             String, boolean, boolean, long, boolean, boolean, int, int,
	 *             int, int, int, int, String, boolean, int, boolean, long,
	 *             long, int, boolean, int, long, long, long, ServiceContext)}
	 */
	@Override
	public PasswordPolicy addPasswordPolicy(
			String name, String description, boolean changeable,
			boolean changeRequired, long minAge, boolean checkSyntax,
			boolean allowDictionaryWords, int minAlphanumeric, int minLength,
			int minLowerCase, int minNumbers, int minSymbols, int minUpperCase,
			boolean history, int historyCount, boolean expireable, long maxAge,
			long warningTime, int graceLimit, boolean lockout, int maxFailure,
			long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_PASSWORD_POLICY);

		return passwordPolicyLocalService.addPasswordPolicy(
			getUserId(), false, name, description, changeable, changeRequired,
			minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
			minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
			history, historyCount, expireable, maxAge, warningTime, graceLimit,
			lockout, maxFailure, lockoutDuration, resetFailureCount,
			resetTicketMaxAge);
	}

	@Override
	public PasswordPolicy addPasswordPolicy(
			String name, String description, boolean changeable,
			boolean changeRequired, long minAge, boolean checkSyntax,
			boolean allowDictionaryWords, int minAlphanumeric, int minLength,
			int minLowerCase, int minNumbers, int minSymbols, int minUpperCase,
			String regex, boolean history, int historyCount, boolean expireable,
			long maxAge, long warningTime, int graceLimit, boolean lockout,
			int maxFailure, long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_PASSWORD_POLICY);

		return passwordPolicyLocalService.addPasswordPolicy(
			getUserId(), false, name, description, changeable, changeRequired,
			minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
			minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
			regex, history, historyCount, expireable, maxAge, warningTime,
			graceLimit, lockout, maxFailure, lockoutDuration, resetFailureCount,
			resetTicketMaxAge, serviceContext);
	}

	@Override
	public void deletePasswordPolicy(long passwordPolicyId)
		throws PortalException, SystemException {

		PasswordPolicyPermissionUtil.check(
			getPermissionChecker(), passwordPolicyId, ActionKeys.DELETE);

		passwordPolicyLocalService.deletePasswordPolicy(passwordPolicyId);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #updatePasswordPolicy(long,
	 *             String, String, boolean, boolean, long, boolean, boolean,
	 *             int, int, int, int, int, int, String, boolean, int, boolean,
	 *             long, long, int, boolean, int, long, long, long,
	 *             ServiceContext)}
	 */
	@Override
	public PasswordPolicy updatePasswordPolicy(
			long passwordPolicyId, String name, String description,
			boolean changeable, boolean changeRequired, long minAge,
			boolean checkSyntax, boolean allowDictionaryWords,
			int minAlphanumeric, int minLength, int minLowerCase,
			int minNumbers, int minSymbols, int minUpperCase, boolean history,
			int historyCount, boolean expireable, long maxAge, long warningTime,
			int graceLimit, boolean lockout, int maxFailure,
			long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge)
		throws PortalException, SystemException {

		PasswordPolicyPermissionUtil.check(
			getPermissionChecker(), passwordPolicyId, ActionKeys.UPDATE);

		return passwordPolicyLocalService.updatePasswordPolicy(
			passwordPolicyId, name, description, changeable, changeRequired,
			minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
			minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
			history, historyCount, expireable, maxAge, warningTime, graceLimit,
			lockout, maxFailure, lockoutDuration, resetFailureCount,
			resetTicketMaxAge);
	}

	@Override
	public PasswordPolicy updatePasswordPolicy(
			long passwordPolicyId, String name, String description,
			boolean changeable, boolean changeRequired, long minAge,
			boolean checkSyntax, boolean allowDictionaryWords,
			int minAlphanumeric, int minLength, int minLowerCase,
			int minNumbers, int minSymbols, int minUpperCase, String regex,
			boolean history, int historyCount, boolean expireable, long maxAge,
			long warningTime, int graceLimit, boolean lockout, int maxFailure,
			long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PasswordPolicyPermissionUtil.check(
			getPermissionChecker(), passwordPolicyId, ActionKeys.UPDATE);

		return passwordPolicyLocalService.updatePasswordPolicy(
			passwordPolicyId, name, description, changeable, changeRequired,
			minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
			minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
			regex, history, historyCount, expireable, maxAge, warningTime,
			graceLimit, lockout, maxFailure, lockoutDuration, resetFailureCount,
			resetTicketMaxAge, serviceContext);
	}

}