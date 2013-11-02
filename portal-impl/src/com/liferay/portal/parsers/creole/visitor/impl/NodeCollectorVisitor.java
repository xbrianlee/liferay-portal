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

package com.liferay.portal.parsers.creole.visitor.impl;

import com.liferay.portal.parsers.creole.ast.ASTNode;
import com.liferay.portal.parsers.creole.ast.WikiPageNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public abstract class NodeCollectorVisitor extends BaseASTVisitor {

	public List<ASTNode> collect(WikiPageNode wikiPageNode) {
		_astNodes = new ArrayList<ASTNode>();

		visit(wikiPageNode);

		List<ASTNode> astNodes = new ArrayList<ASTNode>(_astNodes);

		_astNodes = null;

		return astNodes;
	}

	protected void addNode(ASTNode node) {
		_astNodes.add(node);
	}

	private List<ASTNode> _astNodes;

}