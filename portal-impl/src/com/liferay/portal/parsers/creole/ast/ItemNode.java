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
public abstract class ItemNode extends BaseParentableNode {

	public ItemNode(int tokenType) {
		super(tokenType);
	}

	public ItemNode(
		int level, BaseParentableNode baseParentableNode,
		CollectionNode collectionNode) {

		super(collectionNode);

		_level = level;
		_baseParentableNode = baseParentableNode;
	}

	public BaseParentableNode getBaseParentableNode() {
		return _baseParentableNode;
	}

	public int getLevel() {
		return _level;
	}

	public void setBaseParentableNode(BaseParentableNode baseParentableNode) {
		_baseParentableNode = baseParentableNode;
	}

	private BaseParentableNode _baseParentableNode;
	private int _level;

}