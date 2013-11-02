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
public enum Propagation {

	MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),
	NEVER(TransactionDefinition.PROPAGATION_NEVER),
	NESTED(TransactionDefinition.PROPAGATION_NESTED),
	NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),
	REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),
	REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),
	SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS);

	Propagation(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}

	private int _value;

}