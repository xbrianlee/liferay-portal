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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael Young
 */
public class PublicRenderParametersPool {

	public static Map<String, String[]> get(
		HttpServletRequest request, long plid) {

		if (PropsValues.PORTLET_PUBLIC_RENDER_PARAMETER_DISTRIBUTION_LAYOUT) {
			return RenderParametersPool.get(
				request, plid, _PUBLIC_RENDER_PARAMETERS);
		}

		HttpSession session = request.getSession();

		Map<Long, Map<String, String[]>> publicRenderParametersPool =
			(Map<Long, Map<String, String[]>>)session.getAttribute(
				WebKeys.PUBLIC_RENDER_PARAMETERS_POOL);

		if (publicRenderParametersPool == null) {
			publicRenderParametersPool =
				new ConcurrentHashMap<Long, Map<String, String[]>>();

			session.setAttribute(
				WebKeys.PUBLIC_RENDER_PARAMETERS_POOL,
				publicRenderParametersPool);
		}

		try {
			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			LayoutSet layoutSet = layout.getLayoutSet();

			Map<String, String[]> publicRenderParameters =
				publicRenderParametersPool.get(layoutSet.getLayoutSetId());

			if (publicRenderParameters == null) {
				publicRenderParameters = new HashMap<String, String[]>();

				publicRenderParametersPool.put(
					layoutSet.getLayoutSetId(), publicRenderParameters);
			}

			return publicRenderParameters;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return new HashMap<String, String[]>();
		}
	}

	private static final String _PUBLIC_RENDER_PARAMETERS =
		"PUBLIC_RENDER_PARAMETERS";

	private static Log _log = LogFactoryUtil.getLog(
		PublicRenderParametersPool.class);

}