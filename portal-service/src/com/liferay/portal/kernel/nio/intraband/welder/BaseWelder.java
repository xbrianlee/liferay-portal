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

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseWelder implements Welder {

	public BaseWelder() {

		// Assignments have to stay in the constructor because we need to
		// differentiate between a constructor created object and a
		// deserialization created object. Only the constructor created object
		// needs to assign values. The deserialization created object gets its
		// values from the original object.

		server = true;
	}

	@Override
	public synchronized void destroy() throws IOException {
		if (state != State.WELDED) {
			throw new IllegalStateException(
				"Unable to destroy a welder with state " + state);
		}

		registrationReference.cancelRegistration();

		doDestroy();

		state = State.DESTROYED;
	}

	@Override
	public synchronized RegistrationReference weld(Intraband intraband)
		throws IOException {

		if (state != State.CREATED) {
			throw new IllegalStateException(
				"Unable to weld a welder with state " + state);
		}

		if (server) {
			registrationReference = weldServer(intraband);
		}
		else {
			registrationReference = weldClient(intraband);
		}

		state = State.WELDED;

		return registrationReference;
	}

	protected abstract void doDestroy() throws IOException;

	protected abstract RegistrationReference weldClient(Intraband intraband)
		throws IOException;

	protected abstract RegistrationReference weldServer(Intraband intraband)
		throws IOException;

	protected transient RegistrationReference registrationReference;
	protected final transient boolean server;
	protected transient State state = State.CREATED;

	protected static enum State {

		CREATED, DESTROYED, WELDED

	}

	private void readObject(ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		objectInputStream.defaultReadObject();

		state = State.CREATED;
	}

}