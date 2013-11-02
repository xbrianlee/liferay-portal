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

package com.liferay.portal.repository.cmis;

import com.liferay.portal.kernel.repository.cmis.Session;

import java.util.Set;

import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;

/**
 * @author Alexander Chow
 */
public class SessionImpl implements Session {

	public SessionImpl(
		org.apache.chemistry.opencmis.client.api.Session session) {

		_session = session;
	}

	public org.apache.chemistry.opencmis.client.api.Session getSession() {
		return _session;
	}

	@Override
	public void setDefaultContext(
		Set<String> filter, boolean includeAcls,
		boolean includeAllowableActions, boolean includePolicies,
		String includeRelationships, Set<String> renditionFilter,
		boolean includePathSegments, String orderBy, boolean cacheEnabled,
		int maxItemsPerPage) {

		IncludeRelationships includeRelationshipsObj = null;

		if (includeRelationships != null) {
			includeRelationshipsObj = IncludeRelationships.fromValue(
				includeRelationships);
		}

		OperationContext operationContext = new OperationContextImpl(
			filter, includeAcls, includeAllowableActions, includePolicies,
			includeRelationshipsObj, renditionFilter, includePathSegments,
			orderBy, cacheEnabled, maxItemsPerPage);

		_session.setDefaultContext(operationContext);
	}

	private org.apache.chemistry.opencmis.client.api.Session _session;

}