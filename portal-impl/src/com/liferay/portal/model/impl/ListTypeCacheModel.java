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
import com.liferay.portal.model.ListType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ListType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ListType
 * @generated
 */
public class ListTypeCacheModel implements CacheModel<ListType>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{listTypeId=");
		sb.append(listTypeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ListType toEntityModel() {
		ListTypeImpl listTypeImpl = new ListTypeImpl();

		listTypeImpl.setListTypeId(listTypeId);

		if (name == null) {
			listTypeImpl.setName(StringPool.BLANK);
		}
		else {
			listTypeImpl.setName(name);
		}

		if (type == null) {
			listTypeImpl.setType(StringPool.BLANK);
		}
		else {
			listTypeImpl.setType(type);
		}

		listTypeImpl.resetOriginalValues();

		return listTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		listTypeId = objectInput.readInt();
		name = objectInput.readUTF();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(listTypeId);

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
	}

	public int listTypeId;
	public String name;
	public String type;
}