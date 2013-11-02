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

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class VersionableName
	implements Comparable<VersionableName>, Serializable {

	public static final VersionableName UNKNOWN = new VersionableName(
		"unknown", "unknown");

	public VersionableName(String name) {
		this(name, (Set<String>)null);
	}

	public VersionableName(String name, Set<String> versions) {
		if (Validator.isNull(name)) {
			throw new IllegalArgumentException("Name is null");
		}

		_name = name;
		_versions = versions;
	}

	public VersionableName(String name, String version) {
		this(name, new HashSet<String>());

		addVersion(version);
	}

	public void addVersion(String version) {
		if (version == null) {
			return;
		}

		if (_versions == null) {
			_versions = new TreeSet<String>();
		}

		_versions.add(version);
	}

	@Override
	public int compareTo(VersionableName versionableName) {
		return StringUtil.toUpperCase(_name).compareTo(
			StringUtil.toUpperCase(versionableName.getName()));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VersionableName)) {
			return false;
		}

		VersionableName versionableName = (VersionableName)obj;

		if (Validator.equals(_name, versionableName._name)) {
			return true;
		}

		return false;
	}

	public String getName() {
		return _name;
	}

	public Set<String> getVersions() {
		if (_versions == null) {
			return Collections.emptySet();
		}

		return Collections.unmodifiableSet(_versions);
	}

	@Override
	public int hashCode() {
		if (_name != null) {
			return _name.hashCode();
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{name=");
		sb.append(_name);
		sb.append(", versions=");
		sb.append(_versions);
		sb.append("}");

		return sb.toString();
	}

	private String _name;
	private Set<String> _versions;

}