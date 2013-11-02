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

import com.liferay.portal.kernel.xml.Text;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class TextImpl extends NodeImpl implements Text {

	public TextImpl(org.dom4j.Text text) {
		super(text);

		_text = text;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitText(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TextImpl)) {
			return false;
		}

		org.dom4j.Text text = ((TextImpl)obj).getWrappedText();

		return _text.equals(text);
	}

	public org.dom4j.Text getWrappedText() {
		return _text;
	}

	@Override
	public int hashCode() {
		return _text.hashCode();
	}

	@Override
	public String toString() {
		return _text.toString();
	}

	private org.dom4j.Text _text;

}