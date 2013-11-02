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

package com.liferay.portal.xml.xpath;

import java.util.Map;

import org.jaxen.NamespaceContext;

/**
 * @author Eduardo Lundgren
 */
public class LiferayNamespaceContext implements NamespaceContext {

	public LiferayNamespaceContext(Map<String, String> namespaceContextMap) {
		_namespaceContextMap = namespaceContextMap;
	}

	@Override
	public String translateNamespacePrefixToUri(String prefix) {
		if (_namespaceContextMap == null) {
			return null;
		}

		return _namespaceContextMap.get(prefix);
	}

	private Map<String, String> _namespaceContextMap;

}