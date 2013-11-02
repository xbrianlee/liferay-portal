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
public abstract class BaseListNode extends BaseParentableNode {

	public BaseListNode() {
	}

	public BaseListNode(
		BaseParentableNode baseParentableNode, CollectionNode collectionNode) {

		super(collectionNode);

		_baseParentableNode = baseParentableNode;
	}

	public BaseListNode(int token) {
		super(token);
	}

	public BaseParentableNode getBaseParentableNode() {
		return _baseParentableNode;
	}

	public void setParent(BaseParentableNode baseParentableNode) {
		_baseParentableNode = baseParentableNode;
	}

	private BaseParentableNode _baseParentableNode;

}