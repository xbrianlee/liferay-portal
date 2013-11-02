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

package com.liferay.portal.kernel.nio.intraband;

import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author Shuyang Zhou
 */
public class MockRegistrationReference implements RegistrationReference {

	public MockRegistrationReference(Intraband intraband) {
		_intraband = intraband;
	}

	public MockRegistrationReference(
		ScatteringByteChannel scatteringByteChannel,
		GatheringByteChannel gatheringByteChannel) {

		_gatheringByteChannel = gatheringByteChannel;
		_scatteringByteChannel = scatteringByteChannel;
	}

	@Override
	public void cancelRegistration() {
		_cancelled = true;
	}

	public GatheringByteChannel getGatheringByteChannel() {
		return _gatheringByteChannel;
	}

	@Override
	public Intraband getIntraband() {
		return _intraband;
	}

	public ScatteringByteChannel getScatteringByteChannel() {
		return _scatteringByteChannel;
	}

	@Override
	public boolean isValid() {
		return !_cancelled;
	}

	private boolean _cancelled;
	private GatheringByteChannel _gatheringByteChannel;
	private Intraband _intraband;
	private ScatteringByteChannel _scatteringByteChannel;

}