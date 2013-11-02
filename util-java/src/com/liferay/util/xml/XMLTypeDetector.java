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

import com.liferay.util.xml.descriptor.PortletAppDescriptor;
import com.liferay.util.xml.descriptor.StrictXMLDescriptor;
import com.liferay.util.xml.descriptor.StrutsConfigDescriptor;
import com.liferay.util.xml.descriptor.TilesDefsDescriptor;
import com.liferay.util.xml.descriptor.WebXML23Descriptor;
import com.liferay.util.xml.descriptor.WebXML24Descriptor;
import com.liferay.util.xml.descriptor.XMLDescriptor;

import org.dom4j.Document;

/**
 * @author Jorge Ferrer
 */
public class XMLTypeDetector {

	public static final XMLDescriptor[] REGISTERED_DESCRIPTORS = {
		new PortletAppDescriptor(), new StrutsConfigDescriptor(),
		new TilesDefsDescriptor(), new WebXML23Descriptor(),
		new WebXML24Descriptor()
	};

	public static XMLDescriptor determineType(String doctype, Document root) {
		for (int i = 0; i < REGISTERED_DESCRIPTORS.length; i++) {
			XMLDescriptor descriptor = REGISTERED_DESCRIPTORS[i];

			if (descriptor.canHandleType(doctype, root)) {
				return descriptor;
			}
		}

		return new StrictXMLDescriptor();
	}

}