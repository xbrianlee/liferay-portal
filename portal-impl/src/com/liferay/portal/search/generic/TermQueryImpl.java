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

import com.liferay.portal.kernel.search.BaseQueryImpl;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;

/**
 * @author Michael C. Han
 */
public class TermQueryImpl extends BaseQueryImpl implements TermQuery {

	public TermQueryImpl(QueryTerm queryTerm) {
		_queryTerm = queryTerm;
	}

	@Override
	public QueryTerm getQueryTerm() {
		return _queryTerm;
	}

	@Override
	public Object getWrappedQuery() {
		return this;
	}

	private QueryTerm _queryTerm;

}