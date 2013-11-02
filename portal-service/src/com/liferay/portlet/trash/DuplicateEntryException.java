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

package com.liferay.portlet.trash;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateEntryException extends PortalException {

	public DuplicateEntryException() {
		super();
	}

	public DuplicateEntryException(String msg) {
		super(msg);
	}

	public DuplicateEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateEntryException(Throwable cause) {
		super(cause);
	}

	public long getDuplicateEntryId() {
		return _duplicateEntryId;
	}

	public String getOldName() {
		return _oldName;
	}

	public long getTrashEntryId() {
		return _trashEntryId;
	}

	public void setDuplicateEntryId(long duplicateEntryId) {
		_duplicateEntryId = duplicateEntryId;
	}

	public void setOldName(String oldName) {
		_oldName = oldName;
	}

	public void setTrashEntryId(long trashEntryId) {
		_trashEntryId = trashEntryId;
	}

	private long _duplicateEntryId;
	private String _oldName;
	private long _trashEntryId;

}