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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.social.model.SocialRelation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRelation
 * @generated
 */
public class SocialRelationCacheModel implements CacheModel<SocialRelation>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", relationId=");
		sb.append(relationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", userId1=");
		sb.append(userId1);
		sb.append(", userId2=");
		sb.append(userId2);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialRelation toEntityModel() {
		SocialRelationImpl socialRelationImpl = new SocialRelationImpl();

		if (uuid == null) {
			socialRelationImpl.setUuid(StringPool.BLANK);
		}
		else {
			socialRelationImpl.setUuid(uuid);
		}

		socialRelationImpl.setRelationId(relationId);
		socialRelationImpl.setCompanyId(companyId);
		socialRelationImpl.setCreateDate(createDate);
		socialRelationImpl.setUserId1(userId1);
		socialRelationImpl.setUserId2(userId2);
		socialRelationImpl.setType(type);

		socialRelationImpl.resetOriginalValues();

		return socialRelationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		relationId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		userId1 = objectInput.readLong();
		userId2 = objectInput.readLong();
		type = objectInput.readInt();
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

		objectOutput.writeLong(relationId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(userId1);
		objectOutput.writeLong(userId2);
		objectOutput.writeInt(type);
	}

	public String uuid;
	public long relationId;
	public long companyId;
	public long createDate;
	public long userId1;
	public long userId2;
	public int type;
}