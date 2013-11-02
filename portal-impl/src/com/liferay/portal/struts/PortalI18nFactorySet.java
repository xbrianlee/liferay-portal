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

package com.liferay.portal.struts;

import javax.servlet.ServletContext;

import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;
import org.apache.struts.tiles.xmlDefinition.XmlDefinitionsSet;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalI18nFactorySet extends I18nFactorySet {

	@Override
	protected XmlDefinitionsSet parseXmlFiles(
			ServletContext servletContext, String postfix,
			XmlDefinitionsSet xmlDefinitionsSet)
		throws DefinitionsFactoryException {

		return super.parseXmlFiles(servletContext, postfix, xmlDefinitionsSet);
	}

}