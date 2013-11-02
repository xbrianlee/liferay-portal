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

package com.liferay.portlet.wiki.engines.jspwiki.plugin;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.parser.Heading;
import com.ecyrd.jspwiki.plugin.PluginException;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

/**
 * <p>
 * This is a modification of JSPWiki's core TableOfContents plugin for use
 * within Liferay. This plugin modifies the original behavior by producing
 * ordered lists and making contents collapsable.
 * </p>
 *
 * @author Alexander Chow
 * @author Jorge Ferrer
 */
public class TableOfContents extends com.ecyrd.jspwiki.plugin.TableOfContents {

	@Override
	@SuppressWarnings("rawtypes")
	public String execute(WikiContext context, Map params)
		throws PluginException {

		if (!params.containsKey(PARAM_NUMBERED)) {
			params.put(PARAM_NUMBERED, Boolean.TRUE.toString());
		}

		String result = super.execute(context, params);

		if (_count < 2) {
			return StringPool.BLANK;
		}

		int x = result.indexOf("<div class=\"collapsebox\">\n");

		x = result.indexOf("</h4>", x);

		int y = x + "</h4>".length();

		if ((x != -1) && (y != -1)) {
			StringBundler sb = new StringBundler(15);

			sb.append(result.substring(0, x));
			sb.append(StringPool.NBSP);
			sb.append("<a class=\"toc-trigger\" href=\"javascript:;\">[-]</a>");
			sb.append("</h4>");

			sb.append("<div class=\"toc-index\">\n");
			sb.append(result.substring(y));
			sb.append("</div>\n");

			result = sb.toString();
		}

		return result;
	}

	@Override
	public void headingAdded(WikiContext context, Heading heading) {
		super.headingAdded(context, heading);

		_count++;
	}

	private int _count;

}