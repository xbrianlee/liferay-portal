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
public abstract class AbstractStatementCallback extends Statement {

	public AbstractStatementCallback(
		Statement statement, TestContextHandler testContextHandler) {

		_statement = statement;
		_testContextHandler = testContextHandler;
	}

	@Override
	public abstract void evaluate() throws Throwable;

	public Statement getStatement() {
		return _statement;
	}

	public TestContextHandler getTestContextHandler() {
		return _testContextHandler;
	}

	private Statement _statement;
	private TestContextHandler _testContextHandler;

}