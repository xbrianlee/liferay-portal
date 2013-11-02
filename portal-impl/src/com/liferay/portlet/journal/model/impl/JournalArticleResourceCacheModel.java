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

import com.liferay.portlet.journal.model.JournalArticleResource;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JournalArticleResource in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleResource
 * @generated
 */
public class JournalArticleResourceCacheModel implements CacheModel<JournalArticleResource>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JournalArticleResource toEntityModel() {
		JournalArticleResourceImpl journalArticleResourceImpl = new JournalArticleResourceImpl();

		if (uuid == null) {
			journalArticleResourceImpl.setUuid(StringPool.BLANK);
		}
		else {
			journalArticleResourceImpl.setUuid(uuid);
		}

		journalArticleResourceImpl.setResourcePrimKey(resourcePrimKey);
		journalArticleResourceImpl.setGroupId(groupId);

		if (articleId == null) {
			journalArticleResourceImpl.setArticleId(StringPool.BLANK);
		}
		else {
			journalArticleResourceImpl.setArticleId(articleId);
		}

		journalArticleResourceImpl.resetOriginalValues();

		return journalArticleResourceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		resourcePrimKey = objectInput.readLong();
		groupId = objectInput.readLong();
		articleId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(resourcePrimKey);
		objectOutput.writeLong(groupId);

		if (articleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(articleId);
		}
	}

	public String uuid;
	public long resourcePrimKey;
	public long groupId;
	public String articleId;
}