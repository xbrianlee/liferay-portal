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

package com.liferay.portlet.wiki.engines.antlrwiki.translator;

import com.liferay.portal.kernel.util.TreeNode;
import com.liferay.portal.parsers.creole.ast.HeadingNode;
import com.liferay.portal.parsers.creole.ast.WikiPageNode;
import com.liferay.portal.parsers.creole.visitor.impl.BaseASTVisitor;

import java.util.List;

/**
 * @author Miguel Pastor
 */
public class TableOfContentsVisitor extends BaseASTVisitor {

	public TreeNode<HeadingNode> compose(WikiPageNode wikiPageNode) {
		_headingNode = new TreeNode<HeadingNode>(
			new HeadingNode(Integer.MIN_VALUE));

		visit(wikiPageNode);

		return _headingNode;
	}

	@Override
	public void visit(HeadingNode headingNode) {
		addHeadingNode(_headingNode, headingNode);
	}

	protected boolean addHeadingNode(
		TreeNode<HeadingNode> treeNode, HeadingNode headingNode) {

		if (!isLastHeadingNode(treeNode, headingNode)) {
			HeadingNode treeNodeHeadingNode = treeNode.getValue();

			if (headingNode.getLevel() <= treeNodeHeadingNode.getLevel()) {
				TreeNode<HeadingNode> parentTreeNode = treeNode.getParentNode();

				parentTreeNode.addChildNode(headingNode);
			}
			else {
				treeNode.addChildNode(headingNode);
			}

			return false;
		}

		List<TreeNode<HeadingNode>> treeNodes = treeNode.getChildNodes();

		for (int i = treeNodes.size() - 1; i >= 0; --i) {
			return addHeadingNode(treeNodes.get(i), headingNode);
		}

		return true;
	}

	protected boolean isLastHeadingNode(
		TreeNode<HeadingNode> treeNode, HeadingNode headingNode) {

		HeadingNode treeNodeHeadingNode = treeNode.getValue();

		List<TreeNode<HeadingNode>> treeNodes = treeNode.getChildNodes();

		if ((headingNode.getLevel() > treeNodeHeadingNode.getLevel()) &&
			(treeNodes != null) && (treeNodes.size() > 0)) {

			return true;
		}

		return false;
	}

	private TreeNode<HeadingNode> _headingNode;

}