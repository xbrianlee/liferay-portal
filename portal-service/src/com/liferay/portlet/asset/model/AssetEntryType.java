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

package com.liferay.portlet.asset.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntryType implements Serializable {

	public String getClassName() {
		return _className;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getPortletTitle() {
		return _portletTitle;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public void setPortletTitle(String portletTitle) {
		_portletTitle = portletTitle;
	}

	private String _className;
	private long _classNameId;
	private String _portletId;
	private String _portletTitle;

}