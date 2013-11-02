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

package com.liferay.portal.model;

import com.liferay.portal.kernel.xml.QName;

import java.io.Serializable;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface EventDefinition extends Serializable {

	public void addAliasQName(QName aliasQName);

	public PortletApp getPortletApp();

	public QName getQName();

	public Set<QName> getQNames();

	public String getValueType();

	public void setPortletApp(PortletApp portletApp);

	public void setQName(QName qName);

	public void setValueType(String valueType);

}