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

package com.liferay.portal.security.ntlm.msrpc;

import jcifs.dcerpc.UnicodeString;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;
import jcifs.dcerpc.rpc;

/**
 * @author Marcellus Tavares
 */
public class NetlogonIdentityInfo extends NdrObject {

	public NetlogonIdentityInfo(
		String logonDomainName, int parameterControl, int reservedLow,
		int reservedHigh, String userName, String workstation) {

		_logonDomainName = new UnicodeString(logonDomainName, false);
		_parameterControl = parameterControl;
		_reservedLow = reservedLow;
		_reservedHigh = reservedHigh;
		_userName = new UnicodeString(userName, false);
		_workstation = new UnicodeString(workstation, false);
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_short(_logonDomainName.length);
		ndrBuffer.enc_ndr_short(_logonDomainName.maximum_length);
		ndrBuffer.enc_ndr_referent(_logonDomainName.buffer, 1);
		ndrBuffer.enc_ndr_long(_parameterControl);
		ndrBuffer.enc_ndr_long(_reservedLow);
		ndrBuffer.enc_ndr_long(_reservedHigh);
		ndrBuffer.enc_ndr_short(_userName.length);
		ndrBuffer.enc_ndr_short(_userName.maximum_length);
		ndrBuffer.enc_ndr_referent(_userName.buffer, 1);
		ndrBuffer.enc_ndr_short(_workstation.length);
		ndrBuffer.enc_ndr_short(_workstation.maximum_length);
		ndrBuffer.enc_ndr_referent(_workstation.buffer, 1);
	}

	public void encodeLogonDomainName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _logonDomainName);
	}

	public void encodeUserName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _userName);
	}

	public void encodeWorkStationName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _workstation);
	}

	protected void encodeUnicodeString(
		NdrBuffer ndrBuffer, rpc.unicode_string string ) {

		ndrBuffer = ndrBuffer.deferred;

		int stringBufferl = string.length / 2;
		int stringBuffers = string.maximum_length / 2;

		ndrBuffer.enc_ndr_long(stringBuffers);
		ndrBuffer.enc_ndr_long(0);
		ndrBuffer.enc_ndr_long(stringBufferl);

		int stringBufferIndex = ndrBuffer.index;

		ndrBuffer.advance(2 * stringBufferl);

		ndrBuffer = ndrBuffer.derive(stringBufferIndex);

		for (int _i = 0; _i < stringBufferl; _i++) {
			ndrBuffer.enc_ndr_short(string.buffer[_i]);
		}
	}

	private rpc.unicode_string _logonDomainName;
	private int _parameterControl;
	private int _reservedHigh;
	private int _reservedLow;
	private rpc.unicode_string _userName;
	private rpc.unicode_string _workstation;

}