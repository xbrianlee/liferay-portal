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

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentType;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class DocumentImpl extends BranchImpl implements Document {

	public DocumentImpl(org.dom4j.Document document) {
		super(document);

		_document = document;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitDocument(this);
	}

	@Override
	public Document addComment(String comment) {
		_document.addComment(comment);

		return this;
	}

	@Override
	public Document addDocumentType(
		String name, String publicId, String systemId) {

		_document.addDocType(name, publicId, systemId);

		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentImpl)) {
			return false;
		}

		org.dom4j.Document document = ((DocumentImpl)obj).getWrappedDocument();

		return _document.equals(document);
	}

	@Override
	public DocumentType getDocumentType() {
		return new DocumentTypeImpl(_document.getDocType());
	}

	@Override
	public Element getRootElement() {
		return new ElementImpl(_document.getRootElement());
	}

	public org.dom4j.Document getWrappedDocument() {
		return _document;
	}

	@Override
	public String getXMLEncoding() {
		return _document.getXMLEncoding();
	}

	@Override
	public int hashCode() {
		return _document.hashCode();
	}

	@Override
	public void setRootElement(Element rootElement) {
		ElementImpl rootElementImpl = (ElementImpl)rootElement;

		_document.setRootElement(rootElementImpl.getWrappedElement());
	}

	@Override
	public void setXMLEncoding(String encoding) {
		_document.setXMLEncoding(encoding);
	}

	@Override
	public String toString() {
		return _document.toString();
	}

	private org.dom4j.Document _document;

}