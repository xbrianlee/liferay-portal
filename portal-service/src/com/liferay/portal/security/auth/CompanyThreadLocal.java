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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.TimeZoneThreadLocal;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.CompanyLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class CompanyThreadLocal {

	public static Long getCompanyId() {
		Long companyId = _companyId.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getCompanyId " + companyId);
		}

		return companyId;
	}

	public static boolean isDeleteInProcess() {
		return _deleteInProcess.get();
	}

	public static void setCompanyId(int companyId) {
		setCompanyId(Long.valueOf(companyId));
	}

	public static void setCompanyId(Long companyId) {
		if (_log.isDebugEnabled()) {
			_log.debug("setCompanyId " + companyId);
		}

		if (companyId > 0) {
			try {
				Company company = CompanyLocalServiceUtil.getCompany(companyId);

				LocaleThreadLocal.setDefaultLocale(company.getLocale());
				TimeZoneThreadLocal.setDefaultTimeZone(company.getTimeZone());
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			_companyId.set(companyId);
		}
		else {
			LocaleThreadLocal.setDefaultLocale(null);
			TimeZoneThreadLocal.setDefaultTimeZone(null);

			_companyId.set(CompanyConstants.SYSTEM);
		}
	}

	public static void setDeleteInProcess(boolean deleteInProcess) {
		_deleteInProcess.set(deleteInProcess);
	}

	private static Log _log = LogFactoryUtil.getLog(CompanyThreadLocal.class);

	private static ThreadLocal<Long> _companyId =
		new AutoResetThreadLocal<Long>(
			CompanyThreadLocal.class + "._companyId", CompanyConstants.SYSTEM);
	private static ThreadLocal<Boolean> _deleteInProcess =
		new AutoResetThreadLocal<Boolean>(
			CompanyThreadLocal.class + "._deleteInProcess", false);

}