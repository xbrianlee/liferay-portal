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

package com.liferay.portlet.wiki.engines.jspwiki;

import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.WikiPage;
import com.ecyrd.jspwiki.search.SearchProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Jorge Ferrer
 */
public class LiferaySearchProvider implements SearchProvider {

	@Override
	public Collection<WikiPage> findPages(String query) {
		return Collections.emptyList();
	}

	@Override
	public String getProviderInfo() {
		return LiferaySearchProvider.class.getName();
	}

	@Override
	public void initialize(WikiEngine engine, Properties props) {
	}

	@Override
	public void pageRemoved(WikiPage page) {
	}

	@Override
	public void reindexPage(WikiPage page) {
	}

}