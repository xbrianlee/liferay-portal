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

package com.liferay.mail.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Filter implements Serializable {

	public Filter() {
	}

	public Filter(String emailAddress, String folder) {
		_emailAddress = emailAddress;
		_folder = folder;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFolder() {
		return _folder;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFolder(String folder) {
		_folder = folder;
	}

	private String _emailAddress;
	private String _folder;

}