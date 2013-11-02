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
import com.liferay.portal.model.ResourceAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ResourceAction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceAction
 * @generated
 */
public class ResourceActionCacheModel implements CacheModel<ResourceAction>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{resourceActionId=");
		sb.append(resourceActionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", actionId=");
		sb.append(actionId);
		sb.append(", bitwiseValue=");
		sb.append(bitwiseValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceAction toEntityModel() {
		ResourceActionImpl resourceActionImpl = new ResourceActionImpl();

		resourceActionImpl.setResourceActionId(resourceActionId);

		if (name == null) {
			resourceActionImpl.setName(StringPool.BLANK);
		}
		else {
			resourceActionImpl.setName(name);
		}

		if (actionId == null) {
			resourceActionImpl.setActionId(StringPool.BLANK);
		}
		else {
			resourceActionImpl.setActionId(actionId);
		}

		resourceActionImpl.setBitwiseValue(bitwiseValue);

		resourceActionImpl.resetOriginalValues();

		return resourceActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		resourceActionId = objectInput.readLong();
		name = objectInput.readUTF();
		actionId = objectInput.readUTF();
		bitwiseValue = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(resourceActionId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (actionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionId);
		}

		objectOutput.writeLong(bitwiseValue);
	}

	public long resourceActionId;
	public String name;
	public String actionId;
	public long bitwiseValue;
}