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

/**
 * @author Brian Wing Shun Chan
 */
public interface Attribute extends Node {

	public Object getData();

	public Namespace getNamespace();

	public String getNamespacePrefix();

	public String getNamespaceURI();

	public QName getQName();

	public String getQualifiedName();

	public String getValue();

	public void setData(Object data);

	public void setNamespace(Namespace namespace);

	public void setValue(String value);

}