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
import com.liferay.portal.model.ClusterGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ClusterGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroup
 * @generated
 */
public class ClusterGroupCacheModel implements CacheModel<ClusterGroup>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{clusterGroupId=");
		sb.append(clusterGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", clusterNodeIds=");
		sb.append(clusterNodeIds);
		sb.append(", wholeCluster=");
		sb.append(wholeCluster);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ClusterGroup toEntityModel() {
		ClusterGroupImpl clusterGroupImpl = new ClusterGroupImpl();

		clusterGroupImpl.setClusterGroupId(clusterGroupId);

		if (name == null) {
			clusterGroupImpl.setName(StringPool.BLANK);
		}
		else {
			clusterGroupImpl.setName(name);
		}

		if (clusterNodeIds == null) {
			clusterGroupImpl.setClusterNodeIds(StringPool.BLANK);
		}
		else {
			clusterGroupImpl.setClusterNodeIds(clusterNodeIds);
		}

		clusterGroupImpl.setWholeCluster(wholeCluster);

		clusterGroupImpl.resetOriginalValues();

		return clusterGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		clusterGroupId = objectInput.readLong();
		name = objectInput.readUTF();
		clusterNodeIds = objectInput.readUTF();
		wholeCluster = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(clusterGroupId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (clusterNodeIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(clusterNodeIds);
		}

		objectOutput.writeBoolean(wholeCluster);
	}

	public long clusterGroupId;
	public String name;
	public String clusterNodeIds;
	public boolean wholeCluster;
}