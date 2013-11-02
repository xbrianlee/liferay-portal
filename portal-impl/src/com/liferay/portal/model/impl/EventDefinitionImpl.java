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
import com.liferay.portal.model.EventDefinition;
import com.liferay.portal.model.PortletApp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class EventDefinitionImpl implements EventDefinition {

	public EventDefinitionImpl(
		QName qName, String valueType, PortletApp portletApp) {

		_qName = qName;
		_valueType = valueType;
		_portletApp = portletApp;

		_qNames = new HashSet<QName>();

		_qNames.add(_qName);
	}

	@Override
	public void addAliasQName(QName aliasQName) {
		_qNames.add(aliasQName);
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
	public Set<QName> getQNames() {
		return _qNames;
	}

	@Override
	public String getValueType() {
		return _valueType;
	}

	@Override
	public void setPortletApp(PortletApp portletApp) {
		_portletApp = portletApp;
	}

	@Override
	public void setQName(QName qName) {
		_qName = qName;
	}

	@Override
	public void setValueType(String valueType) {
		_valueType = valueType;
	}

	private PortletApp _portletApp;
	private QName _qName;
	private Set<QName> _qNames;
	private String _valueType;

}