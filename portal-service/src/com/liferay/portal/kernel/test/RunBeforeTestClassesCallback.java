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

package com.liferay.portal.kernel.test;

import org.junit.runners.model.Statement;

/**
 * @author Miguel Pastor
 */
public class RunBeforeTestClassesCallback extends AbstractStatementCallback {

	public RunBeforeTestClassesCallback(
		Statement statement, TestContextHandler testContextHandler) {

		super(statement, testContextHandler);
	}

	@Override
	public void evaluate() throws Throwable {
		TestContextHandler testContextHandler = getTestContextHandler();

		testContextHandler.runBeforeTestClasses();

		Statement statement = getStatement();

		if (statement != null) {
			statement.evaluate();
		}
	}

}