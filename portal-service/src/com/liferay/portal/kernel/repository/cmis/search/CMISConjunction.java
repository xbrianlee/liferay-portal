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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class CMISConjunction extends CMISJunction {

	@Override
	public String toQueryFragment() {
		if (isEmpty()) {
			return StringPool.BLANK;
		}

		List<CMISCriterion> cmisCriterions = list();

		StringBundler sb = new StringBundler(cmisCriterions.size() * 2 + 1);

		if (cmisCriterions.size() > 1) {
			sb.append(StringPool.OPEN_PARENTHESIS);
		}

		for (int i = 0; i < cmisCriterions.size(); i++) {
			CMISCriterion cmisCriterion = cmisCriterions.get(i);

			if (i != 0) {
				sb.append(" AND ");
			}

			sb.append(cmisCriterion.toQueryFragment());
		}

		if (cmisCriterions.size() > 1) {
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		return sb.toString();
	}

}