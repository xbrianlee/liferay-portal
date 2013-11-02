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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

/**
 * @author Raymond Augé
 */
public class URLWrapper {

	public URLWrapper(URL url) {
		_url = url;

		_urlString = _url.toString();

		_hashCode = _urlString.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof URLWrapper)) {
			return false;
		}

		URLWrapper urlWrapper = (URLWrapper)obj;

		if ((_url == urlWrapper._url) ||
			Validator.equals(_urlString, urlWrapper._urlString)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _hashCode;
	}

	private int _hashCode;
	private URL _url;
	private String _urlString;

}