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

package com.liferay.portal.googleapps;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PrefsPropsUtil;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class GAuthenticator {

	public GAuthenticator(long companyId) {
		_companyId = companyId;

		init(true);
	}

	public GAuthenticator(String domain, String userName, String password) {
		_domain = domain;
		_userName = userName;
		_password = password;

		init(true);
	}

	public String getAuthenticationToken() {
		init(false);

		return _authenticationToken;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getDomain() {
		return _domain;
	}

	public String getError() {
		return _error;
	}

	public void init(boolean manual) {
		if (manual || isStale()) {
			try {
				doInit();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	protected void doInit() throws Exception {
		if (_companyId > 0) {
			Company company = CompanyLocalServiceUtil.getCompany(_companyId);

			_domain = company.getMx();
			_userName = PrefsPropsUtil.getString(
				_companyId, PropsKeys.GOOGLE_APPS_USERNAME);
			_password = PrefsPropsUtil.getString(
				_companyId, PropsKeys.GOOGLE_APPS_PASSWORD);
		}

		Http.Options options = new Http.Options();

		options.addPart("accountType", "HOSTED");

		String emailAddress = _userName;

		if (!emailAddress.contains(StringPool.AT)) {
			emailAddress = _userName.concat(StringPool.AT).concat(_domain);
		}

		options.addPart("Email", emailAddress);

		options.addPart("Passwd", _password);
		options.addPart("service", "apps");
		options.setLocation("https://www.google.com/accounts/ClientLogin");
		options.setPost(true);

		String content = HttpUtil.URLtoString(options);

		Properties properties = PropertiesUtil.load(content);

		_error = properties.getProperty("Error");

		if ((_error != null) && _log.isInfoEnabled()) {
			_log.info("Unable to initialize authentication token: " + _error);
		}

		_authenticationToken = properties.getProperty("Auth");

		if ((_authenticationToken != null) && _log.isInfoEnabled()) {
			_log.info("Authentication token " + _authenticationToken);
		}

		_initTime = System.currentTimeMillis();
	}

	protected boolean isStale() {
		if ((_initTime + _EXPIRE_TIME) > System.currentTimeMillis()) {
			return false;
		}
		else {
			return true;
		}
	}

	private static final long _EXPIRE_TIME = Time.HOUR;

	private static Log _log = LogFactoryUtil.getLog(GAuthenticator.class);

	private String _authenticationToken;
	private long _companyId;
	private String _domain;
	private String _error;
	private long _initTime;
	private String _password;
	private String _userName;

}