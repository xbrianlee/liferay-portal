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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Shuyang Zhou
 */
public class PortletParameterUtil {

	public static String addNamespace(String portletId, String queryString) {
		String[] parameters = StringUtil.split(queryString, CharPool.AMPERSAND);

		StringBundler sb = new StringBundler(2 + parameters.length * 4);

		sb.append("p_p_id=");
		sb.append(portletId);

		for (String parameter : parameters) {
			sb.append("&_");
			sb.append(portletId);
			sb.append("_");
			sb.append(parameter);
		}

		return sb.toString();
	}

}