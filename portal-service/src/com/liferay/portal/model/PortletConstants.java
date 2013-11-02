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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletConstants {

	/**
	 * Default preferences.
	 */
	public static final String DEFAULT_PREFERENCES = "<portlet-preferences />";

	/**
	 * Facebook integration method for FBML.
	 */
	public static final String FACEBOOK_INTEGRATION_FBML = "fbml";

	/**
	 * Facebook integration method for IFrame.
	 */
	public static final String FACEBOOK_INTEGRATION_IFRAME = "iframe";

	/**
	 * Instance separator.
	 */
	public static final String INSTANCE_SEPARATOR = "_INSTANCE_";

	/**
	 * Layout separator.
	 */
	public static final String LAYOUT_SEPARATOR = "_LAYOUT_";

	/**
	 * User principal strategy for screen name.
	 */
	public static final String USER_PRINCIPAL_STRATEGY_SCREEN_NAME =
		"screenName";

	/**
	 * User principal strategy for screen name.
	 */
	public static final String USER_PRINCIPAL_STRATEGY_USER_ID = "userId";

	/**
	 * User separator.
	 */
	public static final String USER_SEPARATOR = "_USER_";

	/**
	 * War file separator.
	 */
	public static final String WAR_SEPARATOR = "_WAR_";

	/**
	 * Returns a properly assembled portlet ID from the parameters passed. If
	 * the portlet ID contains an instance ID it will be properly retained. If
	 * the portlet ID contains a user ID it will be replaced by the user ID
	 * parameter.
	 *
	 * @param  portletId the portlet ID
	 * @param  userId a user ID
	 * @return the properly assembled portlet ID
	 */
	public static String assemblePortletId(String portletId, long userId) {
		return assemblePortletId(portletId, userId, null);
	}

	/**
	 * Returns a properly assembled portlet ID from the parameters passed. If
	 * the portlet ID contains a user ID it will be replaced by the user ID
	 * parameter. If the portlet ID contains an instance ID it will be replaced
	 * by the instance ID parameter.
	 *
	 * @param  portletId the portlet ID
	 * @param  userId the user ID
	 * @param  instanceId an instance ID
	 * @return the properly assembled portlet ID
	 */
	public static String assemblePortletId(
		String portletId, long userId, String instanceId) {

		String rootPortletId = getRootPortletId(portletId);

		StringBundler sb = new StringBundler(5);

		sb.append(rootPortletId);

		if (userId <= 0) {
			userId = getUserId(portletId);
		}

		if (userId > 0) {
			sb.append(USER_SEPARATOR);
			sb.append(userId);
		}

		if (Validator.isNull(instanceId)) {
			instanceId = getInstanceId(portletId);
		}

		if (Validator.isNotNull(instanceId)) {
			sb.append(INSTANCE_SEPARATOR);
			sb.append(instanceId);
		}

		return sb.toString();
	}

	/**
	 * Returns a properly assembled portlet ID from the parameters passed. If
	 * the portlet ID contains a user ID it will be properly retained. If the
	 * portlet ID contains an instance ID it will be replaced by the instance ID
	 * parameter.
	 *
	 * @param  portletId the portlet ID
	 * @param  instanceId an instance ID
	 * @return the properly assembled portlet ID
	 */
	public static String assemblePortletId(
		String portletId, String instanceId) {

		return assemblePortletId(portletId, 0, instanceId);
	}

	/**
	 * Returns the instance ID of the portlet.
	 *
	 * @param  portletId the portlet ID
	 * @return the instance ID of the portlet
	 */
	public static String getInstanceId(String portletId) {
		int pos = portletId.indexOf(INSTANCE_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		return portletId.substring(pos + INSTANCE_SEPARATOR.length());
	}

	/**
	 * Returns the root portlet ID of the portlet.
	 *
	 * @param  portletId the portlet ID
	 * @return the root portlet ID of the portlet
	 */
	public static String getRootPortletId(String portletId) {
		int x = portletId.indexOf(USER_SEPARATOR);
		int y = portletId.indexOf(INSTANCE_SEPARATOR);

		if ((x == -1) && (y == -1)) {
			return portletId;
		}
		else if (x != -1) {
			return portletId.substring(0, x);
		}

		return portletId.substring(0, y);
	}

	/**
	 * Returns the user ID of the portlet. This only applies when the portlet is
	 * added by a user to a page in customizable mode.
	 *
	 * @param  portletId the portlet ID
	 * @return the user ID of the portlet
	 */
	public static long getUserId(String portletId) {
		int x = portletId.indexOf(USER_SEPARATOR);
		int y = portletId.indexOf(INSTANCE_SEPARATOR);

		if (x == -1) {
			return 0;
		}

		if (y != -1) {
			return GetterUtil.getLong(
				portletId.substring(x + USER_SEPARATOR.length(), y));
		}

		return GetterUtil.getLong(
			portletId.substring(x + USER_SEPARATOR.length()));
	}

	public static boolean hasIdenticalRootPortletId(
		String portletId1, String portletId2) {

		String rootPortletId1 = getRootPortletId(portletId1);
		String rootPortletId2 = getRootPortletId(portletId2);

		return rootPortletId1.equals(rootPortletId2);
	}

	/**
	 * Returns <code>true</code> if the portlet ID contains an instance ID.
	 *
	 * @param  portletId the portlet ID
	 * @return <code>true</code> if the portlet ID contains an instance ID;
	 *         <code>false</code> otherwise
	 */
	public static boolean hasInstanceId(String portletId) {
		return portletId.contains(INSTANCE_SEPARATOR);
	}

	/**
	 * Returns <code>true</code> if the portlet ID contains a user ID.
	 *
	 * @param  portletId the portlet ID
	 * @return <code>true</code> if the portlet ID contains a user ID;
	 *         <code>false</code> otherwise
	 */
	public static boolean hasUserId(String portletId) {
		return portletId.contains(USER_SEPARATOR);
	}

}