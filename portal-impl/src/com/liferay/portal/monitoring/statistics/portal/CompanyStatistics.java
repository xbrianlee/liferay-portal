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

package com.liferay.portal.monitoring.statistics.portal;

import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleProcessor;
import com.liferay.portal.kernel.monitoring.statistics.RequestStatistics;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.CompanyLocalService;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class CompanyStatistics
	implements DataSampleProcessor<PortalRequestDataSample> {

	public CompanyStatistics() {
		_companyId = CompanyConstants.SYSTEM;
		_webId = CompanyConstants.SYSTEM_STRING;
		_requestStatistics = new RequestStatistics(_webId);
	}

	public CompanyStatistics(
		CompanyLocalService companyLocalService, String webId) {

		try {
			Company company = companyLocalService.getCompanyByWebId(webId);

			_companyId = company.getCompanyId();
			_webId = webId;
			_requestStatistics = new RequestStatistics(_webId);
		}
		catch (Exception e) {
			throw new IllegalStateException(
				"Unable to get company with web id " + webId, e);
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getMaxTime() {
		return _maxTime;
	}

	public long getMinTime() {
		return _minTime;
	}

	public RequestStatistics getRequestStatistics() {
		return _requestStatistics;
	}

	public long getStartTime() {
		return _startTime;
	}

	public long getUptime() {
		return System.currentTimeMillis() - _startTime;
	}

	public String getWebId() {
		return _webId;
	}

	@Override
	public void processDataSample(
		PortalRequestDataSample portalRequestDataSample) {

		if (portalRequestDataSample.getCompanyId() != _companyId) {
			return;
		}

		RequestStatus requestStatus =
			portalRequestDataSample.getRequestStatus();

		if (requestStatus.equals(RequestStatus.ERROR)) {
			_requestStatistics.incrementError();
		}
		else if (requestStatus.equals(RequestStatus.SUCCESS)) {
			_requestStatistics.incrementSuccessDuration(
				portalRequestDataSample.getDuration());
		}
		else if (requestStatus.equals(RequestStatus.TIMEOUT)) {
			_requestStatistics.incrementTimeout();
		}

		long duration = portalRequestDataSample.getDuration();

		if (_maxTime < duration) {
			_maxTime = duration;
		}
		else if (_minTime > duration) {
			_minTime = duration;
		}
	}

	public void reset() {
		_maxTime = 0;
		_minTime = 0;

		_requestStatistics.reset();
	}

	private long _companyId;
	private long _maxTime;
	private long _minTime;
	private RequestStatistics _requestStatistics;
	private long _startTime = System.currentTimeMillis();
	private String _webId;

}