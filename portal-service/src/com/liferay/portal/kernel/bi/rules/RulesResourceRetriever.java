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

package com.liferay.portal.kernel.bi.rules;

import com.liferay.portal.kernel.resource.ResourceRetriever;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class RulesResourceRetriever implements Serializable {

	public RulesResourceRetriever(ResourceRetriever resourceRetriever) {
		this(resourceRetriever, null);
	}

	public RulesResourceRetriever(
		ResourceRetriever resourceRetriever, String rulesLanguage) {

		if (resourceRetriever != null) {
			_resourceRetrievers.add(resourceRetriever);
		}

		_rulesLanguage = rulesLanguage;
	}

	public RulesResourceRetriever(String rulesLanguage) {
		this(null, rulesLanguage);
	}

	public void addResourceRetriever(ResourceRetriever resourceRetriever) {
		_resourceRetrievers.add(resourceRetriever);
	}

	public Set<ResourceRetriever> getResourceRetrievers() {
		return _resourceRetrievers;
	}

	public String getRulesLanguage() {
		return _rulesLanguage;
	}

	private Set<ResourceRetriever> _resourceRetrievers =
		new HashSet<ResourceRetriever>();
	private String _rulesLanguage;

}