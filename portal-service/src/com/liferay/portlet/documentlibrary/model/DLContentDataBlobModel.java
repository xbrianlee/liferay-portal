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

package com.liferay.portlet.documentlibrary.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the data column in DLContent.
 *
 * @author Brian Wing Shun Chan
 * @see DLContent
 * @generated
 */
public class DLContentDataBlobModel {
	public DLContentDataBlobModel() {
	}

	public DLContentDataBlobModel(long contentId) {
		_contentId = contentId;
	}

	public DLContentDataBlobModel(long contentId, Blob dataBlob) {
		_contentId = contentId;
		_dataBlob = dataBlob;
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public Blob getDataBlob() {
		return _dataBlob;
	}

	public void setDataBlob(Blob dataBlob) {
		_dataBlob = dataBlob;
	}

	private long _contentId;
	private Blob _dataBlob;
}