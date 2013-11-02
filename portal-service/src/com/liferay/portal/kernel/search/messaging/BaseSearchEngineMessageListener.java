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

package com.liferay.portal.kernel.search.messaging;

import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.search.SearchEngine;

/**
 * @author Michael C. Han
 */
public abstract class BaseSearchEngineMessageListener
	extends ProxyMessageListener {

	public void setSearchEngine(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}

	public void setSearchEngineId(String searchEngineId) {
		this.searchEngineId = searchEngineId;
	}

	protected SearchEngine searchEngine;
	protected String searchEngineId;

}