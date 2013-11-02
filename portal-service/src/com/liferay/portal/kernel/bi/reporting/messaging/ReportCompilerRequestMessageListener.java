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

package com.liferay.portal.kernel.bi.reporting.messaging;

import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportEngine;
import com.liferay.portal.kernel.bi.reporting.ReportGenerationException;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

/**
 * @author Michael C. Han
 */
public class ReportCompilerRequestMessageListener extends BaseMessageListener {

	public void setReportEngine(ReportEngine reportEngine) {
		_reportEngine = reportEngine;
	}

	public void setReportResultContainer(
		ReportResultContainer reportResultContainer) {

		_reportResultContainer = reportResultContainer;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		ReportRequest reportRequest = (ReportRequest)message.getPayload();

		ReportDesignRetriever reportDesignRetriever =
			reportRequest.getReportDesignRetriever();

		ReportResultContainer reportResultContainer =
			_reportResultContainer.clone(reportDesignRetriever.getReportName());

		try {
			_reportEngine.compile(reportRequest);
		}
		catch (ReportGenerationException rge) {
			_log.error("Unable to compile report", rge);

			reportResultContainer.setReportGenerationException(rge);
		}
		finally {
			Message responseMessage = MessageBusUtil.createResponseMessage(
				message, reportResultContainer);

			responseMessage.setPayload(reportResultContainer);

			MessageBusUtil.sendMessage(
				responseMessage.getDestinationName(), responseMessage);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ReportCompilerRequestMessageListener.class);

	private ReportEngine _reportEngine;
	private ReportResultContainer _reportResultContainer;

}