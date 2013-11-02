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

package com.liferay.portlet.asset;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Roberto Díaz
 */
public class DuplicateQueryRuleException extends PortalException {

	public DuplicateQueryRuleException(
		boolean contains, boolean andOperator, String name) {

		super();

		_contains = contains;
		_andOperator = andOperator;
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public boolean isAndOperator() {
		return _andOperator;
	}

	public boolean isContains() {
		return _contains;
	}

	private boolean _andOperator;
	private boolean _contains;
	private String _name;

}