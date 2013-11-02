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

package com.liferay.portlet.wiki.engines.jspwiki.filters;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.filters.FilterException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Liu
 */
public class CreoleFilter extends com.ecyrd.jspwiki.filters.CreoleFilter {

	@Override
	public void destroy(WikiEngine wikiEngine) {
		super.destroy(wikiEngine);
	}

	@Override
	public void initialize(WikiEngine wikiEngine, Properties properties) {
		if (_log.isDebugEnabled()) {
			_log.debug("Initializing");
		}
	}

	@Override
	public void postSave(WikiContext wikiContext, String content)
		throws FilterException {

		super.postSave(wikiContext, content);
	}

	@Override
	public String postTranslate(WikiContext wikiContext, String htmlContent)
		throws FilterException {

		return super.postTranslate(wikiContext, htmlContent);
	}

	@Override
	public String preSave(WikiContext wikiContext, String content)
		throws FilterException {

		return super.preSave(wikiContext, content);
	}

	@Override
	public String preTranslate(WikiContext wikiContext, String content)
		throws FilterException {

		return super.preTranslate(wikiContext, content);
	}

	private static Log _log = LogFactoryUtil.getLog(CreoleFilter.class);

}