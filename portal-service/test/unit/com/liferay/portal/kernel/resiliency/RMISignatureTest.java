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

package com.liferay.portal.kernel.resiliency;

import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class RMISignatureTest {

	@Test
	public void testMPISignature() {
		_checkRMISignature(MPI.class, false);
	}

	@Test
	public void testSPISignature() {
		_checkRMISignature(SPI.class, true);
	}

	private void _checkRMISignature(
		Class<? extends Remote> rmiClass, boolean serializable) {

		Assert.assertTrue(
			rmiClass + " does not implement " + Remote.class,
			Remote.class.isAssignableFrom(rmiClass));

		if (serializable) {
			Assert.assertTrue(
				rmiClass + " does not implement " + Serializable.class,
				Serializable.class.isAssignableFrom(rmiClass));
		}

		Method[] methods = rmiClass.getDeclaredMethods();

		method:
		for (Method method : methods) {
			Class<?>[] exceptionTypes = method.getExceptionTypes();

			for (Class<?> exceptionType : exceptionTypes) {
				if (RemoteException.class.isAssignableFrom(exceptionType)) {
					continue method;
				}
			}

			Assert.fail(method + " does not throw " + RemoteException.class);
		}
	}

}