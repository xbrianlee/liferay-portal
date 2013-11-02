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
import com.liferay.portal.kernel.search.Query;

/**
 * @author Bruno Farache
 */
public class LuceneQueryImpl extends BaseQueryImpl implements Query {

	public LuceneQueryImpl(org.apache.lucene.search.Query query) {
		_query = query;
	}

	public org.apache.lucene.search.Query getQuery() {
		return _query;
	}

	@Override
	public Object getWrappedQuery() {
		return _query;
	}

	@Override
	public String toString() {
		return _query.toString();
	}

	private org.apache.lucene.search.Query _query;

}