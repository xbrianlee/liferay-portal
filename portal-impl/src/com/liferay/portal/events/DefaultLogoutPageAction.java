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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jerry Niu
 */
public class DefaultLogoutPageAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String path = PrefsPropsUtil.getString(
			companyId, PropsKeys.DEFAULT_LOGOUT_PAGE_PATH);

		if (_log.isInfoEnabled()) {
			_log.info(
				PropsKeys.DEFAULT_LOGOUT_PAGE_PATH + StringPool.EQUAL + path);
		}

		if (Validator.isNotNull(path)) {
			request.setAttribute(WebKeys.REFERER, path);
		}

		// The commented code shows how you can programmaticaly set the user's
		// logout page. You can modify this class to utilize a custom algorithm
		// for forwarding a user to his logout page. See the references to this
		// class in portal.properties.

		/*Map<String, String[]> params = new HashMap<String, String[]>();

		params.put("p_l_id", new String[] {"1806"});

		LastPath lastPath = new LastPath("/c", "/portal/layout", params);

		session.setAttribute(WebKeys.LAST_PATH, lastPath);*/
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultLogoutPageAction.class);

}