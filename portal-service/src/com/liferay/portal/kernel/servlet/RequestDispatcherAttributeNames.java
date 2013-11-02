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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.JavaConstants;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.MimeResponse;

/**
 * @author Shuyang Zhou
 */
public class RequestDispatcherAttributeNames {

	public static boolean contains(String name) {
		return _attributeNames.contains(name);
	}

	private static final Set<String> _attributeNames = new HashSet<String>();

	static {
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_CONTEXT_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_PATH_INFO);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_QUERY_STRING);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_REQUEST_URI);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_FORWARD_SERVLET_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_CONTEXT_PATH);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI);
		_attributeNames.add(JavaConstants.JAVAX_SERVLET_INCLUDE_SERVLET_PATH);
		_attributeNames.add(MimeResponse.MARKUP_HEAD_ELEMENT);
	}

}