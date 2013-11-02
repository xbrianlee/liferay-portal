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

package com.liferay.portal.monitoring.statistics.portlet;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.monitoring.MonitorNames;
import com.liferay.portal.monitoring.statistics.BaseDataSample;
import com.liferay.portlet.PortletResponseImpl;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Karthik Sudarshan
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class PortletRequestDataSample extends BaseDataSample {

	public PortletRequestDataSample(
		PortletRequestType requestType, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		PortletResponseImpl portletResponseImpl =
			(PortletResponseImpl)portletResponse;

		Portlet portlet = portletResponseImpl.getPortlet();

		setCompanyId(portlet.getCompanyId());
		setUser(portletRequest.getRemoteUser());
		setNamespace(MonitorNames.PORTLET);
		setName(portlet.getPortletName());
		_portletId = portlet.getPortletId();
		_displayName = portlet.getDisplayName();
		_requestType = requestType;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public String getPortletId() {
		return _portletId;
	}

	public PortletRequestType getRequestType() {
		return _requestType;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{displayName=");
		sb.append(_displayName);
		sb.append(", portletId=");
		sb.append(_portletId);
		sb.append(", requestType=");
		sb.append(_requestType);
		sb.append(", ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private String _displayName;
	private String _portletId;
	private PortletRequestType _requestType;

}