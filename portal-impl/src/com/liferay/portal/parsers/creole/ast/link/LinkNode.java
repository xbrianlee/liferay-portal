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

package com.liferay.portal.parsers.creole.ast.link;

import com.liferay.portal.parsers.creole.ast.CollectionNode;
import com.liferay.portal.parsers.creole.ast.URLNode;
import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public class LinkNode extends URLNode {

	public LinkNode() {
	}

	public LinkNode(int token) {
		super(token);
	}

	public LinkNode(int token, String link) {
		super(token, link);
	}

	public LinkNode(String link) {
		super(link);
	}

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	public CollectionNode getAltCollectionNode() {
		return _altCollectionNode;
	}

	public boolean hasAltCollectionNode() {
		if (_altCollectionNode != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setAltCollectionNode(CollectionNode altCollectionNode) {
		_altCollectionNode = altCollectionNode;
	}

	private CollectionNode _altCollectionNode;

}