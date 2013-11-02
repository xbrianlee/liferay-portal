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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SCProductScreenshot in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SCProductScreenshot
 * @generated
 */
public class SCProductScreenshotCacheModel implements CacheModel<SCProductScreenshot>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{productScreenshotId=");
		sb.append(productScreenshotId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", thumbnailId=");
		sb.append(thumbnailId);
		sb.append(", fullImageId=");
		sb.append(fullImageId);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SCProductScreenshot toEntityModel() {
		SCProductScreenshotImpl scProductScreenshotImpl = new SCProductScreenshotImpl();

		scProductScreenshotImpl.setProductScreenshotId(productScreenshotId);
		scProductScreenshotImpl.setCompanyId(companyId);
		scProductScreenshotImpl.setGroupId(groupId);
		scProductScreenshotImpl.setProductEntryId(productEntryId);
		scProductScreenshotImpl.setThumbnailId(thumbnailId);
		scProductScreenshotImpl.setFullImageId(fullImageId);
		scProductScreenshotImpl.setPriority(priority);

		scProductScreenshotImpl.resetOriginalValues();

		return scProductScreenshotImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		productScreenshotId = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		productEntryId = objectInput.readLong();
		thumbnailId = objectInput.readLong();
		fullImageId = objectInput.readLong();
		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(productScreenshotId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(productEntryId);
		objectOutput.writeLong(thumbnailId);
		objectOutput.writeLong(fullImageId);
		objectOutput.writeInt(priority);
	}

	public long productScreenshotId;
	public long companyId;
	public long groupId;
	public long productEntryId;
	public long thumbnailId;
	public long fullImageId;
	public int priority;
}