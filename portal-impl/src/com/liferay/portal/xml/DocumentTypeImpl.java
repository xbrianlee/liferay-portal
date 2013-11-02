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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.DocumentType;

/**
 * @author Brian Wing Shun Chan
 */
public class DocumentTypeImpl implements DocumentType {

	public DocumentTypeImpl(org.dom4j.DocumentType documentType) {
		_documentType = documentType;
	}

	@Override
	public String getName() {
		return _documentType.getName();
	}

	@Override
	public String getPublicId() {
		if (_documentType == null) {
			return null;
		}

		return _documentType.getPublicID();
	}

	@Override
	public String getSystemId() {
		if (_documentType == null) {
			return null;
		}

		return _documentType.getSystemID();
	}

	public org.dom4j.DocumentType getWrappedDocumentType() {
		return _documentType;
	}

	@Override
	public int hashCode() {
		if (_documentType == null) {
			return super.hashCode();
		}

		return _documentType.hashCode();
	}

	@Override
	public String toString() {
		if (_documentType == null) {
			return StringPool.BLANK;
		}

		return _documentType.toString();
	}

	private org.dom4j.DocumentType _documentType;

}