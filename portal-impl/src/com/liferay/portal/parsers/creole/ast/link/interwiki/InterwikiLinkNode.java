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

package com.liferay.portal.parsers.creole.ast.link.interwiki;

import com.liferay.portal.parsers.creole.ast.link.LinkNode;
import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public abstract class InterwikiLinkNode extends LinkNode {

	public InterwikiLinkNode() {
	}

	public InterwikiLinkNode(int token) {
		super(token);
	}

	public InterwikiLinkNode(int token, String title) {
		this(token);

		_title = title;
	}

	public InterwikiLinkNode(String title) {
		_title = title;
	}

	@Override
	public abstract void accept(ASTVisitor astVisitor);

	public String getTitle() {
		return _title;
	}

	public String getURL() {
		return getBaseURL() + _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	protected abstract String getBaseURL();

	private String _title;

}