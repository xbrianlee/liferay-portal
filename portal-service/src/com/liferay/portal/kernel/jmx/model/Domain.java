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

package com.liferay.portal.kernel.jmx.model;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class Domain implements Serializable {

	public Domain(String domainName) {
		_domainName = domainName;
	}

	public Domain(String domainName, List<MBean> mBeans) {
		_domainName = domainName;
		_mBeans = mBeans;
		_loaded = true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Domain)) {
			return false;
		}

		Domain domain = (Domain)obj;

		if (Validator.equals(_domainName, domain._domainName)) {
			return true;
		}

		return false;
	}

	public String getDomainName() {
		return _domainName;
	}

	public List<MBean> getMBeans() {
		return _mBeans;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_domainName);

		return hashCode.toHashCode();
	}

	public boolean isLoaded() {
		return _loaded;
	}

	private String _domainName;
	private boolean _loaded;
	private List<MBean> _mBeans;

}