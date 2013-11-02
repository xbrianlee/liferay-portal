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

/**
 * @author Brian Wing Shun Chan
 */
public class GroupConstants {

	public static final int ANY_PARENT_GROUP_ID = -1;

	public static final String CONTROL_PANEL = "Control Panel";

	public static final String CONTROL_PANEL_FRIENDLY_URL = "/control_panel";

	public static final String DEFAULT = "default";

	public static final long DEFAULT_LIVE_GROUP_ID = 0;

	public static final int DEFAULT_MEMBERSHIP_RESTRICTION = 0;

	public static final long DEFAULT_PARENT_GROUP_ID = 0;

	public static final String GLOBAL = "Global";

	public static final String GLOBAL_FRIENDLY_URL = "/global";

	public static final String GUEST = "Guest";

	public static final int MEMBERSHIP_RESTRICTION_TO_PARENT_SITE_MEMBERS = 1;

	public static final String[] SYSTEM_GROUPS = {
		CONTROL_PANEL, GUEST, GroupConstants.USER_PERSONAL_SITE
	};

	public static final int TYPE_SITE_OPEN = 1;

	public static final String TYPE_SITE_OPEN_LABEL = "open";

	public static final int TYPE_SITE_PRIVATE = 3;

	public static final String TYPE_SITE_PRIVATE_LABEL = "private";

	public static final int TYPE_SITE_RESTRICTED = 2;

	public static final String TYPE_SITE_RESTRICTED_LABEL = "restricted";

	public static final int TYPE_SITE_SYSTEM = 4;

	public static final String TYPE_SITE_SYSTEM_LABEL = "system";

	public static final String USER_PERSONAL_SITE = "User Personal Site";

	public static final String USER_PERSONAL_SITE_FRIENDLY_URL =
		"/personal_site";

	public static String getTypeLabel(int type) {
		if (type == TYPE_SITE_OPEN) {
			return TYPE_SITE_OPEN_LABEL;
		}
		else if (type == TYPE_SITE_PRIVATE) {
			return TYPE_SITE_PRIVATE_LABEL;
		}
		else if (type == TYPE_SITE_RESTRICTED) {
			return TYPE_SITE_RESTRICTED_LABEL;
		}
		else {
			return TYPE_SITE_SYSTEM_LABEL;
		}
	}

}