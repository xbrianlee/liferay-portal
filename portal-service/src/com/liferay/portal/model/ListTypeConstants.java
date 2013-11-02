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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Alexander Chow
 */
public class ListTypeConstants {

	// Common

	public static final String ADDRESS = ".address";

	public static final String EMAIL_ADDRESS = ".emailAddress";

	public static final String PHONE = ".phone";

	public static final String WEBSITE = ".website";

	// Account

	public static final String ACCOUNT_ADDRESS =
		Account.class.getName() + ADDRESS;

	public static final int ACCOUNT_ADDRESS_DEFAULT = GetterUtil.getInteger(
		PropsUtil.get(
			PropsKeys.
				SQL_DATA_COM_LIFERAY_PORTAL_MODEL_LISTTYPE_ACCOUNT_ADDRESS));

	public static final String ACCOUNT_EMAIL_ADDRESS =
		Account.class.getName() + EMAIL_ADDRESS;

	public static final int ACCOUNT_EMAIL_ADDRESS_DEFAULT =
		GetterUtil.getInteger(PropsUtil.get(PropsKeys.
			SQL_DATA_COM_LIFERAY_PORTAL_MODEL_LISTTYPE_ACCOUNT_EMAIL_ADDRESS));

	public static final String ACCOUNT_PHONE = Account.class.getName() + PHONE;

	public static final String ACCOUNT_WEBSITE =
		Account.class.getName() + WEBSITE;

	// Contact

	public static final String CONTACT_ADDRESS =
		Contact.class.getName() + ADDRESS;

	public static final String CONTACT_EMAIL_ADDRESS =
		Contact.class.getName() + EMAIL_ADDRESS;

	public static final int CONTACT_EMAIL_ADDRESS_DEFAULT =
		GetterUtil.getInteger(PropsUtil.get(PropsKeys.
			SQL_DATA_COM_LIFERAY_PORTAL_MODEL_LISTTYPE_CONTACT_EMAIL_ADDRESS));

	public static final String CONTACT_PHONE = Contact.class.getName() + PHONE;

	public static final String CONTACT_PREFIX =
		Contact.class.getName() + ".prefix";

	public static final String CONTACT_SUFFIX =
		Contact.class.getName() + ".suffix";

	public static final String CONTACT_WEBSITE =
		Contact.class.getName() + WEBSITE;

	// Organization

	public static final String ORGANIZATION_ADDRESS =
		Organization.class.getName() + ADDRESS;

	public static final String ORGANIZATION_EMAIL_ADDRESS =
		Organization.class.getName() + EMAIL_ADDRESS;

	public static final String ORGANIZATION_PHONE =
		Organization.class.getName() + PHONE;

	public static final String ORGANIZATION_SERVICE =
		Organization.class.getName() + ".service";

	public static final String ORGANIZATION_STATUS =
		Organization.class.getName() + ".status";

	public static final int ORGANIZATION_STATUS_DEFAULT =
		GetterUtil.getInteger(PropsUtil.get(PropsKeys.
			SQL_DATA_COM_LIFERAY_PORTAL_MODEL_LISTTYPE_ORGANIZATION_STATUS));

	public static final String ORGANIZATION_WEBSITE =
		Organization.class.getName() + WEBSITE;

}