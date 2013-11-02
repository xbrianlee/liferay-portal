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

/**
 * @author Michael Young
 */
public enum Isolation {

	COUNTER(TransactionDefinition.ISOLATION_COUNTER),
	DEFAULT(TransactionDefinition.ISOLATION_DEFAULT),
	PORTAL(TransactionDefinition.ISOLATION_PORTAL),
	READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),
	READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),
	REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),
	SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE);

	Isolation(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}

	private int _value;

}