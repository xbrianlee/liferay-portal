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

package com.liferay.portal.kernel.concurrent;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class IncreasableEntryTest {

	@Test
	public void testGettingKey() {
		IncreasableEntry<String, Integer> increasableEntry =
			new IntegerIncreasableEntry("test", 0);

		Assert.assertEquals("test", increasableEntry.getKey());
		Assert.assertEquals("test", increasableEntry.getKey());
		Assert.assertEquals("test", increasableEntry.getKey());
	}

	@Test
	public void testIncreaseAndGet() {
		IncreasableEntry<String, Integer> increasableEntry =
			new IntegerIncreasableEntry("test", 0);

		// Simple increase

		Assert.assertTrue(increasableEntry.increase(1));

		// Simple get

		Assert.assertEquals(1, (int)increasableEntry.getValue());

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		// Continue get

		Assert.assertEquals(0, (int)increasableEntry.getValue());
		Assert.assertEquals(0, (int)increasableEntry.getValue());
		Assert.assertEquals(0, (int)increasableEntry.getValue());

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		// Continue increase

		Assert.assertTrue(increasableEntry.increase(1));
		Assert.assertTrue(increasableEntry.increase(2));
		Assert.assertTrue(increasableEntry.increase(3));

		// Check value

		Assert.assertEquals(6, (int)increasableEntry.getValue());

		// Increase after get

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		Assert.assertEquals(0, (int)increasableEntry.getValue());
		Assert.assertFalse(increasableEntry.increase(1));
		Assert.assertEquals(0, (int)increasableEntry.getValue());
	}

	private class IntegerIncreasableEntry
		extends IncreasableEntry<String, Integer> {

		public IntegerIncreasableEntry(String key, Integer value) {
			super(key, value);
		}

		@Override
		public Integer doIncrease(Integer originalValue, Integer deltaValue) {
			return originalValue + deltaValue;
		}

	}

}