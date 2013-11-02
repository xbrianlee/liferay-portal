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

package com.liferay.portal.kernel.plugin;

import com.liferay.portal.kernel.util.Accessor;

import java.io.Serializable;

/**
 * @author Jorge Ferrer
 */
public class License implements Serializable {

	public static final Accessor<License, String> NAME_ACCESSOR =

		new Accessor<License, String>() {

			@Override
			public String get(License license) {
				return license.getName();
			}

		};

	public String getName() {
		return _name;
	}

	public String getUrl() {
		return _url;
	}

	public boolean isOsiApproved() {
		return _osiApproved;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOsiApproved(boolean osiApproved) {
		_osiApproved = osiApproved;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String _name;
	private boolean _osiApproved;
	private String _url;

}