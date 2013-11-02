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

package com.liferay.portal.kernel.nio.intraband.blocking;

import com.liferay.portal.kernel.nio.intraband.ChannelContext;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
class FutureRegistrationReference implements RegistrationReference {

	@Override
	public void cancelRegistration() {
		readFuture.cancel(true);
		writeFuture.cancel(true);
	}

	@Override
	public Intraband getIntraband() {
		return intraband;
	}

	@Override
	public boolean isValid() {
		if (!readFuture.isDone() && !writeFuture.isDone()) {
			return true;
		}

		return false;
	}

	protected FutureRegistrationReference(
		Intraband intraband, ChannelContext channelContext,
		Future<Void> readFuture, Future<Void> writeFuture) {

		this.intraband = intraband;
		this.channelContext = channelContext;
		this.readFuture = readFuture;
		this.writeFuture = writeFuture;
	}

	protected final ChannelContext channelContext;
	protected final Intraband intraband;
	protected final Future<Void> readFuture;
	protected final Future<Void> writeFuture;

}