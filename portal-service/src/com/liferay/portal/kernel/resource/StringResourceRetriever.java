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

package com.liferay.portal.kernel.resource;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;

import java.io.InputStream;

/**
 * @author Shuyang Zhou
 */
public class StringResourceRetriever implements ResourceRetriever {

	public StringResourceRetriever(String resource) {
		_inputStream = new UnsyncByteArrayInputStream(resource.getBytes());
	}

	@Override
	public InputStream getInputStream() {
		return _inputStream;
	}

	private InputStream _inputStream;

}