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

import com.liferay.portal.kernel.xml.XMLSchema;
import com.liferay.portal.util.EntityResolver;

import org.xml.sax.InputSource;

/**
 * @author Marcellus Tavares
 */
public class XMLSchemaImpl implements XMLSchema {

	@Override
	public String getPublicId() {
		return _publicId;
	}

	@Override
	public String getSchemaLanguage() {
		return _schemaLanguage;
	}

	@Override
	public InputSource getSchemaSource() {
		EntityResolver entityResolver = new EntityResolver();

		return entityResolver.resolveEntity(_publicId, _systemId);
	}

	@Override
	public String getSystemId() {
		return _systemId;
	}

	public void setPublicId(String publicId) {
		_publicId = publicId;
	}

	public void setSchemaLanguage(String schemaLanguage) {
		_schemaLanguage = schemaLanguage;
	}

	public void setSystemId(String systemId) {
		_systemId = systemId;
	}

	private String _publicId;
	private String _schemaLanguage;
	private String _systemId;

}