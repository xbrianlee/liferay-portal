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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.search.BaseQueryImpl;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;

import org.apache.lucene.index.Term;

/**
 * @author Brian Wing Shun Chan
 */
public class TermQueryImpl extends BaseQueryImpl implements TermQuery {

	public TermQueryImpl(String field, long value) {
		this(field, String.valueOf(value));
	}

	public TermQueryImpl(String field, String value) {
		_termQuery = new org.apache.lucene.search.TermQuery(
			new Term(field, value));
	}

	@Override
	public QueryTerm getQueryTerm() {
		throw new UnsupportedOperationException();
	}

	public org.apache.lucene.search.TermQuery getTermQuery() {
		return _termQuery;
	}

	@Override
	public Object getWrappedQuery() {
		return getTermQuery();
	}

	@Override
	public String toString() {
		return _termQuery.toString();
	}

	private org.apache.lucene.search.TermQuery _termQuery;

}