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

package com.liferay.util.xml.descriptor;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Jorge Ferrer
 */
public interface XMLDescriptor {

	public boolean areEqual(Element el1, Element el2);

	public boolean canHandleType(String doctype, Document root);

	public boolean canJoinChildren(Element element);

	public String[] getChildrenOrder(Element parentElement);

	public String[] getRootChildrenOrder();

}