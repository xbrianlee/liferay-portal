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

package com.liferay.portal.scripting;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.scripting.ScriptingExecutor;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Miguel Pastor
 */
@PrepareForTest(SingleVMPoolUtil.class)
public abstract class ScriptingExecutorTestCase extends PowerMockito {

	public abstract String getScriptExtension();

	public abstract ScriptingExecutor getScriptingExecutor();

	@Before
	public void setUp() {
		mockStatic(SingleVMPoolUtil.class);

		when(
			SingleVMPoolUtil.getCache(Mockito.anyString())
		).thenReturn(
			_portalCache
		);

		_scriptingExecutor = getScriptingExecutor();
	}

	@After
	public void tearDown() {
		verifyStatic();
	}

	@Test
	public void testBindingInputVariables() throws ScriptingException {
		Map<String, Object> inputObjects = new HashMap<String, Object>();

		inputObjects.put("variable", "string");

		Set<String> outputNames = Collections.emptySet();

		execute(inputObjects, outputNames, "binding-input");
	}

	@Test
	public void testSimpleScript() throws ScriptingException {
		Map<String, Object> inputObjects = Collections.emptyMap();
		Set<String> outputNames = Collections.emptySet();

		execute(inputObjects, outputNames, "simple");
	}

	protected Map<String, Object> execute(
			Map<String, Object> inputObjects, Set<String> outputNames,
			String fileName)
		throws ScriptingException {

		String script = getScript(fileName + getScriptExtension());

		return _scriptingExecutor.eval(null, inputObjects, outputNames, script);
	}

	protected String getScript(String name) {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + name);

		String script = null;

		try {
			script = StringUtil.read(inputStream);
		}
		catch (IOException ioe) {
			Assert.fail("Unable to read " + name);
		}

		return script;
	}

	@Mock
	private PortalCache<Serializable, Object> _portalCache;

	private ScriptingExecutor _scriptingExecutor;

}