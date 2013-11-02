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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class CollectionNode extends ASTNode {

	public CollectionNode() {
	}

	public CollectionNode(int token) {
		super(token);
	}

	public CollectionNode(List<ASTNode> astNodes) {
		_astNodes = astNodes;
	}

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	public void add(ASTNode astNode) {
		_astNodes.add(astNode);
	}

	public ASTNode get(int position) {
		return _astNodes.get(position);
	}

	public List<ASTNode> getASTNodes() {
		return _astNodes;
	}

	public int size() {
		return _astNodes.size();
	}

	private List<ASTNode> _astNodes = new ArrayList<ASTNode>();

}