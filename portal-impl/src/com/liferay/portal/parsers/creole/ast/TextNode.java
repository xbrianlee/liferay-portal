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
public abstract class TextNode extends BaseParentableNode {

	public TextNode(ASTNode astNode) {
		super((CollectionNode)astNode);
	}

	public TextNode(int tokenType) {
		super(tokenType);
	}

	public TextNode(String content) {
		_content = content;
	}

	public String getContent() {
		return _content;
	}

	public boolean hasContent() {
		if (_content != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private String _content;

}