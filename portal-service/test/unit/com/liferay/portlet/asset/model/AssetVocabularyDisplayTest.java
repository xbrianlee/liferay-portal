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

package com.liferay.portlet.asset.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class AssetVocabularyDisplayTest {

	@Test
	public void testGetPage() {
		AssetVocabularyDisplay assetVocabularyDisplay =
			new AssetVocabularyDisplay();

		assetVocabularyDisplay.setStart(0);
		assetVocabularyDisplay.setEnd(20);

		Assert.assertEquals(1, assetVocabularyDisplay.getPage());

		assetVocabularyDisplay.setStart(20);
		assetVocabularyDisplay.setEnd(40);

		Assert.assertEquals(2, assetVocabularyDisplay.getPage());

		assetVocabularyDisplay.setEnd(0);

		Assert.assertEquals(0, assetVocabularyDisplay.getPage());
	}

}