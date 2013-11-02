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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.journal.model.JournalArticleImage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JournalArticleImage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImage
 * @generated
 */
public class JournalArticleImageCacheModel implements CacheModel<JournalArticleImage>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{articleImageId=");
		sb.append(articleImageId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", elInstanceId=");
		sb.append(elInstanceId);
		sb.append(", elName=");
		sb.append(elName);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", tempImage=");
		sb.append(tempImage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JournalArticleImage toEntityModel() {
		JournalArticleImageImpl journalArticleImageImpl = new JournalArticleImageImpl();

		journalArticleImageImpl.setArticleImageId(articleImageId);
		journalArticleImageImpl.setGroupId(groupId);

		if (articleId == null) {
			journalArticleImageImpl.setArticleId(StringPool.BLANK);
		}
		else {
			journalArticleImageImpl.setArticleId(articleId);
		}

		journalArticleImageImpl.setVersion(version);

		if (elInstanceId == null) {
			journalArticleImageImpl.setElInstanceId(StringPool.BLANK);
		}
		else {
			journalArticleImageImpl.setElInstanceId(elInstanceId);
		}

		if (elName == null) {
			journalArticleImageImpl.setElName(StringPool.BLANK);
		}
		else {
			journalArticleImageImpl.setElName(elName);
		}

		if (languageId == null) {
			journalArticleImageImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			journalArticleImageImpl.setLanguageId(languageId);
		}

		journalArticleImageImpl.setTempImage(tempImage);

		journalArticleImageImpl.resetOriginalValues();

		return journalArticleImageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		articleImageId = objectInput.readLong();
		groupId = objectInput.readLong();
		articleId = objectInput.readUTF();
		version = objectInput.readDouble();
		elInstanceId = objectInput.readUTF();
		elName = objectInput.readUTF();
		languageId = objectInput.readUTF();
		tempImage = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(articleImageId);
		objectOutput.writeLong(groupId);

		if (articleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(articleId);
		}

		objectOutput.writeDouble(version);

		if (elInstanceId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(elInstanceId);
		}

		if (elName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(elName);
		}

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		objectOutput.writeBoolean(tempImage);
	}

	public long articleImageId;
	public long groupId;
	public String articleId;
	public double version;
	public String elInstanceId;
	public String elName;
	public String languageId;
	public boolean tempImage;
}