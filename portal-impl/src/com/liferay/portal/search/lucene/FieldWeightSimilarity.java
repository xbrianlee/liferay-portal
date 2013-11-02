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

import org.apache.lucene.search.DefaultSimilarity;

/**
 * @author Daeyoung Song
 */
public class FieldWeightSimilarity extends DefaultSimilarity {

	@Override
	public float coord(int overlap, int maxOverlap) {
		return 1;
	}

	@Override
	public float idf(int docFreq, int numDocs) {
		return 1;
	}

	@Override
	public float queryNorm(float sumOfSquaredWeights) {
		return 1;
	}

}