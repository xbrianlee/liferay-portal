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

package com.liferay.portlet.wiki.engines.mediawiki;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.sql.Connection;

import org.jamwiki.model.Namespace;
import org.jamwiki.model.Topic;
import org.jamwiki.model.TopicType;

/**
 * @author Jonathan Potter
 */
public class LiferayDataHandler extends DummyDataHandler {

	@Override
	public Namespace lookupNamespace(
		String virtualWiki, String namespaceString) {

		String label = _fileNamespace.getLabel(virtualWiki);

		if (StringUtil.equalsIgnoreCase(label, namespaceString)) {
			return _fileNamespace;
		}
		else {
			return null;
		}
	}

	@Override
	public Namespace lookupNamespaceById(int namespaceId) {
		return Namespace.DEFAULT_NAMESPACES.get(namespaceId);
	}

	@Override
	public Topic lookupTopic(
		String virtualWiki, String topicName, boolean deleteOK,
		Connection conn) {

		Topic topic = new Topic(virtualWiki, topicName);

		topic.setTopicType(TopicType.IMAGE);

		return topic;
	}

	@Override
	public String lookupTopicName(String virtualWiki, String topicName) {
		long nodeId = getNodeId(virtualWiki);

		try {
			WikiPage page = WikiPageLocalServiceUtil.getPage(
				nodeId, topicName, true);

			return page.getTitle();
		}
		catch (Exception e) {
		}

		return null;
	}

	protected long getNodeId(String virtualWiki) {
		String nodeId = virtualWiki.replaceAll("Special:Node:(\\d+)", "$1");

		return GetterUtil.getLong(nodeId);
	}

	private Namespace _fileNamespace = Namespace.DEFAULT_NAMESPACES.get(
		Namespace.FILE_ID);

}