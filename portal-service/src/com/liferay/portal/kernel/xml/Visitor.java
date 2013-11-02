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

/**
 * @author Marcellus Tavares
 */
public interface Visitor<T> {

	public T visitAttribute(Attribute attribute);

	public T visitCDATA(CDATA cdata);

	public T visitComment(Comment comment);

	public T visitDocument(Document document);

	public T visitElement(Element element);

	public T visitEntity(Entity entity);

	public T visitNamespace(Namespace namespace);

	public T visitNode(Node node);

	public T visitProcessInstruction(
		ProcessingInstruction processingInstruction);

	public T visitText(Text text);

}