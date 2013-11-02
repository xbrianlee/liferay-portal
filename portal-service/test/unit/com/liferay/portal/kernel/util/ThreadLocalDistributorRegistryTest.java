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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class ThreadLocalDistributorRegistryTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testThreadLocalDistributorRegistry() {
		ThreadLocalDistributor[] threadLocalDistributors =
			ThreadLocalDistributorRegistry.getThreadLocalDistributors();

		Assert.assertEquals(0, threadLocalDistributors.length);

		ThreadLocalDistributor threadLocalDistributor1 =
			new ThreadLocalDistributor();
		ThreadLocalDistributor threadLocalDistributor2 =
			new ThreadLocalDistributor();

		Assert.assertEquals(
			0,
			ThreadLocalDistributorRegistry.addThreadLocalDistributor(
				threadLocalDistributor1));
		Assert.assertEquals(
			1,
			ThreadLocalDistributorRegistry.addThreadLocalDistributor(
				threadLocalDistributor2));
		Assert.assertSame(
			threadLocalDistributor1,
			ThreadLocalDistributorRegistry.getThreadLocalDistributor(0));
		Assert.assertSame(
			threadLocalDistributor2,
			ThreadLocalDistributorRegistry.getThreadLocalDistributor(1));
	}

}