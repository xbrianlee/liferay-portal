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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.poller.PollerException;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

/**
 * @author Alexander Chow
 */
public class ShardPollerProcessorWrapper implements PollerProcessor {

	public ShardPollerProcessorWrapper(PollerProcessor pollerProcessor) {
		_pollerProcessor = pollerProcessor;
	}

	@Override
	public void receive(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws PollerException {

		try {
			ShardUtil.pushCompanyService(pollerRequest.getCompanyId());

			_pollerProcessor.receive(pollerRequest, pollerResponse);
		}
		finally {
			ShardUtil.popCompanyService();
		}
	}

	@Override
	public void send(PollerRequest pollerRequest) throws PollerException {
		try {
			ShardUtil.pushCompanyService(pollerRequest.getCompanyId());

			_pollerProcessor.send(pollerRequest);
		}
		finally {
			ShardUtil.popCompanyService();
		}
	}

	private PollerProcessor _pollerProcessor;

}