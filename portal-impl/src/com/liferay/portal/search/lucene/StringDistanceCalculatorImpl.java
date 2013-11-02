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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.search.StringDistanceCalculator;

import org.apache.lucene.search.spell.StringDistance;

/**
 * @author Michael C. Han
 */
public class StringDistanceCalculatorImpl implements StringDistanceCalculator {

	@Override
	public float getDistance(String string1, String string2) {
		return _stringDistance.getDistance(string1, string2);
	}

	public void setStringDistance(StringDistance stringDistance) {
		_stringDistance = stringDistance;
	}

	private StringDistance _stringDistance;

}