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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * This class ensures that portlet attributes and parameters are private to the
 * portlet.
 * </p>
 *
 * @author Brian Myunghun Kim
 */
public class NamespaceServletRequest extends DynamicServletRequest {

	static Set<String> reservedAttrs = new HashSet<String>();

	static {
		reservedAttrs.add(JavaConstants.JAVAX_PORTLET_CONFIG);
		reservedAttrs.add(JavaConstants.JAVAX_PORTLET_PORTLET);
		reservedAttrs.add(JavaConstants.JAVAX_PORTLET_REQUEST);
		reservedAttrs.add(JavaConstants.JAVAX_PORTLET_RESPONSE);
		reservedAttrs.add(JavaConstants.JAVAX_SERVLET_INCLUDE_CONTEXT_PATH);
		reservedAttrs.add(JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO);
		reservedAttrs.add(JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING);
		reservedAttrs.add(JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI);
		reservedAttrs.add(JavaConstants.JAVAX_SERVLET_INCLUDE_SERVLET_PATH);
		reservedAttrs.add(MimeResponse.MARKUP_HEAD_ELEMENT);
		reservedAttrs.add(PortletRequest.LIFECYCLE_PHASE);
	}

	public NamespaceServletRequest(
		HttpServletRequest request, String attrNamespace,
		String paramNamespace) {

		this(request, attrNamespace, paramNamespace, true);
	}

	public NamespaceServletRequest(
		HttpServletRequest request, String attrNamespace, String paramNamespace,
		boolean inherit) {

		super(request, inherit);

		_attrNamespace = attrNamespace;
		_paramNamespace = paramNamespace;
	}

	@Override
	public Object getAttribute(String name) {
		Object value = super.getAttribute(_attrNamespace + name);

		if (value == null) {
			value = super.getAttribute(name);
		}

		return value;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		List<String> names = new ArrayList<String>();

		Enumeration<String> enu = super.getAttributeNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(_attrNamespace)) {
				names.add(name.substring(_attrNamespace.length()));
			}
			else if (_isReservedParam(name)) {
				names.add(name);
			}
		}

		return Collections.enumeration(names);
	}

	@Override
	public String getParameter(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		String value = super.getParameter(name);

		if (value == null) {
			value = super.getParameter(_paramNamespace + name);
		}

		return value;
	}

	@Override
	public void removeAttribute(String name) {
		if (_isReservedParam(name)) {
			super.removeAttribute(name);
		}
		else {
			super.removeAttribute(_attrNamespace + name);
		}
	}

	@Override
	public void setAttribute(String name, Object value) {
		if (_isReservedParam(name)) {
			super.setAttribute(name, value);
		}
		else {
			super.setAttribute(_attrNamespace + name, value);
		}
	}

	public void setAttribute(
		String name, Object value, boolean privateRequestAttribute) {

		if (!privateRequestAttribute) {
			super.setAttribute(name, value);
		}
		else {
			setAttribute(name, value);
		}
	}

	private boolean _isReservedParam(String name) {
		if (reservedAttrs.contains(name)) {
			return true;
		}

		for (String requestSharedAttribute :
				PropsValues.REQUEST_SHARED_ATTRIBUTES) {

			if (name.startsWith(requestSharedAttribute)) {
				return true;
			}
		}

		return false;
	}

	private String _attrNamespace;
	private String _paramNamespace;

}