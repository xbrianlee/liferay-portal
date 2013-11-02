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

package com.liferay.portal.security.permission.comparator;

import com.liferay.portal.security.permission.ResourceActionsUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Jorge Ferrer
 */
public class ModelResourceWeightComparator
	implements Comparator<String>, Serializable {

	@Override
	public int compare(String resource1, String resource2) {
		double weight1 = ResourceActionsUtil.getModelResourceWeight(resource1);
		double weight2 = ResourceActionsUtil.getModelResourceWeight(resource2);

		return Double.compare(weight1, weight2);
	}

}