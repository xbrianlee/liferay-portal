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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.UserGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroup
 * @generated
 */
public class UserGroupCacheModel implements CacheModel<UserGroup>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userGroupId=");
		sb.append(userGroupId);
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
		sb.append(", parentUserGroupId=");
		sb.append(parentUserGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", addedByLDAPImport=");
		sb.append(addedByLDAPImport);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserGroup toEntityModel() {
		UserGroupImpl userGroupImpl = new UserGroupImpl();

		if (uuid == null) {
			userGroupImpl.setUuid(StringPool.BLANK);
		}
		else {
			userGroupImpl.setUuid(uuid);
		}

		userGroupImpl.setUserGroupId(userGroupId);
		userGroupImpl.setCompanyId(companyId);
		userGroupImpl.setUserId(userId);

		if (userName == null) {
			userGroupImpl.setUserName(StringPool.BLANK);
		}
		else {
			userGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userGroupImpl.setCreateDate(null);
		}
		else {
			userGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userGroupImpl.setModifiedDate(null);
		}
		else {
			userGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		userGroupImpl.setParentUserGroupId(parentUserGroupId);

		if (name == null) {
			userGroupImpl.setName(StringPool.BLANK);
		}
		else {
			userGroupImpl.setName(name);
		}

		if (description == null) {
			userGroupImpl.setDescription(StringPool.BLANK);
		}
		else {
			userGroupImpl.setDescription(description);
		}

		userGroupImpl.setAddedByLDAPImport(addedByLDAPImport);

		userGroupImpl.resetOriginalValues();

		return userGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		userGroupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		parentUserGroupId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		addedByLDAPImport = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(userGroupId);
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
		objectOutput.writeLong(parentUserGroupId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeBoolean(addedByLDAPImport);
	}

	public String uuid;
	public long userGroupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentUserGroupId;
	public String name;
	public String description;
	public boolean addedByLDAPImport;
}