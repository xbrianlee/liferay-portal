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

import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SCFrameworkVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SCFrameworkVersion
 * @generated
 */
public class SCFrameworkVersionCacheModel implements CacheModel<SCFrameworkVersion>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{frameworkVersionId=");
		sb.append(frameworkVersionId);
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
		sb.append(", url=");
		sb.append(url);
		sb.append(", active=");
		sb.append(active);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SCFrameworkVersion toEntityModel() {
		SCFrameworkVersionImpl scFrameworkVersionImpl = new SCFrameworkVersionImpl();

		scFrameworkVersionImpl.setFrameworkVersionId(frameworkVersionId);
		scFrameworkVersionImpl.setGroupId(groupId);
		scFrameworkVersionImpl.setCompanyId(companyId);
		scFrameworkVersionImpl.setUserId(userId);

		if (userName == null) {
			scFrameworkVersionImpl.setUserName(StringPool.BLANK);
		}
		else {
			scFrameworkVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scFrameworkVersionImpl.setCreateDate(null);
		}
		else {
			scFrameworkVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scFrameworkVersionImpl.setModifiedDate(null);
		}
		else {
			scFrameworkVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			scFrameworkVersionImpl.setName(StringPool.BLANK);
		}
		else {
			scFrameworkVersionImpl.setName(name);
		}

		if (url == null) {
			scFrameworkVersionImpl.setUrl(StringPool.BLANK);
		}
		else {
			scFrameworkVersionImpl.setUrl(url);
		}

		scFrameworkVersionImpl.setActive(active);
		scFrameworkVersionImpl.setPriority(priority);

		scFrameworkVersionImpl.resetOriginalValues();

		return scFrameworkVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		frameworkVersionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		active = objectInput.readBoolean();
		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(frameworkVersionId);
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

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeBoolean(active);
		objectOutput.writeInt(priority);
	}

	public long frameworkVersionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public boolean active;
	public int priority;
}