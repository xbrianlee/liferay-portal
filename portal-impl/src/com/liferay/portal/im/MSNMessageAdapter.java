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

package com.liferay.portal.im;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import rath.msnm.MSNMessenger;
import rath.msnm.SwitchboardSession;
import rath.msnm.entity.MsnFriend;
import rath.msnm.event.MsnAdapter;
import rath.msnm.msg.MimeMessage;

/**
 * @author Brian Wing Shun Chan
 */
public class MSNMessageAdapter extends MsnAdapter {

	public MSNMessageAdapter(MSNMessenger msn, String to, String msg) {
		_msn = msn;
		_to = to;
		_msg = msg;
	}

	@Override
	public void whoJoinSession(SwitchboardSession ss, MsnFriend join) {
		try {
			if (_to.equals(join.getLoginName())) {
				ss.sendInstantMessage(new MimeMessage(_msg));
				ss.cleanUp();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		_msn.removeMsnListener(this);
	}

	private static Log _log = LogFactoryUtil.getLog(MSNMessageAdapter.class);

	private String _msg;
	private MSNMessenger _msn;
	private String _to;

}