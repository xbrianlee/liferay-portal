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

package com.liferay.portal.kernel.atom;

/**
 * @author Igor Spasic
 */
public class AtomEntryContent {

	public AtomEntryContent() {
	}

	public AtomEntryContent(String text) {
		_text = text;
	}

	public AtomEntryContent(String text, Type type) {
		_text = text;
		_type = type;
	}

	public AtomEntryContent(Type type) {
		_type = type;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public String getSrcLink() {
		return _srcLink;
	}

	public String getText() {
		return _text;
	}

	public Type getType() {
		return _type;
	}

	public void setMimeType(String mimeType) {
		_mimeType = mimeType;
	}

	public void setSrcLink(String srcLink) {
		_srcLink = srcLink;
	}

	public void setText(String text) {
		_text = text;
	}

	public void setType(Type type) {
		_type = type;
	}

	public enum Type {

		HTML, MEDIA, TEXT, XHTML, XML

	}

	private String _mimeType;
	private String _srcLink;
	private String _text;
	private Type _type = Type.HTML;

}