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
import com.liferay.portal.model.LayoutBranch;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LayoutBranch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranch
 * @generated
 */
public class LayoutBranchCacheModel implements CacheModel<LayoutBranch>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{LayoutBranchId=");
		sb.append(LayoutBranchId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", layoutSetBranchId=");
		sb.append(layoutSetBranchId);
		sb.append(", plid=");
		sb.append(plid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", master=");
		sb.append(master);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutBranch toEntityModel() {
		LayoutBranchImpl layoutBranchImpl = new LayoutBranchImpl();

		layoutBranchImpl.setLayoutBranchId(LayoutBranchId);
		layoutBranchImpl.setGroupId(groupId);
		layoutBranchImpl.setCompanyId(companyId);
		layoutBranchImpl.setUserId(userId);

		if (userName == null) {
			layoutBranchImpl.setUserName(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setUserName(userName);
		}

		layoutBranchImpl.setLayoutSetBranchId(layoutSetBranchId);
		layoutBranchImpl.setPlid(plid);

		if (name == null) {
			layoutBranchImpl.setName(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setName(name);
		}

		if (description == null) {
			layoutBranchImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setDescription(description);
		}

		layoutBranchImpl.setMaster(master);

		layoutBranchImpl.resetOriginalValues();

		return layoutBranchImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		LayoutBranchId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		layoutSetBranchId = objectInput.readLong();
		plid = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		master = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(LayoutBranchId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(layoutSetBranchId);
		objectOutput.writeLong(plid);

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

		objectOutput.writeBoolean(master);
	}

	public long LayoutBranchId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long layoutSetBranchId;
	public long plid;
	public String name;
	public String description;
	public boolean master;
}