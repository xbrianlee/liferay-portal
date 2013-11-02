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

package com.liferay.portal.kernel.repository.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;

import java.util.Date;

/**
 * @author Alexander Chow
 */
public interface FileVersion extends RepositoryModel<FileVersion> {

	public String getChangeLog();

	@Override
	public long getCompanyId();

	public InputStream getContentStream(boolean incrementCounter)
		throws PortalException, SystemException;

	@Override
	public Date getCreateDate();

	public String getDescription();

	@Override
	public ExpandoBridge getExpandoBridge();

	public String getExtension();

	public String getExtraSettings();

	public FileEntry getFileEntry() throws PortalException, SystemException;

	public long getFileEntryId();

	public long getFileVersionId();

	@Override
	public long getGroupId();

	public String getIcon();

	public String getMimeType();

	public long getRepositoryId();

	public long getSize();

	public int getStatus();

	public long getStatusByUserId();

	public String getStatusByUserName();

	public String getStatusByUserUuid() throws SystemException;

	public Date getStatusDate();

	public String getTitle();

	@Override
	public long getUserId();

	@Override
	public String getUserName();

	@Override
	public String getUserUuid() throws SystemException;

	@Override
	public String getUuid();

	public String getVersion();

	public boolean isApproved();

	public boolean isDefaultRepository();

	public boolean isDraft();

	public boolean isExpired();

	public boolean isPending();

}