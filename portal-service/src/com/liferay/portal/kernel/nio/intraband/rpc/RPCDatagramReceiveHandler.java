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
import com.liferay.portal.kernel.nio.intraband.BaseAsyncDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class RPCDatagramReceiveHandler extends BaseAsyncDatagramReceiveHandler {

	@Override
	protected void doReceive(
			RegistrationReference registrationReference, Datagram datagram)
		throws Exception {

		Deserializer deserializer = new Deserializer(
			datagram.getDataByteBuffer());

		ProcessCallable<? extends Serializable> processCallable =
			deserializer.readObject();

		Serializer serializer = new Serializer();

		Serializable result = processCallable.call();

		serializer.writeObject(result);

		Intraband intraband = registrationReference.getIntraband();

		intraband.sendDatagram(
			registrationReference,
			Datagram.createResponseDatagram(
				datagram, serializer.toByteBuffer()));
	}

}