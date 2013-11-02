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

package com.liferay.portal.kernel.configuration;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class Filter {

	public Filter(String selector1) {
		this(new String[] {selector1}, null);
	}

	public Filter(String selector1, Map<String, String> variables) {
		this(new String[] {selector1}, variables);
	}

	public Filter(String selector1, String selector2) {
		this(new String[] {selector1, selector2}, null);
	}

	public Filter(
		String selector1, String selector2, Map<String, String> variables) {

		this(new String[] {selector1, selector2}, variables);
	}

	public Filter(String selector1, String selector2, String selector3) {
		this(new String[] {selector1, selector2, selector3}, null);
	}

	public Filter(
		String selector1, String selector2, String selector3,
		Map<String, String> variables) {

		this(new String[] {selector1, selector2, selector3}, variables);
	}

	public Filter(String[] selectors, Map<String, String> variables) {
		_selectors = selectors;
		_variables = variables;
	}

	public String[] getSelectors() {
		return _selectors;
	}

	public Map<String, String> getVariables() {
		return _variables;
	}

	private String[] _selectors;
	private Map<String, String> _variables;

}