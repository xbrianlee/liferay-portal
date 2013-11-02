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

package com.liferay.portal.kernel.repository.cmis.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public abstract class CMISJunction implements CMISCriterion {

	public CMISJunction() {
		_cmisCriterions = new ArrayList<CMISCriterion>();
	}

	public void add(CMISCriterion cmisCriterion) {
		_cmisCriterions.add(cmisCriterion);
	}

	public boolean isEmpty() {
		return _cmisCriterions.isEmpty();
	}

	public List<CMISCriterion> list() {
		return _cmisCriterions;
	}

	@Override
	public abstract String toQueryFragment();

	private List<CMISCriterion> _cmisCriterions;

}