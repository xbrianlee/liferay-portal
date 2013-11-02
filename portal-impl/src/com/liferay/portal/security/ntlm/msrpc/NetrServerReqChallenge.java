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

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.ndr.NdrBuffer;

/**
 * @author Marcellus Tavares
 */
public class NetrServerReqChallenge extends DcerpcMessage {

	public NetrServerReqChallenge(
		String primaryName, String computerName, byte[] clientChallenge,
		byte[] serverChallenge) {

		_primaryName = primaryName;
		_computerName = computerName;
		_clientChallenge = clientChallenge;
		_serverChallenge = serverChallenge;

		ptype = 0;
		flags = DCERPC_FIRST_FRAG | DCERPC_LAST_FRAG;
	}

	@Override
	public void decode_out(NdrBuffer ndrBuffer) {
		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_serverChallenge[i] = (byte)ndrBuffer.dec_ndr_small();
		}

		_status = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode_in(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_referent(_primaryName, 1);
		ndrBuffer.enc_ndr_string(_primaryName);
		ndrBuffer.enc_ndr_string(_computerName);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_clientChallenge[i]);
		}
	}

	@Override
	public int getOpnum() {
		return 4;
	}

	public byte[] getServerChallenge() {
		return _serverChallenge;
	}

	public int getStatus() {
		return _status;
	}

	private byte[] _clientChallenge;
	private String _computerName;
	private String _primaryName;
	private byte[] _serverChallenge;
	private int _status;

}