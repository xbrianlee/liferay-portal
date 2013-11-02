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

/**
 * @author Eudaldo Alonso
 */
public abstract class BaseSearcher extends BaseIndexer {

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		for (String className : getClassNames()) {
			Indexer indexer = IndexerRegistryUtil.getIndexer(className);

			if (indexer == null) {
				continue;
			}

			indexer.postProcessSearchQuery(searchQuery, searchContext);
		}
	}

}