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

import com.ecyrd.jspwiki.QueryItem;
import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.WikiPage;
import com.ecyrd.jspwiki.attachment.Attachment;
import com.ecyrd.jspwiki.providers.ProviderException;
import com.ecyrd.jspwiki.providers.WikiAttachmentProvider;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Jorge Ferrer
 */
public class LiferayAttachmentProvider implements WikiAttachmentProvider {

	@Override
	public void deleteAttachment(Attachment attachment) {
	}

	@Override
	public void deleteVersion(Attachment attachment) {
	}

	@Override
	public Collection<Attachment> findAttachments(QueryItem[] query) {
		return Collections.emptyList();
	}

	@Override
	public InputStream getAttachmentData(Attachment attachment) {
		return _EMPTY_STREAM;
	}

	@Override
	public Attachment getAttachmentInfo(WikiPage page, String name, int version)
		throws ProviderException {

		com.liferay.portlet.wiki.model.WikiPage wikiPage = null;

		try {
			wikiPage = WikiPageLocalServiceUtil.getPage(
				_nodeId, page.getName());

			for (FileEntry fileEntry : wikiPage.getAttachmentsFileEntries()) {
				String title = fileEntry.getTitle();

				if (title.equals(name)) {
					return new Attachment(_engine, page.getName(), name);
				}
			}

			return null;
		}
		catch (Exception e) {
			throw new ProviderException(e.toString());
		}
	}

	@Override
	public String getProviderInfo() {
		return LiferayAttachmentProvider.class.getName();
	}

	@Override
	public List<Attachment> getVersionHistory(Attachment attachment) {
		List<Attachment> history = new ArrayList<Attachment>();

		history.add(attachment);

		return history;
	}

	@Override
	public void initialize(WikiEngine engine, Properties props) {
		_engine = engine;
		_nodeId = GetterUtil.getLong(props.getProperty("nodeId"));
	}

	@Override
	public List<Attachment> listAllChanged(Date timestamp) {
		return Collections.emptyList();
	}

	@Override
	public Collection<Attachment> listAttachments(WikiPage page) {
		return Collections.emptyList();
	}

	@Override
	public void moveAttachmentsForPage(String oldParent, String newParent) {
	}

	@Override
	public void putAttachmentData(Attachment attachment, InputStream data) {
	}

	private static final InputStream _EMPTY_STREAM =
		new UnsyncByteArrayInputStream(new byte[0]);

	private WikiEngine _engine;
	private long _nodeId;

}