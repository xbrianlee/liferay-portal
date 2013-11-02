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
import com.liferay.portal.model.Lock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Lock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Lock
 * @generated
 */
public class LockCacheModel implements CacheModel<Lock>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", lockId=");
		sb.append(lockId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", className=");
		sb.append(className);
		sb.append(", key=");
		sb.append(key);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", inheritable=");
		sb.append(inheritable);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Lock toEntityModel() {
		LockImpl lockImpl = new LockImpl();

		if (uuid == null) {
			lockImpl.setUuid(StringPool.BLANK);
		}
		else {
			lockImpl.setUuid(uuid);
		}

		lockImpl.setLockId(lockId);
		lockImpl.setCompanyId(companyId);
		lockImpl.setUserId(userId);

		if (userName == null) {
			lockImpl.setUserName(StringPool.BLANK);
		}
		else {
			lockImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			lockImpl.setCreateDate(null);
		}
		else {
			lockImpl.setCreateDate(new Date(createDate));
		}

		if (className == null) {
			lockImpl.setClassName(StringPool.BLANK);
		}
		else {
			lockImpl.setClassName(className);
		}

		if (key == null) {
			lockImpl.setKey(StringPool.BLANK);
		}
		else {
			lockImpl.setKey(key);
		}

		if (owner == null) {
			lockImpl.setOwner(StringPool.BLANK);
		}
		else {
			lockImpl.setOwner(owner);
		}

		lockImpl.setInheritable(inheritable);

		if (expirationDate == Long.MIN_VALUE) {
			lockImpl.setExpirationDate(null);
		}
		else {
			lockImpl.setExpirationDate(new Date(expirationDate));
		}

		lockImpl.resetOriginalValues();

		return lockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		lockId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		className = objectInput.readUTF();
		key = objectInput.readUTF();
		owner = objectInput.readUTF();
		inheritable = objectInput.readBoolean();
		expirationDate = objectInput.readLong();
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

		objectOutput.writeLong(lockId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (className == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(className);
		}

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (owner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(owner);
		}

		objectOutput.writeBoolean(inheritable);
		objectOutput.writeLong(expirationDate);
	}

	public String uuid;
	public long lockId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public String className;
	public String key;
	public String owner;
	public boolean inheritable;
	public long expirationDate;
}