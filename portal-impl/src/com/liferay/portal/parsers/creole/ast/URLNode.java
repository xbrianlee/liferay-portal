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

package com.liferay.portal.parsers.creole.ast;

/**
 * @author Miguel Pastor
 */
public abstract class URLNode extends ASTNode {

	public URLNode() {
	}

	public URLNode(int token) {
		super(token);
	}

	public URLNode(int token, String link) {
		this(token);

		_link = link;
	}

	public URLNode(String link) {
		_link = link;
	}

	public String getLink() {
		return _link;
	}

	public String[] getSupportedProtocols() {
		return _supportedProtocols;
	}

	public boolean isAbsoluteLink() {
		for (String supportedProtocol : getSupportedProtocols()) {
			if (_link.startsWith(supportedProtocol)) {
				return true;
			}
		}

		return false;
	}

	public void setLink(String link) {
		_link = link;
	}

	public void setSupportedProtocols(String[] supportedProtocols) {
		_supportedProtocols = supportedProtocols;
	}

	private static final String[] _SUPPORTED_PROTOCOL_LINK =
		{"http://", "https://", "ftp://", "mailto:"};

	private String _link;
	private String[] _supportedProtocols = _SUPPORTED_PROTOCOL_LINK;

}