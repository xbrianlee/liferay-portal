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

package com.liferay.portal.monitoring.statistics;

import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSample;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class BaseDataSample implements DataSample, Serializable {

	public void capture(RequestStatus requestStatus) {
		if (_stopWatch != null) {
			_stopWatch.stop();

			_duration = _stopWatch.getTime();
		}

		if ((_timeout > 0) && (_duration >= _timeout) &&
			(requestStatus != RequestStatus.ERROR)) {

			_requestStatus = RequestStatus.TIMEOUT;
		}
		else {
			_requestStatus = requestStatus;
		}
	}

	public Map<String, String> getAttributes() {
		return _attributes;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public long getDuration() {
		return _duration;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getNamespace() {
		return _namespace;
	}

	@Override
	public RequestStatus getRequestStatus() {
		return _requestStatus;
	}

	public long getTimeout() {
		return _timeout;
	}

	@Override
	public String getUser() {
		return _user;
	}

	public void prepare() {
		if (_stopWatch == null) {
			_stopWatch = new StopWatch();
		}

		_stopWatch.start();
	}

	public void setAttributes(Map<String, String> attributes) {
		_attributes = attributes;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public void setTimeout(long timeout) {
		_timeout = timeout;
	}

	public void setUser(String user) {
		_user = user;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{attributes=");
		sb.append(_attributes);
		sb.append(", companyId=");
		sb.append(_companyId);
		sb.append(", description=");
		sb.append(_description);
		sb.append(", duration=");
		sb.append(_duration);
		sb.append(", name=");
		sb.append(_name);
		sb.append(", namespace=");
		sb.append(_namespace);
		sb.append(", requestStatus=");
		sb.append(_requestStatus);
		sb.append(", stopWatch=");
		sb.append(_stopWatch);
		sb.append(", timeout=");
		sb.append(_timeout);
		sb.append(", user=");
		sb.append(_user);
		sb.append("}");

		return sb.toString();
	}

	private Map<String, String> _attributes;
	private long _companyId;
	private String _description;
	private long _duration;
	private String _name;
	private String _namespace;
	private RequestStatus _requestStatus;
	private transient StopWatch _stopWatch;
	private long _timeout = -1;
	private String _user;

}