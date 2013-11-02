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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.xml.Element;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Zsolt Berentey
 */
public class MissingReference implements Serializable {

	public MissingReference(Element element) {
		_className = element.attributeValue("class-name");
		_classPK = element.attributeValue("class-pk");
		_displayName = GetterUtil.getString(
			element.attributeValue("display-name"));
		_referrerClassName = element.attributeValue("referrer-class-name");
		_type = GetterUtil.getString(element.attributeValue("type"));

		String referrerDisplayName = GetterUtil.getString(
			element.attributeValue("referrer-display-name"));

		addReferrer(_referrerClassName, referrerDisplayName);
	}

	public void addReferrer(
		String referrerClassName, String referrerDisplayName) {

		_referrers.put(referrerDisplayName, referrerClassName);
	}

	public void addReferrers(Map<String, String> referrers) {
		_referrers.putAll(referrers);
	}

	public String getClassName() {
		return _className;
	}

	public String getClassPK() {
		return _classPK;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public String getReferrerClassName() {
		return _referrerClassName;
	}

	public Set<String> getReferrerDisplayNames() {
		return _referrers.keySet();
	}

	public Map<String, String> getReferrers() {
		return _referrers;
	}

	public String getType() {
		return _type;
	}

	private String _className;
	private String _classPK;
	private String _displayName;
	private String _referrerClassName;
	private Map<String, String> _referrers = new HashMap<String, String>();
	private String _type;

}