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

package com.liferay.portal.kernel.nio.intraband.nonblocking;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.nio.channels.SelectionKey;

/**
 * @author Shuyang Zhou
 */
class SelectionKeyRegistrationReference implements RegistrationReference {

	@Override
	public void cancelRegistration() {
		readSelectionKey.cancel();
		writeSelectionKey.cancel();
	}

	@Override
	public Intraband getIntraband() {
		return intraband;
	}

	@Override
	public boolean isValid() {
		return writeSelectionKey.isValid();
	}

	protected SelectionKeyRegistrationReference(
		Intraband intraband, SelectionKey readSelectionKey,
		SelectionKey writeSelectionKey) {

		this.intraband = intraband;
		this.readSelectionKey = readSelectionKey;
		this.writeSelectionKey = writeSelectionKey;
	}

	protected final Intraband intraband;
	protected final SelectionKey readSelectionKey;
	protected final SelectionKey writeSelectionKey;

}