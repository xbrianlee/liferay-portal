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

package com.liferay.portal.kernel.backgroundtask;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskResult implements Serializable {

	public static BackgroundTaskResult SUCCESS = new BackgroundTaskResult(
		BackgroundTaskConstants.STATUS_SUCCESSFUL);

	public BackgroundTaskResult() {
	}

	public BackgroundTaskResult(int status) {
		_status = status;
	}

	public BackgroundTaskResult(int status, String statusMessage) {
		_status = status;
		_statusMessage = statusMessage;
	}

	public int getStatus() {
		return _status;
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public boolean isSuccessful() {
		if (_status == BackgroundTaskConstants.STATUS_SUCCESSFUL) {
			return true;
		}

		return false;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	private int _status;
	private String _statusMessage;

}