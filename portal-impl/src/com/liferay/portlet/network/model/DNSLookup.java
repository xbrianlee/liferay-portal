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

package com.liferay.portlet.network.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class DNSLookup implements Serializable {

	public DNSLookup() {
	}

	public DNSLookup(String domain, String results) {
		_domain = domain;
		_results = results;
	}

	public String getDomain() {
		return _domain;
	}

	public String getResults() {
		return _results;
	}

	public void setDomain(String domain) {
		_domain = domain;
	}

	public void setResults(String results) {
		_results = results;
	}

	private String _domain;
	private String _results;

}