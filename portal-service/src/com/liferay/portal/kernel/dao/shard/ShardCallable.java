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

package com.liferay.portal.kernel.dao.shard;

import java.util.concurrent.Callable;

/**
 * @author Alexander Chow
 */
public abstract class ShardCallable<V> implements Callable<V> {

	public ShardCallable(long companyId) {
		_companyId = companyId;
	}

	@Override
	public V call() throws Exception {
		ShardUtil.pushCompanyService(_companyId);

		try {
			return doCall();
		}
		finally {
			ShardUtil.popCompanyService();
		}
	}

	protected abstract V doCall() throws Exception;

	private long _companyId;

}