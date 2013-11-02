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

package com.liferay.portal.kernel.xml;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface Branch extends Node {

	public void add(Comment comment);

	public void add(Element element);

	public void add(Node node);

	public void add(ProcessingInstruction processingInstruction);

	public Element addElement(QName qName);

	public Element addElement(String name);

	public Element addElement(String qualifiedName, String namespaceURI);

	public void appendContent(Branch branch);

	public void clearContent();

	public List<Node> content();

	public Element elementByID(String elementID);

	public int indexOf(Node node);

	public Node node(int index);

	public int nodeCount();

	public Iterator<Node> nodeIterator();

	public void normalize();

	public ProcessingInstruction processingInstruction(String target);

	public List<ProcessingInstruction> processingInstructions();

	public List<ProcessingInstruction> processingInstructions(String target);

	public boolean remove(Comment comment);

	public boolean remove(Element element);

	public boolean remove(Node node);

	public boolean remove(ProcessingInstruction processingInstruction);

	public boolean removeProcessingInstruction(String target);

	public void setContent(List<Node> content);

	public void setProcessingInstructions(
		List<ProcessingInstruction> processingInstructions);

}