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

package com.liferay.portal.kernel.xml;

import java.io.Serializable;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface XPath extends Serializable {

	public boolean booleanValueOf(Object context);

	public Object evaluate(Object context);

	public String getText();

	public boolean matches(Node node);

	public Number numberValueOf(Object context);

	public List<Node> selectNodes(Object context);

	public List<Node> selectNodes(Object context, XPath sortXPath);

	public List<Node> selectNodes(
		Object context, XPath sortXPath, boolean distinct);

	public Node selectSingleNode(Object context);

	public void sort(List<Node> nodes);

	public void sort(List<Node> nodes, boolean distinct);

	public String valueOf(Object context);

}