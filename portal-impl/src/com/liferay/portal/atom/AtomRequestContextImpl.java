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

package com.liferay.portal.atom;

import com.liferay.portal.kernel.atom.AtomRequestContext;
import com.liferay.portal.kernel.util.GetterUtil;

import org.apache.abdera.protocol.server.RequestContext;

/**
 * @author Igor Spasic
 */
public class AtomRequestContextImpl implements AtomRequestContext {

	public AtomRequestContextImpl(RequestContext requestContext) {
		_requestContext = requestContext;
	}

	@Override
	public Object getContainerAttribute(String name) {
		return _requestContext.getAttribute(
			RequestContext.Scope.CONTAINER, name);
	}

	@Override
	public String getHeader(String name) {
		return _requestContext.getHeader(name);
	}

	@Override
	public int getIntParameter(String name) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getInteger(value);
	}

	@Override
	public int getIntParameter(String name, int defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getInteger(value, defaultValue);
	}

	@Override
	public long getLongParameter(String name) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getLong(value);
	}

	@Override
	public long getLongParameter(String name, long defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getLong(value, defaultValue);
	}

	@Override
	public String getParameter(String name) {
		return _requestContext.getParameter(name);
	}

	@Override
	public String getParameter(String name, String defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getString(value, defaultValue);
	}

	@Override
	public Object getRequestAttribute(String name) {
		return _requestContext.getAttribute(RequestContext.Scope.REQUEST, name);
	}

	@Override
	public String getResolvedUri() {
		return _requestContext.getResolvedUri().toString();
	}

	@Override
	public Object getSessionAttribute(String name) {
		return _requestContext.getAttribute(RequestContext.Scope.SESSION, name);
	}

	@Override
	public String getTargetBasePath() {
		return _requestContext.getTargetBasePath();
	}

	@Override
	public void setContainerAttribute(String name, Object value) {
		_requestContext.setAttribute(
			RequestContext.Scope.CONTAINER, name, value);
	}

	@Override
	public void setRequestAttribute(String name, Object value) {
		_requestContext.setAttribute(RequestContext.Scope.REQUEST, name, value);
	}

	@Override
	public void setSessionAttribute(String name, Object value) {
		_requestContext.setAttribute(RequestContext.Scope.SESSION, name, value);
	}

	private RequestContext _requestContext;

}