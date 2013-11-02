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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockHttpSession;

/**
 * @author Sergio González
 */
public class ProgressTrackerTest {

	@Before
	public void setUp() throws Exception {
		_mockHttpSession = new MockHttpSession();

		_mockInstallProcess = new MockInstallProcess(_mockHttpSession);
	}

	@After
	public void tearDown() throws Exception {
		_mockInstallProcess.finish();
	}

	@Test
	public void testGetMessage() throws Exception {
		_mockInstallProcess.initialize();

		ProgressTracker progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertEquals(StringPool.BLANK, progressTracker.getMessage());

		_mockInstallProcess.download();

		progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertEquals("downloading", progressTracker.getMessage());
	}

	@Test
	public void testGetPercent() throws Exception {
		_mockInstallProcess.initialize();

		ProgressTracker progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertEquals(0, progressTracker.getPercent());

		_mockInstallProcess.download();
		_mockInstallProcess.copy();

		progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertEquals(progressTracker.getPercent(), 50);
	}

	@Test
	public void testGetStatus() throws Exception {
		_mockInstallProcess.initialize();

		ProgressTracker progressTracker = getAttribute(ProgressTracker.PERCENT);

		_mockInstallProcess.download();
		_mockInstallProcess.copy();

		Assert.assertEquals(
			ProgressStatusConstants.COPYING, progressTracker.getStatus());
	}

	@Test
	public void testInitializeAndFinish() throws Exception {
		_mockInstallProcess.initialize();

		ProgressTracker progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertNotNull(progressTracker);

		_mockInstallProcess.finish();

		progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertNull(progressTracker);
	}

	@Test
	public void testInitialStatus() throws Exception {
		_mockInstallProcess.initialize();

		ProgressTracker progressTracker = getAttribute(ProgressTracker.PERCENT);

		Assert.assertEquals(
			ProgressStatusConstants.PREPARED, progressTracker.getStatus());
		Assert.assertEquals(StringPool.BLANK, progressTracker.getMessage());
		Assert.assertEquals(0, progressTracker.getPercent());
	}

	protected ProgressTracker getAttribute(String status) {
		ProgressTracker progressTracker =
			(ProgressTracker)_mockHttpSession.getAttribute(
				status + ProgressTrackerTest.class.getName());

		return progressTracker;
	}

	private MockHttpSession _mockHttpSession;
	private MockInstallProcess _mockInstallProcess;

	private class MockInstallProcess {

		public MockInstallProcess(MockHttpSession mockHttpSession) {
			ProgressTracker progressTracker = new ProgressTracker(
				ProgressTrackerTest.class.getName());

			progressTracker.addProgress(
				ProgressStatusConstants.DOWNLOADING, 25, "downloading");
			progressTracker.addProgress(
				ProgressStatusConstants.COPYING, 50, "copying");

			_progressTracker = progressTracker;
		}

		public void copy() {
			_progressTracker.setStatus(ProgressStatusConstants.COPYING);
		}

		public void download() {
			_progressTracker.setStatus(ProgressStatusConstants.DOWNLOADING);
		}

		public void finish() {
			_progressTracker.finish(_mockHttpSession);
		}

		public void initialize() {
			_progressTracker.initialize(_mockHttpSession);
		}

		private ProgressTracker _progressTracker;

	}

}