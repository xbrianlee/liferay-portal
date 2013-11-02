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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.cluster.Priority;

/**
 * @author Bruno Farache
 * @author Michael C. Han
 */
public interface SearchEngine {

	public BooleanClauseFactory getBooleanClauseFactory();

	public BooleanQueryFactory getBooleanQueryFactory();

	public Priority getClusteredWritePriority();

	public IndexSearcher getIndexSearcher();

	public IndexWriter getIndexWriter();

	public TermQueryFactory getTermQueryFactory();

	public TermRangeQueryFactory getTermRangeQueryFactory();

	public String getVendor();

	public boolean isClusteredWrite();

	public boolean isLuceneBased();

}