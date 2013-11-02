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

package com.liferay.portlet.messageboards.model;

import java.io.Serializable;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface MBTreeWalker extends Serializable {

	public List<MBMessage> getChildren(MBMessage message);

	public int[] getChildrenRange(MBMessage message);

	public List<MBMessage> getMessages();

	public MBMessage getRoot();

	public boolean isLeaf(MBMessage message);

	public boolean isOdd();

}