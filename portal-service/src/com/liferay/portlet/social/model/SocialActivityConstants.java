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

package com.liferay.portlet.social.model;

/**
 * @author Zsolt Berentey
 * @author Brian Wing Shun Chan
 */
public class SocialActivityConstants {

	public static final int TYPE_ADD_ATTACHMENT = 10006;

	public static final int TYPE_ADD_COMMENT = 10005;

	public static final int TYPE_ADD_VOTE = 10004;

	public static final int TYPE_DELETE = 10000;

	public static final int TYPE_MOVE_ATTACHMENT_TO_TRASH = 10009;

	public static final int TYPE_MOVE_TO_TRASH = 10007;

	public static final int TYPE_RESTORE_ATTACHMENT_FROM_TRASH = 10010;

	public static final int TYPE_RESTORE_FROM_TRASH = 10008;

	public static final int TYPE_SUBSCRIBE = 10002;

	public static final int TYPE_UNSUBSCRIBE = 10003;

	/**
	 * @see com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl#isLogActivity(
	 *      SocialActivity)
	 */
	public static final int TYPE_VIEW = 10001;

}