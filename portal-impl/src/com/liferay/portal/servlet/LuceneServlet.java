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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.search.lucene.LuceneHelperUtil;
import com.liferay.portal.security.auth.TransientTokenUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 */
public class LuceneServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String pathInfo = GetterUtil.getString(request.getPathInfo());

		if (!pathInfo.equals("/dump")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		String transientToken = ParamUtil.getString(request, "transientToken");

		if (Validator.isNull(transientToken)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);

			return;
		}

		if (!TransientTokenUtil.checkToken(transientToken)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

			return;
		}

		long companyId = ParamUtil.getLong(request, "companyId");

		if (companyId < CompanyConstants.SYSTEM) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);

			return;
		}

		if (companyId != CompanyConstants.SYSTEM) {
			try {
				CompanyLocalServiceUtil.getCompanyById(companyId);
			}
			catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);

				return;
			}
		}

		LuceneHelperUtil.dumpIndex(companyId, response.getOutputStream());
	}

}