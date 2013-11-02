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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.InputStream;
import java.io.OutputStream;

import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 * @author Tina Tian
 */
public class BaseReceiver implements Receiver {

	@Override
	public void block() {
	}

	@Override
	public void getState(OutputStream outputStream) throws Exception {
	}

	public View getView() {
		return view;
	}

	@Override
	public void receive(Message message) {
	}

	@Override
	public void setState(InputStream inputStream) throws Exception {
	}

	@Override
	public void suspect(Address address) {
	}

	@Override
	public void unblock() {
	}

	@Override
	public void viewAccepted(View view) {
		if (_log.isInfoEnabled()) {
			_log.info("Accepted view " + view);
		}

		this.view = view;
	}

	protected volatile View view;

	private static Log _log = LogFactoryUtil.getLog(BaseReceiver.class);

}