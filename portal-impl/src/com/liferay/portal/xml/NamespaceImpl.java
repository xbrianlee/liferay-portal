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

import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class NamespaceImpl extends NodeImpl implements Namespace {

	public NamespaceImpl(org.dom4j.Namespace namespace) {
		super(namespace);

		_namespace = namespace;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitNamespace(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NamespaceImpl)) {
			return false;
		}

		org.dom4j.Namespace namespace =
			((NamespaceImpl)obj).getWrappedNamespace();

		return _namespace.equals(namespace);
	}

	@Override
	public short getNodeType() {
		return _namespace.getNodeType();
	}

	@Override
	public String getPrefix() {
		return _namespace.getPrefix();
	}

	@Override
	public String getURI() {
		return _namespace.getURI();
	}

	public org.dom4j.Namespace getWrappedNamespace() {
		return _namespace;
	}

	@Override
	public String getXPathNameStep() {
		return _namespace.getXPathNameStep();
	}

	@Override
	public int hashCode() {
		return _namespace.hashCode();
	}

	@Override
	public String toString() {
		return _namespace.toString();
	}

	private org.dom4j.Namespace _namespace;

}