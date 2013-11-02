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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.ldap.DummyDirContext;
import com.liferay.portal.kernel.util.Validator;

import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

/**
 * @author Edward Han
 */
public class PortalLDAPContext extends DummyDirContext {

	public PortalLDAPContext(Attributes attributes) {
		_attributes = attributes;
	}

	public PortalLDAPContext(boolean ignoreCase) {
		_attributes = new BasicAttributes(ignoreCase);
	}

	public void addAttribute(String name, Object object) {
		_attributes.put(name, object);
	}

	public Attributes getAttributes() {
		return _attributes;
	}

	@Override
	public Attributes getAttributes(Name name) throws NamingException {
		return getAttributes(name.toString());
	}

	@Override
	public Attributes getAttributes(Name name, String[] ids)
		throws NamingException {

		return getAttributes(name.toString(), ids);
	}

	@Override
	public Attributes getAttributes(String name) throws NamingException {
		if (Validator.isNotNull(name)) {
			throw new NameNotFoundException();
		}

		return (Attributes)_attributes.clone();
	}

	@Override
	public Attributes getAttributes(String name, String[] ids)
		throws NamingException {

		if (Validator.isNotNull(name)) {
			throw new NameNotFoundException();
		}

		Attributes attributes = new BasicAttributes(true);

		for (int i = 0; i < ids.length; i++) {
			Attribute attribute = _attributes.get(ids[i]);

			if (attribute != null) {
				attributes.put(attribute);
			}
		}

		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		_attributes = attributes;
	}

	private Attributes _attributes;

}