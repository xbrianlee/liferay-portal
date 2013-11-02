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

package com.liferay.portal.kernel.transaction;

import java.sql.Connection;

/**
 * @author Michael Young
 */
public interface TransactionDefinition {

	public static final int ISOLATION_COUNTER = -3;

	public static final int ISOLATION_DEFAULT = -1;

	public static final int ISOLATION_PORTAL = -2;

	public static final int ISOLATION_READ_COMMITTED =
		Connection.TRANSACTION_READ_COMMITTED;

	public static final int ISOLATION_READ_UNCOMMITTED =
		Connection.TRANSACTION_READ_UNCOMMITTED;

	public static final int ISOLATION_REPEATABLE_READ =
		Connection.TRANSACTION_REPEATABLE_READ;

	public static final int ISOLATION_SERIALIZABLE =
		Connection.TRANSACTION_SERIALIZABLE;

	public static final int PROPAGATION_MANDATORY = 2;

	public static final int PROPAGATION_NESTED = 6;

	public static final int PROPAGATION_NEVER = 5;

	public static final int PROPAGATION_NOT_SUPPORTED = 4;

	public static final int PROPAGATION_REQUIRED = 0;

	public static final int PROPAGATION_REQUIRES_NEW = 3;

	public static final int PROPAGATION_SUPPORTS = 1;

	public static final int TIMEOUT_DEFAULT = -1;

}