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

package com.liferay.portal.kernel.nio.intraband.rpc;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler.CompletionType;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.IOException;
import java.io.Serializable;

import java.util.EnumSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author Shuyang Zhou
 */
public class IntrabandRPCUtil {

	public static <V extends Serializable> Future<V> execute(
			RegistrationReference registrationReference,
			ProcessCallable<V> processCallable)
		throws IntrabandRPCException {

		Intraband intraband = registrationReference.getIntraband();

		SystemDataType systemDataType = SystemDataType.RPC;

		Serializer serializer = new Serializer();

		serializer.writeObject(processCallable);

		try {
			Datagram datagram = Datagram.createRequestDatagram(
				systemDataType.getValue(), serializer.toByteBuffer());

			FutureResult<V> futureResult = new FutureResult<V>();

			intraband.sendDatagram(
				registrationReference, datagram, null, repliedEnumSet,
				new FutureCompletionHandler<V>(futureResult));

			return futureResult;
		}
		catch (Exception e) {
			throw new IntrabandRPCException(e);
		}
	}

	protected static Callable<Serializable> emptyCallable =
		new Callable<Serializable>() {

		@Override
		public Serializable call() throws Exception {
			return null;
		}

	};

	protected static EnumSet<CompletionType> repliedEnumSet = EnumSet.of(
		CompletionType.REPLIED);

	protected static class FutureCompletionHandler<V extends Serializable>
		implements CompletionHandler<Object> {

		protected FutureCompletionHandler(FutureResult<V> futureResult) {
			_futureResult = futureResult;
		}

		@Override
		public void delivered(Object attachment) {
		}

		@Override
		public void failed(Object attachment, IOException ioe) {
			_futureResult.setException(ioe);
		}

		@Override
		public void replied(Object attachment, Datagram datagram) {
			Deserializer deserializer = new Deserializer(
				datagram.getDataByteBuffer());

			try {
				V v = deserializer.readObject();

				_futureResult.set(v);
			}
			catch (ClassNotFoundException cnfe) {
				_futureResult.setException(cnfe);
			}
		}

		@Override
		public void submitted(Object attachment) {
		}

		@Override
		public void timedOut(Object attachment) {
			_futureResult.cancel(true);
		}

		private final FutureResult<V> _futureResult;

	}

	protected static class FutureResult<V extends Serializable>
		extends FutureTask<V> {

		protected FutureResult() {
			super((Callable<V>)emptyCallable);
		}

		@Override
		protected void set(V v) {
			super.set(v);
		}

		@Override
		protected void setException(Throwable t) {
			super.setException(t);
		}

	}

}