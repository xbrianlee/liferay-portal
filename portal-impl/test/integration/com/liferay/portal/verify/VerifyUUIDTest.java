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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class VerifyUUIDTest extends BaseVerifyTestCase {

	@Test
	public void testVerifyModel() throws Exception {
		testVerifyModel("Layout", "plid");
	}

	@Test(expected = SQLException.class)
	public void testVerifyModelWithUnknownPKColumnName() throws Exception {
		testVerifyModel("Layout", _UNKNOWN);
	}

	@Test(expected = SQLException.class)
	public void testVerifyUnknownModelWithUnknownPKColumnName()
		throws Exception {

		testVerifyModel(_UNKNOWN, _UNKNOWN);
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyUUID();
	}

	protected void testVerifyModel(String model, String pkColumnName)
		throws Exception {

		VerifyUUID.verifyModel(model, pkColumnName);
	}

	private static final String _UNKNOWN = "Unknown";

}