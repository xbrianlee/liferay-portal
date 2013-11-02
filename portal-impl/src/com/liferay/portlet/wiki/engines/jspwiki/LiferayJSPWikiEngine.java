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

import com.ecyrd.jspwiki.WikiException;

import java.util.Collection;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Jorge Ferrer
 */
public class LiferayJSPWikiEngine extends com.ecyrd.jspwiki.WikiEngine {

	public LiferayJSPWikiEngine(Properties properties) throws WikiException {
		super(properties);
	}

	public LiferayJSPWikiEngine(
			ServletContext context, String appId, Properties props)
		throws WikiException {

		super(context, appId, props);
	}

	@Override
	public Collection<String> scanWikiLinks(
		com.ecyrd.jspwiki.WikiPage page, String pageData) {

		return super.scanWikiLinks(page, pageData);
	}

}