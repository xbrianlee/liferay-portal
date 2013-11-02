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

import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public class ImageNode extends URLNode {

	public ImageNode() {
	}

	public ImageNode(CollectionNode altCollectionNode, String uri) {
		super(uri);

		_altCollectionNode = altCollectionNode;
	}

	public ImageNode(int token) {
		super(token);
	}

	public ImageNode(
		int tokenType, CollectionNode altCollectionNode, String uri) {

		super(tokenType, uri);

		_altCollectionNode = altCollectionNode;
	}

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	public CollectionNode getAltNode() {
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