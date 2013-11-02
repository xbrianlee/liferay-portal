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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class MessageStatus implements Serializable {

	public long getDuration() {
		return _endTime - _startTime;
	}

	public String getExceptionMessage() {
		return _exceptionMessage;
	}

	public String getExceptionStackTrace() {
		return _exceptionStackTrace;
	}

	public Object getPayload() {
		return _payload;
	}

	public boolean hasException() {
		if (_exceptionStackTrace != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setException(Exception e) {
		_exceptionMessage = e.getMessage();
		_exceptionStackTrace = StackTraceUtil.getStackTrace(e);
	}

	public void setPayload(Object payload) {
		_payload = payload;
	}

	public void startTimer() {
		_startTime = System.currentTimeMillis();
	}

	public void stopTimer() {
		_endTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{startTime=");
		sb.append(_startTime);
		sb.append(", endTime=");
		sb.append(_endTime);
		sb.append(", payload=");
		sb.append(_payload);
		sb.append(", errorMessage=");
		sb.append(_exceptionMessage);
		sb.append(", errorStackTrace=");
		sb.append(_exceptionStackTrace);
		sb.append("}");

		return sb.toString();
	}

	private long _endTime;
	private String _exceptionMessage;
	private String _exceptionStackTrace;
	private Object _payload;
	private long _startTime;

}