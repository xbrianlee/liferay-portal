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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * Checks permissions with respect to subscriptions.
 *
 * @author Mate Thurzo
 * @author Raymond Augé
 */
public interface SubscriptionPermission {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #check(PermissionChecker,
	 *             String, long, String, long)}
	 */
	public void check(
			PermissionChecker permissionChecker, String className, long classPK)
		throws PortalException, SystemException;

	/**
	 * Checks if the user has permission to subscribe to the subscription entity
	 * and receive notifications about the inferred entity.
	 *
	 * @param  permissionChecker the permission checker
	 * @param  subscriptionClassName the class name of the subscribed entity
	 * @param  subscriptionClassPK the primary key of the subscribed entity
	 * @param  inferredClassName the class name of the inferred entity
	 *         (optionally <code>null</code> if the the subscribed entity is the
	 *         inferred entity).
	 * @param  inferredClassPK the primary key of the inferred entity.
	 * @throws PortalException if the user did not have permission to view the
	 *         inferred entity or receive notifications about the subscribed
	 *         entity, or if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 * @see    #contains(PermissionChecker, String, long, String, long)
	 */
	public void check(
			PermissionChecker permissionChecker, String subscriptionClassName,
			long subscriptionClassPK, String inferredClassName,
			long inferredClassPK)
		throws PortalException, SystemException;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #contains(PermissionChecker,
	 *             String, long, String, long)}
	 */
	public boolean contains(
			PermissionChecker permissionChecker, String className, long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns <code>true</code> if the user has permission to subscribe to the
	 * subscribed entity and receive notifications about the inferred entity.
	 *
	 * <p>
	 * If the subscribed entity is a container and if an inferred entity
	 * (presumably within the container) is specified, a view permission check
	 * is performed on the inferred entity. The inferred entity is the subject
	 * of the notification. A failed view check on the inferred entity
	 * short-circuits further permission checks and prevents notifications from
	 * being sent. Checking the view permission on the inferred entity is useful
	 * for enforcing permissions for private subtrees within larger container
	 * entities to which the user is subscribed.
	 * </p>
	 *
	 * <p>
	 * If the subscribed entity and the inferred entity are the same, then no
	 * inferred entity needs to be specified. Without any inferred entity
	 * specified only the subscription check on the subscribed entity is
	 * performed.
	 * </p>
	 *
	 * @param  permissionChecker the permission checker
	 * @param  subscriptionClassName the class name of the subscribed entity
	 * @param  subscriptionClassPK the primary key of the subscribed entity
	 * @param  inferredClassName the class name of the inferred entity if the
	 *         subscribed entity is a container entity
	 * @param  inferredClassPK the primary key of the inferred entity if the
	 *         subscribed entity is a container entity
	 * @return <code>true</code> if the user has permission to subscribe to the
	 *         subscribed entity and receive notifications about the inferred
	 *         entity; <code>false</code> otherwise
	 * @throws PortalException if the user did not have permission to view the
	 *         inferred entity or receive notifications about it via the
	 *         subscribed entity, or if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public boolean contains(
			PermissionChecker permissionChecker, String subscriptionClassName,
			long subscriptionClassPK, String inferredClassName,
			long inferredClassPK)
		throws PortalException, SystemException;

}