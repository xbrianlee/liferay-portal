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

package com.liferay.portal.kernel.bi.rules;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class Query implements Serializable {

	public static Query createCustomQuery(String identifier, String queryName) {
		if (Validator.isNull(identifier)) {
			throw new IllegalArgumentException("Query idenfier is null.");
		}

		if (Validator.isNull(queryName)) {
			throw new IllegalArgumentException("Query string is null.");
		}

		return new Query(identifier, QueryType.CUSTOM, queryName);
	}

	public static Query createStandardQuery() {
		return new Query(null, QueryType.STANDARD, null);
	}

	public void addArgument(Object object) {
		if (_queryType.equals(QueryType.STANDARD)) {
			throw new IllegalStateException(
				"Standard queries cannot accept query arguments");
		}

		_arguments.add(object);
	}

	public void addArguments(List<?> arguments) {
		if (_queryType.equals(QueryType.STANDARD)) {
			throw new IllegalStateException(
				"Standard queries cannot accept query arguments");
		}

		_arguments.addAll(arguments);
	}

	public void addArguments(Object[] arguments) {
		if (_queryType.equals(QueryType.STANDARD)) {
			throw new IllegalStateException(
				"Standard queries cannot accept query arguments");
		}

		if (ArrayUtil.isNotEmpty(arguments)) {
			_arguments.addAll(Arrays.asList(arguments));
		}
	}

	public Object[] getArguments() {
		return _arguments.toArray(new Object[_arguments.size()]);
	}

	public String getIdentifier() {
		return _identifier;
	}

	public String getQueryName() {
		return _queryName;
	}

	public QueryType getQueryType() {
		return _queryType;
	}

	private Query(String identifier, QueryType queryType, String queryName) {
		_identifier = identifier;
		_queryType = queryType;
		_queryName = queryName;
	}

	private List<Object> _arguments = new ArrayList<Object>();
	private String _identifier;
	private String _queryName;
	private QueryType _queryType;

}