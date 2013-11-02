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

import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactory;

/**
 * @author Michael C. Han
 */
public class TermQueryFactoryImpl implements TermQueryFactory {

	@Override
	public TermQuery create(String field, long value) {
		return create(field, String.valueOf(value));
	}

	@Override
	public TermQuery create(String field, String value) {
		return new TermQueryImpl(new QueryTermImpl(field, value));
	}

}