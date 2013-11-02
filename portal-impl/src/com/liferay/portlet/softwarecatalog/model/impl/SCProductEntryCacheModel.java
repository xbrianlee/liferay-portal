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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.softwarecatalog.model.SCProductEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SCProductEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SCProductEntry
 * @generated
 */
public class SCProductEntryCacheModel implements CacheModel<SCProductEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{productEntryId=");
		sb.append(productEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", tags=");
		sb.append(tags);
		sb.append(", shortDescription=");
		sb.append(shortDescription);
		sb.append(", longDescription=");
		sb.append(longDescription);
		sb.append(", pageURL=");
		sb.append(pageURL);
		sb.append(", author=");
		sb.append(author);
		sb.append(", repoGroupId=");
		sb.append(repoGroupId);
		sb.append(", repoArtifactId=");
		sb.append(repoArtifactId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SCProductEntry toEntityModel() {
		SCProductEntryImpl scProductEntryImpl = new SCProductEntryImpl();

		scProductEntryImpl.setProductEntryId(productEntryId);
		scProductEntryImpl.setGroupId(groupId);
		scProductEntryImpl.setCompanyId(companyId);
		scProductEntryImpl.setUserId(userId);

		if (userName == null) {
			scProductEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scProductEntryImpl.setCreateDate(null);
		}
		else {
			scProductEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scProductEntryImpl.setModifiedDate(null);
		}
		else {
			scProductEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			scProductEntryImpl.setName(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setName(name);
		}

		if (type == null) {
			scProductEntryImpl.setType(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setType(type);
		}

		if (tags == null) {
			scProductEntryImpl.setTags(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setTags(tags);
		}

		if (shortDescription == null) {
			scProductEntryImpl.setShortDescription(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setShortDescription(shortDescription);
		}

		if (longDescription == null) {
			scProductEntryImpl.setLongDescription(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setLongDescription(longDescription);
		}

		if (pageURL == null) {
			scProductEntryImpl.setPageURL(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setPageURL(pageURL);
		}

		if (author == null) {
			scProductEntryImpl.setAuthor(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setAuthor(author);
		}

		if (repoGroupId == null) {
			scProductEntryImpl.setRepoGroupId(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setRepoGroupId(repoGroupId);
		}

		if (repoArtifactId == null) {
			scProductEntryImpl.setRepoArtifactId(StringPool.BLANK);
		}
		else {
			scProductEntryImpl.setRepoArtifactId(repoArtifactId);
		}

		scProductEntryImpl.resetOriginalValues();

		return scProductEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		productEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();
		tags = objectInput.readUTF();
		shortDescription = objectInput.readUTF();
		longDescription = objectInput.readUTF();
		pageURL = objectInput.readUTF();
		author = objectInput.readUTF();
		repoGroupId = objectInput.readUTF();
		repoArtifactId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(productEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (tags == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tags);
		}

		if (shortDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortDescription);
		}

		if (longDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(longDescription);
		}

		if (pageURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pageURL);
		}

		if (author == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(author);
		}

		if (repoGroupId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(repoGroupId);
		}

		if (repoArtifactId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(repoArtifactId);
		}
	}

	public long productEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String type;
	public String tags;
	public String shortDescription;
	public String longDescription;
	public String pageURL;
	public String author;
	public String repoGroupId;
	public String repoArtifactId;
}