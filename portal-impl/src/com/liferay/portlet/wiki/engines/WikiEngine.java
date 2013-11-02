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

package com.liferay.portlet.wiki.engines;

import com.liferay.portlet.wiki.PageContentException;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Jorge Ferrer
 */
public interface WikiEngine {

	/**
	 * Returns the content of the given page converted to HTML using the view
	 * and edit URLs to build links.
	 *
	 * @param  page the wiki page
	 * @param  viewPageURL the URL to view the page
	 * @param  editPageURL the URL to edit the page
	 * @param  attachmentURLPrefix the URL prefix to use for attachments to the
	 *         page
	 * @return the content of the given page converted to HTML
	 * @throws PageContentException if a page content exception occurred
	 */
	public String convert(
			WikiPage page, PortletURL viewPageURL, PortletURL editPageURL,
			String attachmentURLPrefix)
		throws PageContentException;

	/**
	 * Returns a map of the links included in the given page. The key of each
	 * map entry is the title of the linked page. The value is a Boolean object
	 * that indicates if the linked page exists or not.
	 *
	 * @param  page the page
	 * @return a map of links included in the given page
	 * @throws PageContentException if a page content exception occurred
	 */
	public Map<String, Boolean> getOutgoingLinks(WikiPage page)
		throws PageContentException;

	/**
	 * Set the configuration to support quick links to other wikis. The format
	 * of the configuration is specific to the wiki engine.
	 *
	 * @param interWikiConfiguration the configuration to support quick links to
	 *        other wikis
	 */
	public void setInterWikiConfiguration(String interWikiConfiguration);

	/**
	 * Sets the main wiki configuration as a String. The format of the
	 * configuration is specific to the wiki engine.
	 *
	 * @param mainConfiguration the main configuration of the wiki engine
	 */
	public void setMainConfiguration(String mainConfiguration);

	/**
	 * Returns <code>true</code> if the content of a wiki page for this engine
	 * is valid.
	 *
	 * @param  nodeId the ID of the wiki page node
	 * @param  content the page content
	 * @return <code>true</code> if the content of a wiki page for this engine
	 *         is valid; <code>false</code> otherwise
	 */
	public boolean validate(long nodeId, String content);

}