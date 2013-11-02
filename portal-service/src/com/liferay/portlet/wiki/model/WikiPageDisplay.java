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

package com.liferay.portlet.wiki.model;

import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.Serializable;

import java.util.List;

/**
 * @author Jorge Ferrer
 */
public interface WikiPageDisplay extends Serializable {

	public List<FileEntry> getAttachmentsFileEntries();

	public String getContent();

	public String getFormat();

	public String getFormattedContent();

	public boolean getHead();

	public long getNodeId();

	public String getTitle();

	public long getUserId();

	public double getVersion();

	public boolean isHead();

	public void setAttachmentsFileEntries(
		List<FileEntry> attachmentsFileEntries);

	public void setContent(String content);

	public void setFormat(String format);

	public void setFormattedContent(String formattedContent);

	public void setHead(boolean head);

	public void setNodeId(long nodeId);

	public void setTitle(String title);

	public void setUserId(long userId);

	public void setVersion(double version);

}