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

package com.liferay.portal.search.generic;

import com.liferay.portal.kernel.search.QueryTerm;

/**
 * @author Michael C. Han
 */
public class QueryTermImpl implements QueryTerm {

	public QueryTermImpl(String field, String value) {
		_field = field;
		_value = value;
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public String getValue() {
		return _value;
	}

	private String _field;
	private String _value;

}