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
import com.liferay.portal.model.ResourceTypePermission;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ResourceTypePermission in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceTypePermission
 * @generated
 */
public class ResourceTypePermissionCacheModel implements CacheModel<ResourceTypePermission>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{resourceTypePermissionId=");
		sb.append(resourceTypePermissionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", actionIds=");
		sb.append(actionIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceTypePermission toEntityModel() {
		ResourceTypePermissionImpl resourceTypePermissionImpl = new ResourceTypePermissionImpl();

		resourceTypePermissionImpl.setResourceTypePermissionId(resourceTypePermissionId);
		resourceTypePermissionImpl.setCompanyId(companyId);
		resourceTypePermissionImpl.setGroupId(groupId);

		if (name == null) {
			resourceTypePermissionImpl.setName(StringPool.BLANK);
		}
		else {
			resourceTypePermissionImpl.setName(name);
		}

		resourceTypePermissionImpl.setRoleId(roleId);
		resourceTypePermissionImpl.setActionIds(actionIds);

		resourceTypePermissionImpl.resetOriginalValues();

		return resourceTypePermissionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		resourceTypePermissionId = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		name = objectInput.readUTF();
		roleId = objectInput.readLong();
		actionIds = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(resourceTypePermissionId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(roleId);
		objectOutput.writeLong(actionIds);
	}

	public long resourceTypePermissionId;
	public long companyId;
	public long groupId;
	public String name;
	public long roleId;
	public long actionIds;
}