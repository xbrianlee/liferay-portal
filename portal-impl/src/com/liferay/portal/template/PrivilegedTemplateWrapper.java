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

package com.liferay.portal.template;

import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateException;

import java.io.Writer;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tina Tian
 * @author Shuyang Zhou
 */
public class PrivilegedTemplateWrapper implements Template {

	public PrivilegedTemplateWrapper(
		AccessControlContext accessControlContext, Template template) {

		_accessControlContext = accessControlContext;
		_template = template;
	}

	@Override
	public Object get(String key) {
		return _template.get(key);
	}

	@Override
	public String[] getKeys() {
		return _template.getKeys();
	}

	@Override
	public void prepare(HttpServletRequest request) {
		_template.prepare(request);
	}

	@Override
	public void processTemplate(Writer writer) throws TemplateException {
		try {
			AccessController.doPrivileged(
				new ProcessTemplatePrivilegedExceptionAction(_template, writer),
				_accessControlContext);
		}
		catch (PrivilegedActionException pae) {
			throw (TemplateException)pae.getException();
		}
	}

	@Override
	public void put(String key, Object value) {
		_template.put(key, value);
	}

	private AccessControlContext _accessControlContext;
	private Template _template;

	private static class ProcessTemplatePrivilegedExceptionAction
		implements PrivilegedExceptionAction<Void> {

		public ProcessTemplatePrivilegedExceptionAction(
			Template template, Writer writer) {

			_template = template;
			_writer = writer;
		}

		@Override
		public Void run() throws Exception {
			_template.processTemplate(_writer);

			return null;
		}

		private Template _template;
		private Writer _writer;

	}

}