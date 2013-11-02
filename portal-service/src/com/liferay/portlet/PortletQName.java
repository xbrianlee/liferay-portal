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

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.QName;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletQName {

	public static final String PUBLIC_RENDER_PARAMETER_NAMESPACE = "p_r_p_";

	public static final String REMOVE_PUBLIC_RENDER_PARAMETER_NAMESPACE =
		"r_p_r_p";

	public String getKey(QName qName);

	public String getKey(String uri, String localPart);

	public String getPublicRenderParameterIdentifier(
		String publicRenderParameterName);

	public String getPublicRenderParameterName(QName qName);

	public QName getQName(
		Element qNameEl, Element nameEl, String defaultNamespace);

	public QName getQName(String publicRenderParameterName);

	public String getRemovePublicRenderParameterName(QName qName);

	public void setPublicRenderParameterIdentifier(
		String publicRenderParameterName, String identifier);

}