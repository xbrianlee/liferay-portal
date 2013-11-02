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

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tina Tian
 */
public class RestrictedTemplate implements Template {

	public RestrictedTemplate(
		Template template, Set<String> restrictedVariables) {

		_template = template;
		_restrictedVariables = restrictedVariables;
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
		_template.processTemplate(writer);
	}

	@Override
	public void put(String key, Object value) {
		if (_restrictedVariables.contains(key)) {
			return;
		}

		_template.put(key, value);
	}

	private Set<String> _restrictedVariables;
	private Template _template;

}