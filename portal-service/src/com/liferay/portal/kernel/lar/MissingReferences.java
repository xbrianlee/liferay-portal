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

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Julio Camarero
 */
public class MissingReferences implements Serializable {

	public void add(MissingReference missingReference) {
		String type = missingReference.getType();

		if (type.equals(PortletDataContext.REFERENCE_TYPE_DEPENDENCY)) {
			add(_dependencyMissingReferences, missingReference);
		}
		else if (type.equals(PortletDataContext.REFERENCE_TYPE_WEAK)) {
			add(_weakMissingReferences, missingReference);
		}
	}

	public Map<String, MissingReference> getDependencyMissingReferences() {
		return _dependencyMissingReferences;
	}

	public Map<String, MissingReference> getWeakMissingReferences() {
		return _weakMissingReferences;
	}

	protected void add(
		Map<String, MissingReference> missingReferences,
		MissingReference missingReference) {

		String key = null;

		String type = missingReference.getType();

		if (type.equals(PortletDataContext.REFERENCE_TYPE_DEPENDENCY)) {
			key = missingReference.getDisplayName();
		}
		else if (type.equals(PortletDataContext.REFERENCE_TYPE_WEAK)) {
			key = missingReference.getReferrerClassName();
		}

		MissingReference existingMissingReference = missingReferences.get(key);

		if (existingMissingReference != null) {
			existingMissingReference.addReferrers(
				missingReference.getReferrers());
		}
		else {
			missingReferences.put(key, missingReference);
		}
	}

	private Map<String, MissingReference> _dependencyMissingReferences =
		new HashMap<String, MissingReference>();
	private Map<String, MissingReference> _weakMissingReferences =
		new HashMap<String, MissingReference>();

}