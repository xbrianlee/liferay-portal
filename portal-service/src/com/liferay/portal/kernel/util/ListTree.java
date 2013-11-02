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

package com.liferay.portal.kernel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class ListTree<T extends Comparable<T>> {

	public ListTree() {
		this(null);
	}

	public ListTree(T value) {
		_rootNode = new TreeNode<T>(value);
	}

	public List<TreeNode<T>> getChildNodes(TreeNode<T> node) {
		List<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();

		getChildNodes(node, nodes);

		return nodes;
	}

	public TreeNode<T> getRootNode() {
		return _rootNode;
	}

	protected void getChildNodes(TreeNode<T> node, List<TreeNode<T>> nodes) {
		List<TreeNode<T>> childNodes = node.getChildNodes();

		nodes.addAll(childNodes);

		for (TreeNode<T> childNode : childNodes) {
			getChildNodes(childNode, nodes);
		}
	}

	private final TreeNode<T> _rootNode;

}