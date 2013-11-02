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

import com.liferay.util.xml.AttributeComparator;
import com.liferay.util.xml.ElementComparator;

import java.util.Comparator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Jorge Ferrer
 */
public class StrictXMLDescriptor implements XMLDescriptor {

	@Override
	public boolean areEqual(Element el1, Element el2) {
		if (_compare(el1, el2) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean canHandleType(String doctype, Document root) {
		return false;
	}

	@Override
	public boolean canJoinChildren(Element element) {
		return false;
	}

	@Override
	public String[] getChildrenOrder(Element parentElement) {
		return new String[0];
	}

	@Override
	public String[] getRootChildrenOrder() {
		return _ROOT_ORDERED_CHILDREN;
	}

	private int _compare(Object obj1, Object obj2) {
		Element el1 = (Element)obj1;
		Element el2 = (Element)obj2;

		String el1Name = el1.getName();
		String el2Name = el2.getName();

		if (!el1Name.equals(el2Name)) {
			return el1Name.compareTo(el2Name);
		}

		String el1Text = el1.getTextTrim();
		String el2Text = el2.getTextTrim();

		if (!el1Text.equals(el2Text)) {
			return el1Text.compareTo(el2Text);
		}

		int attributeComparison = _compareAttributes(el1, el2);

		if (attributeComparison != 0) {
			return attributeComparison;
		}

		int childrenComparison = _compareChildren(el1, el2);

		if (childrenComparison != 0) {
			return childrenComparison;
		}

		return 0;
	}

	private int _compareAttributes(Element el1, Element el2) {
		List<Attribute> el1Attrs = el1.attributes();
		List<Attribute> el2Attrs = el2.attributes();

		if (el1Attrs.size() < el2Attrs.size()) {
			return -1;
		}
		else if (el1Attrs.size() > el2Attrs.size()) {
			return 1;
		}

		for (Attribute attr : el1Attrs) {
			int value = _contains(el2Attrs, attr, new AttributeComparator());

			if (value != 0) {
				return value;
			}
		}

		return -1;
	}

	private int _compareChildren(Element el1, Element el2) {
		List<Element> el1Children = el1.elements();
		List<Element> el2Children = el2.elements();

		if (el1Children.size() < el2Children.size()) {
			return -1;
		}
		else if (el1Children.size() > el2Children.size()) {
			return 1;
		}

		for (Element el : el1Children) {
			int value = _contains(el2Children, el, new ElementComparator());

			if (value != 0) {
				return value;
			}
		}

		return -1;
	}

	private int _contains(
		List<Attribute> list, Attribute obj, Comparator<Attribute> comparator) {

		int firstValue = -1;

		for (int i = 0; i < list.size(); i++) {
			Attribute o = list.get(i);

			int value = comparator.compare(obj, o);

			if (i == 0) {
				firstValue = value;
			}

			if (value == 0) {
				return 0;
			}
		}

		return firstValue;
	}

	private int _contains(
		List<Element> list, Element obj, Comparator<Element> comparator) {

		int firstValue = -1;

		for (int i = 0; i < list.size(); i++) {
			Element o = list.get(i);

			int value = comparator.compare(obj, o);

			if (i == 0) {
				firstValue = value;
			}

			if (value == 0) {
				return 0;
			}
		}

		return firstValue;
	}

	private static final String[] _ROOT_ORDERED_CHILDREN = {
	};

}