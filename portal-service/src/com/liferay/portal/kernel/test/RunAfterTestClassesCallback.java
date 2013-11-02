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

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

/**
 * @author Miguel Pastor
 */
public class RunAfterTestClassesCallback extends AbstractStatementCallback {

	public RunAfterTestClassesCallback(
		Statement statement, TestContextHandler testContextHandler) {

		super(statement, testContextHandler);
	}

	@Override
	public void evaluate() throws Throwable {
		List<Throwable> throwables = new ArrayList<Throwable>();

		Statement statement = getStatement();

		if ( statement != null) {
			try {
				statement.evaluate();
			}
			catch (Throwable t) {
				throwables.add(t);
			}
		}

		try {
			TestContextHandler testContextHandler = getTestContextHandler();

			testContextHandler.runAfterTestClasses();
		}
		catch (Exception e) {
			throwables.add(e);
		}

		if (!throwables.isEmpty()) {
			throw new MultipleFailureException(throwables);
		}
	}

}