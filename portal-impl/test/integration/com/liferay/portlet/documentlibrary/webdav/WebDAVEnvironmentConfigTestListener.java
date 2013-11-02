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

package com.liferay.portlet.documentlibrary.webdav;

import com.liferay.portal.kernel.test.TestContext;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.webdav.methods.Method;
import com.liferay.portal.test.EnvironmentExecutionTestListener;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

/**
 * @author Miguel Pastor
 */
public class WebDAVEnvironmentConfigTestListener
	extends EnvironmentExecutionTestListener {

	@Override
	public void runAfterClass(TestContext testContext) {
		_baseWebDAVTestCase.service(Method.DELETE, "", null, null);

		super.runAfterClass(testContext);
	}

	@Override
	public void runBeforeClass(TestContext testContext) {
		super.runBeforeClass(testContext);

		Tuple tuple = _baseWebDAVTestCase.service(Method.MKCOL, "", null, null);

		int statusCode = BaseWebDAVTestCase.getStatusCode(tuple);

		if (statusCode == HttpServletResponse.SC_METHOD_NOT_ALLOWED) {
			_baseWebDAVTestCase.service(Method.DELETE, "", null, null);

			tuple = _baseWebDAVTestCase.service(Method.MKCOL, "", null, null);

			statusCode = BaseWebDAVTestCase.getStatusCode(tuple);

			Assert.assertEquals(HttpServletResponse.SC_CREATED, statusCode);
		}
	}

	private BaseWebDAVTestCase _baseWebDAVTestCase = new BaseWebDAVTestCase();

}