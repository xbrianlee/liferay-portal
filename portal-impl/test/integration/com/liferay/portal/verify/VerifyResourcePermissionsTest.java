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
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.CompanyTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class VerifyResourcePermissionsTest extends BaseVerifyTestCase {

	@Test
	public void testVerifyMoreThanOneCompany() throws Exception {
		verify(false);
	}

	@Test
	public void testVerifyOneCompany() throws Exception {
		verify(true);
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyResourcePermissions();
	}

	protected void verify(boolean oneCompany) throws Exception {
		Company company = null;

		if (!oneCompany) {
			company = CompanyTestUtil.addCompany();
		}

		doVerify();

		if (!oneCompany) {
			if (company != null) {
				CompanyLocalServiceUtil.deleteCompany(company);
			}
		}
	}

}