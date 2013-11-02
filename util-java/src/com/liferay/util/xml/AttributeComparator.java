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

import java.util.Comparator;

import org.dom4j.Attribute;

/**
 * @author Brian Wing Shun Chan
 */
public class AttributeComparator implements Comparator<Attribute> {

	@Override
	public int compare(Attribute attr1, Attribute attr2) {
		String attr1Value = attr1.getValue();
		String attr2Value = attr2.getValue();

		return attr1Value.compareTo(attr2Value);
	}

}