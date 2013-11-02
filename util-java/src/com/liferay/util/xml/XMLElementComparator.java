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

import com.liferay.util.xml.descriptor.XMLDescriptor;

import org.dom4j.Element;

/**
 * @author Jorge Ferrer
 */
public class XMLElementComparator extends ElementComparator {

	public XMLElementComparator(XMLDescriptor descriptor) {
		_descriptor = descriptor;
	}

	public boolean canJoinChildren(Element element) {
		return _descriptor.canJoinChildren(element);
	}

	@Override
	public int compare(Element el1, Element el2) {
		if (_descriptor.areEqual(el1, el2)) {
			return 0;
		}
		else {
			return -1;
		}
	}

	private XMLDescriptor _descriptor;

}