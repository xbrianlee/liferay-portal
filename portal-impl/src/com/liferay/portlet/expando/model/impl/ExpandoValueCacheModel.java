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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.expando.model.ExpandoValue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ExpandoValue in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoValue
 * @generated
 */
public class ExpandoValueCacheModel implements CacheModel<ExpandoValue>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{valueId=");
		sb.append(valueId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", tableId=");
		sb.append(tableId);
		sb.append(", columnId=");
		sb.append(columnId);
		sb.append(", rowId=");
		sb.append(rowId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExpandoValue toEntityModel() {
		ExpandoValueImpl expandoValueImpl = new ExpandoValueImpl();

		expandoValueImpl.setValueId(valueId);
		expandoValueImpl.setCompanyId(companyId);
		expandoValueImpl.setTableId(tableId);
		expandoValueImpl.setColumnId(columnId);
		expandoValueImpl.setRowId(rowId);
		expandoValueImpl.setClassNameId(classNameId);
		expandoValueImpl.setClassPK(classPK);

		if (data == null) {
			expandoValueImpl.setData(StringPool.BLANK);
		}
		else {
			expandoValueImpl.setData(data);
		}

		expandoValueImpl.resetOriginalValues();

		return expandoValueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		valueId = objectInput.readLong();
		companyId = objectInput.readLong();
		tableId = objectInput.readLong();
		columnId = objectInput.readLong();
		rowId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(valueId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(tableId);
		objectOutput.writeLong(columnId);
		objectOutput.writeLong(rowId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(data);
		}
	}

	public long valueId;
	public long companyId;
	public long tableId;
	public long columnId;
	public long rowId;
	public long classNameId;
	public long classPK;
	public String data;
}