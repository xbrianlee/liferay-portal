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

package com.liferay.portlet.shopping.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.shopping.model.ShoppingItemField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ShoppingItemField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemField
 * @generated
 */
public class ShoppingItemFieldCacheModel implements CacheModel<ShoppingItemField>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{itemFieldId=");
		sb.append(itemFieldId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", values=");
		sb.append(values);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingItemField toEntityModel() {
		ShoppingItemFieldImpl shoppingItemFieldImpl = new ShoppingItemFieldImpl();

		shoppingItemFieldImpl.setItemFieldId(itemFieldId);
		shoppingItemFieldImpl.setItemId(itemId);

		if (name == null) {
			shoppingItemFieldImpl.setName(StringPool.BLANK);
		}
		else {
			shoppingItemFieldImpl.setName(name);
		}

		if (values == null) {
			shoppingItemFieldImpl.setValues(StringPool.BLANK);
		}
		else {
			shoppingItemFieldImpl.setValues(values);
		}

		if (description == null) {
			shoppingItemFieldImpl.setDescription(StringPool.BLANK);
		}
		else {
			shoppingItemFieldImpl.setDescription(description);
		}

		shoppingItemFieldImpl.resetOriginalValues();

		return shoppingItemFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemFieldId = objectInput.readLong();
		itemId = objectInput.readLong();
		name = objectInput.readUTF();
		values = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(itemFieldId);
		objectOutput.writeLong(itemId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (values == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(values);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long itemFieldId;
	public long itemId;
	public String name;
	public String values;
	public String description;
}