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

package com.liferay.portal.kernel.search;

/**
 * @author Michael C. Han
 */
public class WeightedWord implements Comparable<WeightedWord> {

	public WeightedWord(String word, float weight) {
		_weight = weight;
		_word = word;
	}

	@Override
	public int compareTo(WeightedWord weightedWord) {
		if (getWeight() < weightedWord.getWeight()) {
			return -1;
		}
		else if (getWeight() == weightedWord.getWeight()) {
			return 0;
		}

		return 1;
	}

	public float getWeight() {
		return _weight;
	}

	public String getWord() {
		return _word;
	}

	public void setWeight(float weight) {
		_weight = weight;
	}

	private float _weight;
	private String _word;

}