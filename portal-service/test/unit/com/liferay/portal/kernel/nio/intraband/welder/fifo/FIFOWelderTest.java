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

package com.liferay.portal.kernel.nio.intraband.welder.fifo;

import com.liferay.portal.kernel.nio.intraband.MockIntraband;
import com.liferay.portal.kernel.nio.intraband.MockRegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.WelderTestUtil;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class FIFOWelderTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Before
	public void setUp() {
		File tempFile = new File("tempFile");

		tempFile.delete();
	}

	@After
	public void tearDown() {
		File tempFolder = new File(System.getProperty("java.io.tmpdir"));

		File[] files = tempFolder.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().startsWith("FIFO-")) {
				file.delete();
			}
		}
	}

	@Test
	public void testConstructor() throws IOException {
		AtomicLong idCounter = FIFOWelder.idCounter;

		idCounter.set(0);

		FIFOWelder fifoWelder = new FIFOWelder();

		try {
			Assert.assertEquals(1, idCounter.get());
			Assert.assertTrue(fifoWelder.inputFIFOFile.exists());
			Assert.assertTrue(fifoWelder.outputFIFOFile.exists());
		}
		finally {
			File inputFIFOFile = fifoWelder.inputFIFOFile;

			inputFIFOFile.delete();

			File outputFIFOFile = fifoWelder.outputFIFOFile;

			outputFIFOFile.delete();
		}

		String oldTempFolder = System.getProperty("java.io.tmpdir");

		File tempFolder = new File("tempFolder");

		tempFolder.delete();

		System.setProperty("java.io.tmpdir", tempFolder.getAbsolutePath());

		try {
			new FIFOWelder();

			Assert.fail();
		}
		catch (IOException ioe) {
		}
		finally {
			System.setProperty("java.io.tmpdir", oldTempFolder);

			tempFolder.delete();
		}
	}

	@Test
	public void testWeld() throws Exception {
		final FIFOWelder serverFifoWelder = new FIFOWelder();
		final FIFOWelder clientFIFOWelder = WelderTestUtil.transform(
			serverFifoWelder);

		FutureTask<MockRegistrationReference> serverWeldingTask =
			new FutureTask<MockRegistrationReference>(
				new Callable<MockRegistrationReference>() {

					@Override
					public MockRegistrationReference call() throws Exception {
						return (MockRegistrationReference)serverFifoWelder.weld(
							new MockIntraband());
					}
				});

		Thread serverWeldingThread = new Thread(serverWeldingTask);

		serverWeldingThread.start();

		FutureTask<MockRegistrationReference> clientWeldingTask =
			new FutureTask<MockRegistrationReference>(
				new Callable<MockRegistrationReference>() {

					@Override
					public MockRegistrationReference call() throws Exception {
						return (MockRegistrationReference)clientFIFOWelder.weld(
							new MockIntraband());
					}
				});

		Thread clientWeldingThread = new Thread(clientWeldingTask);

		clientWeldingThread.start();

		MockRegistrationReference serverMockRegistrationReference =
			serverWeldingTask.get();

		MockRegistrationReference clientMockRegistrationReference =
			clientWeldingTask.get();

		WelderTestUtil.assertConnectted(
			serverMockRegistrationReference.getScatteringByteChannel(),
			clientMockRegistrationReference.getGatheringByteChannel());
		WelderTestUtil.assertConnectted(
			clientMockRegistrationReference.getScatteringByteChannel(),
			serverMockRegistrationReference.getGatheringByteChannel());

		serverFifoWelder.destroy();
		clientFIFOWelder.destroy();

		try {
			serverFifoWelder.weld(new MockIntraband());

			Assert.fail();
		}
		catch (IllegalStateException ise) {
			Assert.assertEquals(
				"Unable to weld a welder with state DESTROYED",
				ise.getMessage());
		}
		finally {
			serverFifoWelder.inputFIFOFile.delete();
			serverFifoWelder.outputFIFOFile.delete();
		}

		try {
			clientFIFOWelder.weld(new MockIntraband());

			Assert.fail();
		}
		catch (IllegalStateException ise) {
			Assert.assertEquals(
				"Unable to weld a welder with state DESTROYED",
				ise.getMessage());
		}
		finally {
			serverFifoWelder.inputFIFOFile.delete();
			serverFifoWelder.outputFIFOFile.delete();
		}
	}

}