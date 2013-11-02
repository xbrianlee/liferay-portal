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
import com.liferay.portal.model.Shard;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Shard in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Shard
 * @generated
 */
public class ShardCacheModel implements CacheModel<Shard>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{shardId=");
		sb.append(shardId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Shard toEntityModel() {
		ShardImpl shardImpl = new ShardImpl();

		shardImpl.setShardId(shardId);
		shardImpl.setClassNameId(classNameId);
		shardImpl.setClassPK(classPK);

		if (name == null) {
			shardImpl.setName(StringPool.BLANK);
		}
		else {
			shardImpl.setName(name);
		}

		shardImpl.resetOriginalValues();

		return shardImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		shardId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(shardId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long shardId;
	public long classNameId;
	public long classPK;
	public String name;
}