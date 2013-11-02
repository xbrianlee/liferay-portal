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

package com.liferay.portlet.expando.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.expando.model.ExpandoRow;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ExpandoRow in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoRow
 * @generated
 */
public class ExpandoRowCacheModel implements CacheModel<ExpandoRow>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{rowId=");
		sb.append(rowId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", tableId=");
		sb.append(tableId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExpandoRow toEntityModel() {
		ExpandoRowImpl expandoRowImpl = new ExpandoRowImpl();

		expandoRowImpl.setRowId(rowId);
		expandoRowImpl.setCompanyId(companyId);

		if (modifiedDate == Long.MIN_VALUE) {
			expandoRowImpl.setModifiedDate(null);
		}
		else {
			expandoRowImpl.setModifiedDate(new Date(modifiedDate));
		}

		expandoRowImpl.setTableId(tableId);
		expandoRowImpl.setClassPK(classPK);

		expandoRowImpl.resetOriginalValues();

		return expandoRowImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		rowId = objectInput.readLong();
		companyId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		tableId = objectInput.readLong();
		classPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(rowId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(tableId);
		objectOutput.writeLong(classPK);
	}

	public long rowId;
	public long companyId;
	public long modifiedDate;
	public long tableId;
	public long classPK;
}