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

package com.liferay.portlet.dynamicdatalists.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DDLRecordSet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSet
 * @generated
 */
public class DDLRecordSetCacheModel implements CacheModel<DDLRecordSet>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", recordSetId=");
		sb.append(recordSetId);
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
		sb.append(", DDMStructureId=");
		sb.append(DDMStructureId);
		sb.append(", recordSetKey=");
		sb.append(recordSetKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", minDisplayRows=");
		sb.append(minDisplayRows);
		sb.append(", scope=");
		sb.append(scope);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDLRecordSet toEntityModel() {
		DDLRecordSetImpl ddlRecordSetImpl = new DDLRecordSetImpl();

		if (uuid == null) {
			ddlRecordSetImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddlRecordSetImpl.setUuid(uuid);
		}

		ddlRecordSetImpl.setRecordSetId(recordSetId);
		ddlRecordSetImpl.setGroupId(groupId);
		ddlRecordSetImpl.setCompanyId(companyId);
		ddlRecordSetImpl.setUserId(userId);

		if (userName == null) {
			ddlRecordSetImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddlRecordSetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddlRecordSetImpl.setCreateDate(null);
		}
		else {
			ddlRecordSetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddlRecordSetImpl.setModifiedDate(null);
		}
		else {
			ddlRecordSetImpl.setModifiedDate(new Date(modifiedDate));
		}

		ddlRecordSetImpl.setDDMStructureId(DDMStructureId);

		if (recordSetKey == null) {
			ddlRecordSetImpl.setRecordSetKey(StringPool.BLANK);
		}
		else {
			ddlRecordSetImpl.setRecordSetKey(recordSetKey);
		}

		if (name == null) {
			ddlRecordSetImpl.setName(StringPool.BLANK);
		}
		else {
			ddlRecordSetImpl.setName(name);
		}

		if (description == null) {
			ddlRecordSetImpl.setDescription(StringPool.BLANK);
		}
		else {
			ddlRecordSetImpl.setDescription(description);
		}

		ddlRecordSetImpl.setMinDisplayRows(minDisplayRows);
		ddlRecordSetImpl.setScope(scope);

		ddlRecordSetImpl.resetOriginalValues();

		return ddlRecordSetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		recordSetId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		DDMStructureId = objectInput.readLong();
		recordSetKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		minDisplayRows = objectInput.readInt();
		scope = objectInput.readInt();
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

		objectOutput.writeLong(recordSetId);
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
		objectOutput.writeLong(DDMStructureId);

		if (recordSetKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recordSetKey);
		}

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

		objectOutput.writeInt(minDisplayRows);
		objectOutput.writeInt(scope);
	}

	public String uuid;
	public long recordSetId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long DDMStructureId;
	public String recordSetKey;
	public String name;
	public String description;
	public int minDisplayRows;
	public int scope;
}