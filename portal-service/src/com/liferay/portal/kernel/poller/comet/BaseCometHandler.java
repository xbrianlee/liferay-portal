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

package com.liferay.portal.kernel.poller.comet;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseCometHandler implements CometHandler {

	@Override
	public abstract CometHandler clone();

	@Override
	public void destroy() throws CometException {
		_cometState = CometState.STATE_CLOSED;

		try {
			doDestroy();
		}
		catch (CometException ce) {
			throw ce;
		}
		catch (Exception e) {
			throw new CometException(e);
		}
	}

	@Override
	public CometSession getCometSession() {
		return _cometSession;
	}

	@Override
	public CometState getCometState() {
		return _cometState;
	}

	@Override
	public void init(CometSession cometSession) throws CometException {
		_cometSession = cometSession;
		_cometState = CometState.STATE_READY;

		try {
			doInit(cometSession);
		}
		catch (CometException ce) {
			throw ce;
		}
		catch (Exception e) {
			throw new CometException(e);
		}
	}

	@Override
	public void receiveData(char[] data) throws CometException {
		receiveData(new String(data));
	}

	protected void doDestroy() throws Exception {
	}

	protected void doInit(CometSession cometSession) throws Exception {
	}

	private CometSession _cometSession;
	private CometState _cometState = CometState.STATE_OPEN;

}