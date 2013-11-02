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

package com.liferay.portal.monitoring.statistics.portal;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.monitoring.MonitorNames;
import com.liferay.portal.monitoring.statistics.BaseDataSample;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class PortalRequestDataSample extends BaseDataSample {

	public PortalRequestDataSample(
		long companyId, String user, String requestURI, String requestURL) {

		setCompanyId(companyId);
		setUser(user);
		setNamespace(MonitorNames.PORTAL);
		setName(requestURI);
		_requestURL = requestURL;
	}

	public String getRequestURL() {
		return _requestURL;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{requestURL=");
		sb.append(_requestURL);
		sb.append(", ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private String _requestURL;

}