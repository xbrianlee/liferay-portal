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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PublicRenderParameter;

/**
 * @author Brian Wing Shun Chan
 */
public class PublicRenderParameterImpl implements PublicRenderParameter {

	public PublicRenderParameterImpl(
		String identifier, QName qName, PortletApp portletApp) {

		_identifier = identifier;
		_qName = qName;
		_portletApp = portletApp;
	}

	@Override
	public String getIdentifier() {
		return _identifier;
	}

	@Override
	public PortletApp getPortletApp() {
		return _portletApp;
	}

	@Override
	public QName getQName() {
		return _qName;
	}

	@Override
	public void setIdentifier(String identifier) {
		_identifier = identifier;
	}

	@Override
	public void setPortletApp(PortletApp portletApp) {
		_portletApp = portletApp;
	}

	@Override
	public void setQName(QName qName) {
		_qName = qName;
	}

	private String _identifier;
	private PortletApp _portletApp;
	private QName _qName;

}