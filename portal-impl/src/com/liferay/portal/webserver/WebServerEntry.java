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

package com.liferay.portal.webserver;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class WebServerEntry {

	public WebServerEntry(String path, String name) {
		this(path, name, null, null, null, 0);
	}

	public WebServerEntry(
		String path, String name, Date createDate, Date modifiedDate,
		String description, long size) {

		_path = getPath(path, name);
		_name = name;
		_createDate = createDate;
		_modifiedDate = modifiedDate;
		_description = GetterUtil.getString(description);
		_size = size;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public String getPath() {
		return _path;
	}

	public long getSize() {
		return _size;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPath(String path) {
		_path = path;
	}

	public void setSize(long size) {
		_size = size;
	}

	protected String getPath(String path, String name) {
		if (name.endsWith(StringPool.SLASH)) {
			name = HttpUtil.fixPath(name, false, true);

			return getPath(path, name) + StringPool.SLASH;
		}

		if (path.endsWith(StringPool.SLASH)) {
			path = path + HttpUtil.encodeURL(name, true);
		}
		else {
			path = path + StringPool.SLASH + HttpUtil.encodeURL(name, true);
		}

		return path;
	}

	private Date _createDate;
	private String _description;
	private Date _modifiedDate;
	private String _name;
	private String _path;
	private long _size;

}