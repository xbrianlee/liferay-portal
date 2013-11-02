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

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.process.ProcessExecutor;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.NewClassLoaderJUnitTestRunner;

import java.util.concurrent.ConcurrentMap;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(NewClassLoaderJUnitTestRunner.class)
public class SPIUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testIsNotSPI() {
		Assert.assertFalse(SPIUtil.isSPI());

		try {
			SPIUtil.getSPI();

			Assert.fail();
		}
		catch (IllegalStateException ise) {
			Assert.assertEquals(
				"Current process is not an SPI instance", ise.getMessage());
		}
	}

	@Test
	public void testIsSPI() {
		MockSPI mockSPI = new MockSPI();

		ConcurrentMap<String, Object> attributes =
			ProcessExecutor.ProcessContext.getAttributes();

		attributes.put(SPI.SPI_INSTANCE_PUBLICATION_KEY, mockSPI);

		Assert.assertTrue(SPIUtil.isSPI());
		Assert.assertSame(mockSPI, SPIUtil.getSPI());
	}

}