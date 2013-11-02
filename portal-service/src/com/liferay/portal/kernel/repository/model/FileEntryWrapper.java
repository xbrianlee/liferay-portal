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
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.ModelWrapper;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Stiemann
 */
public class FileEntryWrapper implements FileEntry, ModelWrapper<FileEntry> {

	public FileEntryWrapper(FileEntry fileEntry) {
		_fileEntry = fileEntry;
	}

	@Override
	public Object clone() {
		return new FileEntryWrapper((FileEntry)_fileEntry.clone());
	}

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return _fileEntry.containsPermission(permissionChecker, actionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileEntryWrapper)) {
			return false;
		}

		FileEntryWrapper fileEntryWrapper = (FileEntryWrapper)obj;

		if (Validator.equals(_fileEntry, fileEntryWrapper._fileEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return _fileEntry.getAttributes();
	}

	@Override
	public long getCompanyId() {
		return _fileEntry.getCompanyId();
	}

	@Override
	public InputStream getContentStream()
		throws PortalException, SystemException {

		return _fileEntry.getContentStream();
	}

	@Override
	public InputStream getContentStream(String version)
		throws PortalException, SystemException {

		return _fileEntry.getContentStream();
	}

	@Override
	public Date getCreateDate() {
		return _fileEntry.getCreateDate();
	}

	@Override
	public String getDescription() {
		return _fileEntry.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileEntry.getExpandoBridge();
	}

	@Override
	public String getExtension() {
		return _fileEntry.getExtension();
	}

	@Override
	public long getFileEntryId() {
		return _fileEntry.getFileEntryId();
	}

	@Override
	public FileVersion getFileVersion()
		throws PortalException, SystemException {

		return _fileEntry.getFileVersion();
	}

	@Override
	public FileVersion getFileVersion(String version)
		throws PortalException, SystemException {

		return _fileEntry.getFileVersion();
	}

	@Override
	public List<FileVersion> getFileVersions(int status)
		throws SystemException {

		return _fileEntry.getFileVersions(status);
	}

	@Override
	public Folder getFolder() {
		return _fileEntry.getFolder();
	}

	@Override
	public long getFolderId() {
		return _fileEntry.getFolderId();
	}

	@Override
	public long getGroupId() {
		return _fileEntry.getGroupId();
	}

	@Override
	public String getIcon() {
		return _fileEntry.getIcon();
	}

	@Override
	public FileVersion getLatestFileVersion()
		throws PortalException, SystemException {

		return _fileEntry.getLatestFileVersion();
	}

	@Override
	public Lock getLock() {
		return _fileEntry.getLock();
	}

	@Override
	public String getMimeType() {
		return _fileEntry.getMimeType();
	}

	@Override
	public String getMimeType(String version) {
		return _fileEntry.getMimeType(version);
	}

	@Override
	public Object getModel() {
		return _fileEntry.getModel();
	}

	@Override
	public Class<?> getModelClass() {
		return FileEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FileEntry.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _fileEntry.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntry.getPrimaryKeyObj();
	}

	@Override
	public int getReadCount() {
		return _fileEntry.getReadCount();
	}

	@Override
	public long getRepositoryId() {
		return _fileEntry.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _fileEntry.getSize();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _fileEntry.getStagedModelType();
	}

	@Override
	public String getTitle() {
		return _fileEntry.getTitle();
	}

	@Override
	public long getUserId() {
		return _fileEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _fileEntry.getUserName();
	}

	@Override
	public String getUserUuid() throws SystemException {
		return _fileEntry.getUserUuid();
	}

	@Override
	public String getUuid() {
		return _fileEntry.getUuid();
	}

	@Override
	public String getVersion() {
		return _fileEntry.getVersion();
	}

	@Override
	public long getVersionUserId() {
		return _fileEntry.getVersionUserId();
	}

	@Override
	public String getVersionUserName() {
		return _fileEntry.getVersionUserName();
	}

	@Override
	public String getVersionUserUuid() throws SystemException {
		return _fileEntry.getVersionUserUuid();
	}

	@Override
	public FileEntry getWrappedModel() {
		return _fileEntry;
	}

	@Override
	public int hashCode() {
		return _fileEntry.hashCode();
	}

	@Override
	public boolean hasLock() {
		return _fileEntry.hasLock();
	}

	@Override
	public boolean isCheckedOut() {
		return _fileEntry.isCheckedOut();
	}

	@Override
	public boolean isDefaultRepository() {
		return _fileEntry.isDefaultRepository();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileEntry.isEscapedModel();
	}

	@Override
	public boolean isInTrash() {
		return _fileEntry.isInTrash();
	}

	@Override
	public boolean isInTrashContainer() {
		return _fileEntry.isInTrashContainer();
	}

	@Override
	public boolean isManualCheckInRequired() {
		return _fileEntry.isManualCheckInRequired();
	}

	@Override
	public boolean isSupportsLocking() {
		return _fileEntry.isSupportsLocking();
	}

	@Override
	public boolean isSupportsMetadata() {
		return _fileEntry.isSupportsMetadata();
	}

	@Override
	public boolean isSupportsSocial() {
		return _fileEntry.isSupportsSocial();
	}

	@Override
	public void setCompanyId(long companyId) {
		_fileEntry.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date date) {
		_fileEntry.setCreateDate(date);
	}

	@Override
	public void setGroupId(long groupId) {
		_fileEntry.setGroupId(groupId);
	}

	@Override
	public void setModifiedDate(Date date) {
		_fileEntry.setModifiedDate(date);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setUserId(long userId) {
		_fileEntry.setUserId(userId);
	}

	@Override
	public void setUserName(String userName) {
		_fileEntry.setUserName(userName);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_fileEntry.setUserUuid(userUuid);
	}

	@Override
	public void setUuid(String uuid) {
		_fileEntry.setUuid(uuid);
	}

	@Override
	public FileEntry toEscapedModel() {
		return new FileEntryWrapper(_fileEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _fileEntry.toString();
	}

	@Override
	public FileEntry toUnescapedModel() {
		return new FileEntryWrapper(_fileEntry.toUnescapedModel());
	}

	private FileEntry _fileEntry;

}