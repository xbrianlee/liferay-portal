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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.model.Lock;

import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface Resource {

	public String getClassName();

	public InputStream getContentAsStream() throws WebDAVException;

	public String getContentType();

	public String getCreateDate();

	public String getDisplayName();

	public String getHREF();

	public Lock getLock();

	public Object getModel();

	public String getModifiedDate();

	public long getPrimaryKey();

	public long getSize();

	public boolean isCollection();

	public boolean isLocked();

	public void setClassName(String className);

	public void setModel(Object model);

	public void setPrimaryKey(long primaryKey);

}