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

package com.liferay.portal.util;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletCategoryKeys {

	public static final String[] ALL = {
		PortletCategoryKeys.USERS, PortletCategoryKeys.SITES,
		PortletCategoryKeys.APPS, PortletCategoryKeys.CONFIGURATION
	};

	public static final String APPS = "apps";

	public static final String CONFIGURATION = "configuration";

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String CONTENT = "content";

	public static final String CURRENT_SITE = "current_site";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #APPS}
	 */
	public static final String MARKETPLACE = "marketplace";

	public static final String MY = "my";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #CONFIGURATION}, {@link
	 *             #SITES}, or {@link #USERS}.
	 */
	public static final String PORTAL = CONFIGURATION;

	public static final String PORTLET = "portlet";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #APPS}
	 */
	public static final String SERVER = APPS;

	public static final String SITE_ADMINISTRATION = "site_administration.";

	public static final String[] SITE_ADMINISTRATION_ALL = {
		PortletCategoryKeys.SITE_ADMINISTRATION_PAGES,
		PortletCategoryKeys.SITE_ADMINISTRATION_CONTENT,
		PortletCategoryKeys.SITE_ADMINISTRATION_USERS,
		PortletCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	};

	public static final String SITE_ADMINISTRATION_CONFIGURATION =
		"site_administration.configuration";

	public static final String SITE_ADMINISTRATION_CONTENT =
		"site_administration.content";

	public static final String SITE_ADMINISTRATION_PAGES =
		"site_administration.pages";

	public static final String SITE_ADMINISTRATION_USERS =
		"site_administration.users";

	public static final String SITES = "sites";

	public static final String USERS = "users";

}