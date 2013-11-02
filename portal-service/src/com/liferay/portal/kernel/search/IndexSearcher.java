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

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

/**
 * @author Bruno Farache
 * @author Raymond Augé
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface IndexSearcher extends QuerySuggester {

	public Hits search(SearchContext searchContext, Query query)
		throws SearchException;

	public Hits search(
			String searchEngineId, long companyId, Query query, Sort[] sort,
			int start, int end)
		throws SearchException;

}