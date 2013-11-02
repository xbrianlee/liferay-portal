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
public class AssetCategoryDisplayTest {

	@Test
	public void testGetPage() {
		AssetCategoryDisplay assetCategoryDisplay = new AssetCategoryDisplay();

		assetCategoryDisplay.setStart(0);
		assetCategoryDisplay.setEnd(20);

		Assert.assertEquals(1, assetCategoryDisplay.getPage());

		assetCategoryDisplay.setStart(20);
		assetCategoryDisplay.setEnd(40);

		Assert.assertEquals(2, assetCategoryDisplay.getPage());

		assetCategoryDisplay.setEnd(0);

		Assert.assertEquals(0, assetCategoryDisplay.getPage());
	}

}