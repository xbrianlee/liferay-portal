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

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface Node extends Serializable {

	public <T, V extends Visitor<T>> T accept(V visitor);

	public String asXML();

	public Node asXPathResult(Element parent);

	public String compactString() throws IOException;

	public Node detach();

	public String formattedString() throws IOException;

	public String formattedString(String indent) throws IOException;

	public String formattedString(String indent, boolean expandEmptyElements)
		throws IOException;

	public String formattedString(
			String indent, boolean expandEmptyElements, boolean trimText)
		throws IOException;

	public Document getDocument();

	public String getName();

	public Element getParent();

	public String getPath();

	public String getPath(Element context);

	public String getStringValue();

	public String getText();

	public String getUniquePath();

	public String getUniquePath(Element context);

	public boolean hasContent();

	public boolean isReadOnly();

	public boolean matches(String xPathExpression);

	public Number numberValueOf(String xPathExpression);

	public List<Node> selectNodes(String xPathExpression);

	public List<Node> selectNodes(
		String xPathExpression, String comparisonXPathExpression);

	public List<Node> selectNodes(
		String xPathExpression, String comparisonXPathExpression,
		boolean removeDuplicates);

	public Object selectObject(String xPathExpression);

	public Node selectSingleNode(String xPathExpression);

	public void setName(String name);

	public void setText(String text);

	public boolean supportsParent();

	public String valueOf(String xPathExpression);

	public void write(Writer writer) throws IOException;

}