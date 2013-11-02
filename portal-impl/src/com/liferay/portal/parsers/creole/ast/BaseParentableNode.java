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

import java.util.List;

/**
 * @author Miguel Pastor
 */
public abstract class BaseParentableNode extends ASTNode {

	public BaseParentableNode() {
	}

	public BaseParentableNode(CollectionNode collectionNode) {
		if (collectionNode != null) {
			_collectionNode = collectionNode;
		}
	}

	public BaseParentableNode(int tokenType) {
		super(tokenType);
	}

	public void addChildASTNode(ASTNode astNode) {
		_collectionNode.add(astNode);
	}

	public ASTNode getChildASTNode(int position) {
		return _collectionNode.get(position);
	}

	public List<ASTNode> getChildASTNodes() {
		return _collectionNode.getASTNodes();
	}

	public int getChildASTNodesCount() {
		return _collectionNode.size();
	}

	private CollectionNode _collectionNode = new CollectionNode();

}