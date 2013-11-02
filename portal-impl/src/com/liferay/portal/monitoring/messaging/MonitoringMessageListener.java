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

package com.liferay.portal.monitoring.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.monitoring.statistics.DataSample;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleProcessor;

import java.util.List;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MonitoringMessageListener extends BaseMessageListener {

	public void setDataSampleProcessor(
		DataSampleProcessor<DataSample> dataSampleProcessor) {

		_dataSampleProcessor = dataSampleProcessor;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<DataSample> dataSamples = (List<DataSample>)message.getPayload();

		if ((dataSamples != null) && !dataSamples.isEmpty()) {
			for (DataSample dataSample : dataSamples) {
				_dataSampleProcessor.processDataSample(dataSample);
			}
		}
	}

	private DataSampleProcessor<DataSample> _dataSampleProcessor;

}