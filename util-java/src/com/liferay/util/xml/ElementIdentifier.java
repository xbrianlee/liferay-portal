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

package com.liferay.util.xml;

/**
 * @author Jorge Ferrer
 */
public class ElementIdentifier {

	public ElementIdentifier(String elementName, String identifierName) {
		_elementName = elementName;
		_identifierName = identifierName;
	}

	public String getElementName() {
		return _elementName;
	}

	public String getIdentifierName() {
		return _identifierName;
	}

	private String _elementName;
	private String _identifierName;

}