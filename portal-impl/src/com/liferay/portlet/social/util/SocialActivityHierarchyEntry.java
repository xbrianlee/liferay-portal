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

package com.liferay.portlet.social.util;

import com.liferay.portal.util.PortalUtil;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityHierarchyEntry {

	public SocialActivityHierarchyEntry() {
	}

	public SocialActivityHierarchyEntry(long classNameId, long classPK) {
		_classNameId = classNameId;
		_classPK = classPK;
	}

	public String getClassName() {
		return PortalUtil.getClassName(_classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _classNameId;
	private long _classPK;

}