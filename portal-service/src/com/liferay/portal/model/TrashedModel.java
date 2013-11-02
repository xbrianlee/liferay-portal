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

package com.liferay.portal.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portlet.trash.model.TrashEntry;

/**
 * @author Zsolt Berentey
 */
public interface TrashedModel {

	public int getStatus();

	public TrashEntry getTrashEntry() throws PortalException, SystemException;

	public long getTrashEntryClassPK();

	public TrashHandler getTrashHandler();

	public boolean isInTrash();

	public boolean isInTrashContainer();

}