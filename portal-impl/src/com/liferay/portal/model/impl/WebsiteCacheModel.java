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
import com.liferay.portal.model.Website;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Website in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Website
 * @generated
 */
public class WebsiteCacheModel implements CacheModel<Website>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", websiteId=");
		sb.append(websiteId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", url=");
		sb.append(url);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Website toEntityModel() {
		WebsiteImpl websiteImpl = new WebsiteImpl();

		if (uuid == null) {
			websiteImpl.setUuid(StringPool.BLANK);
		}
		else {
			websiteImpl.setUuid(uuid);
		}

		websiteImpl.setWebsiteId(websiteId);
		websiteImpl.setCompanyId(companyId);
		websiteImpl.setUserId(userId);

		if (userName == null) {
			websiteImpl.setUserName(StringPool.BLANK);
		}
		else {
			websiteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			websiteImpl.setCreateDate(null);
		}
		else {
			websiteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			websiteImpl.setModifiedDate(null);
		}
		else {
			websiteImpl.setModifiedDate(new Date(modifiedDate));
		}

		websiteImpl.setClassNameId(classNameId);
		websiteImpl.setClassPK(classPK);

		if (url == null) {
			websiteImpl.setUrl(StringPool.BLANK);
		}
		else {
			websiteImpl.setUrl(url);
		}

		websiteImpl.setTypeId(typeId);
		websiteImpl.setPrimary(primary);

		websiteImpl.resetOriginalValues();

		return websiteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		websiteId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		url = objectInput.readUTF();
		typeId = objectInput.readInt();
		primary = objectInput.readBoolean();
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

		objectOutput.writeLong(websiteId);
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
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeInt(typeId);
		objectOutput.writeBoolean(primary);
	}

	public String uuid;
	public long websiteId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String url;
	public int typeId;
	public boolean primary;
}