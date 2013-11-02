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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Brian Wing Shun Chan
 * @author Alan Zimmerman
 * @author Jorge Ferrer
 */
public class XMLMerger {

	public XMLMerger(XMLDescriptor descriptor) {
		_descriptor = descriptor;
	}

	public XMLElementComparator getElementComparator() {
		return new XMLElementComparator(_descriptor);
	}

	public Document merge(Document masterDocument, Document slaveDocument) {
		Document mergedDocument = (Document)masterDocument.clone();

		Element mergedRootElement = mergedDocument.getRootElement();
		Element slaveRootElement = slaveDocument.getRootElement();

		for (Element slaveElement :
				(List<Element>)slaveRootElement.elements()) {

			Element clonedSlaveElement = (Element)slaveElement.clone();

			clonedSlaveElement.detach();

			mergedRootElement.add(clonedSlaveElement);
		}

		organizeXML(mergedDocument);

		return mergedDocument;
	}

	public void organizeXML(Document document) {
		Element rootElement = document.getRootElement();

		_orderChildren(rootElement, _descriptor.getRootChildrenOrder());
		_mergeDuplicateElements(rootElement, getElementComparator());
	}

	private void _addChildren(
		Element firstElement, Collection<Element> childElements) {

		List<Element> elements = firstElement.elements();

		for (Element childElement : childElements) {
			elements.add((Element)childElement.clone());
		}

		_orderChildren(
			firstElement, _descriptor.getChildrenOrder(firstElement));
	}

	private boolean _containsObjectEqualTo(
		Element element, List<Element> elements,
		ElementComparator elementComparator) {

		for (Element curElement : elements) {
			if (elementComparator.compare(element, curElement) == 0) {
				return true;
			}
		}

		return false;
	}

	private Element _findObjectEqualTo(
		Element element, List<Element> elements,
		ElementComparator elementComparator) {

		for (Element curElement : elements) {
			if (elementComparator.compare(element, curElement) == 0) {
				return curElement;
			}
		}

		return element;
	}

	private void _mergeDuplicateElements(
		Element element, ElementComparator elementComparator) {

		List<Element> childElements = element.elements();

		if (childElements.isEmpty()) {
			return;
		}

		List<Element> originalElements = new ArrayList<Element>();
		List<Element> duplicateElements = new ArrayList<Element>();

		for (int i = 0; i < childElements.size(); i++) {
			Element childElement = childElements.get(i);

			if (_containsObjectEqualTo(
					childElement, originalElements, elementComparator)) {

				if (_descriptor.canJoinChildren(childElement)) {
					Element firstElement = _findObjectEqualTo(
						childElement, originalElements, elementComparator);

					_addChildren(firstElement, childElement.elements());
				}

				duplicateElements.add(childElement);
			}
			else {
				originalElements.add(childElement);
			}

			_orderChildren(
				childElement, _descriptor.getChildrenOrder(childElement));
		}

		for (Element duplicateElement : duplicateElements) {
			duplicateElement.detach();
		}

		for (Element childElement : originalElements) {
			_mergeDuplicateElements(childElement, elementComparator);
		}
	}

	private void _orderChildren(
		Element parentElement, String[] orderedChildrenNames) {

		if (orderedChildrenNames == null) {
			return;
		}

		List<Element> elements = new ArrayList<Element>();

		for (int i = 0; i < orderedChildrenNames.length; i++) {
			elements.addAll(parentElement.elements(orderedChildrenNames[i]));
		}

		for (Element element : elements) {
			element.detach();

			parentElement.add(element);
		}
	}

	private XMLDescriptor _descriptor;

}