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

package com.liferay.portal.security.permission;

/**
 * Provides utility methods for filtering SQL queries by the user's permissions.
 *
 * @author Raymond Augé
 */
public interface InlineSQLHelper {

	/**
	 * Returns <code>true</code> if the inline SQL helper is enabled.
	 *
	 * @return <code>true</code> if the inline SQL helper is enabled;
	 *         <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Returns <code>true</code> if the inline SQL helper is enabled for the
	 * group.
	 *
	 * @param  groupId the primary key of the group
	 * @return <code>true</code> if the inline SQL helper is enabled for the
	 *         group; <code>false</code> otherwise
	 */
	public boolean isEnabled(long groupId);

	/**
	 * Returns <code>true</code> if the inline SQL helper is enabled for the
	 * groups.
	 *
	 * @param  groupIds the primary keys of the groups
	 * @return <code>true</code> if the inline SQL helper is enabled for the
	 *         groups; <code>false</code> otherwise
	 */
	public boolean isEnabled(long[] groupIds);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  groupId the primary key of the group containing the resources
	 *         (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  groupId the primary key of the group containing the resources
	 *         (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  groupIds the primary keys of the groups containing the resources
	 *         (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  groupIds the primary keys of the groups containing the resources
	 *         (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  groupId the primary key of the group containing the resources
	 *         (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  groupId the primary key of the group containing the resources
	 *         (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  groupIds the primary keys of the groups containing the resources
	 *         (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  groupIds the primary keys of the groups containing the resources
	 *         (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin);

	/**
	 * Modifies the SQL query to only match resources that the user has
	 * permission to view.
	 *
	 * @param  sql the SQL query
	 * @param  className the fully qualified class name of the resources matched
	 *         by the query
	 * @param  classPKField the name of the column containing the resource's
	 *         primary key
	 * @param  userIdField the name of the column containing  the resource
	 *         owner's primary key (optionally <code>null</code>)
	 * @param  groupIdField the name of the column containing the resource's
	 *         group ID (optionally <code>null</code>)
	 * @param  groupIds the primary keys of the groups containing the resources
	 *         (optionally <code>null</code>)
	 * @param  bridgeJoin an additional join clause to insert before the
	 *         permission join (optionally <code>null</code>)
	 * @return the modified SQL query
	 */
	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String bridgeJoin);

}