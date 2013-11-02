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

import com.liferay.portal.kernel.xml.CDATA;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class CDATAImpl extends NodeImpl implements CDATA {

	public CDATAImpl(org.dom4j.CDATA cdata) {
		super(cdata);

		_cdata = cdata;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitCDATA(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CDATAImpl)) {
			return false;
		}

		org.dom4j.CDATA cdata = ((CDATAImpl)obj).getWrappedCDATA();

		return _cdata.equals(cdata);
	}

	public org.dom4j.CDATA getWrappedCDATA() {
		return _cdata;
	}

	@Override
	public int hashCode() {
		return _cdata.hashCode();
	}

	@Override
	public String toString() {
		return _cdata.toString();
	}

	private org.dom4j.CDATA _cdata;

}