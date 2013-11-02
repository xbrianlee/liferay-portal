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

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.MockRegistrationReference;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.Welder;

/**
 * @author Shuyang Zhou
 */
public class MockWelder implements Welder {

	@Override
	public void destroy() {
	}

	public boolean isClientWelded() {
		return _clientWelded;
	}

	public boolean isServerWelded() {
		return _serverWelded;
	}

	@Override
	public RegistrationReference weld(Intraband intraband) {
		_clientWelded = true;

		return new MockRegistrationReference(intraband);
	}

	private boolean _clientWelded;
	private boolean _serverWelded;

}