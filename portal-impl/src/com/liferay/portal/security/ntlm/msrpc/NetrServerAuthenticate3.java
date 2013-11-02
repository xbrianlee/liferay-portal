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
public class NetrServerAuthenticate3 extends DcerpcMessage {

	public NetrServerAuthenticate3(
		String primaryName, String accountName, int secureChannelType,
		String computerName, byte[] clientCredential, byte[] serverCredential,
		int negotiateFlags) {

		_primaryName = primaryName;
		_accountName = accountName;
		_secureChannelType = (short)secureChannelType;
		_computerName = computerName;
		_clientCredential = clientCredential;
		_serverCredential = serverCredential;
		_negotiateFlags = negotiateFlags;

		ptype = 0;
		flags = DCERPC_FIRST_FRAG | DCERPC_LAST_FRAG;
	}

	@Override
	public void decode_out(NdrBuffer ndrBuffer) {
		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_serverCredential[i] = (byte)ndrBuffer.dec_ndr_small();
		}

		_negotiateFlags = ndrBuffer.dec_ndr_long();
		_accountRid = ndrBuffer.dec_ndr_long();
		_status = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode_in(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_referent(_primaryName, 1);
		ndrBuffer.enc_ndr_string(_primaryName);
		ndrBuffer.enc_ndr_string(_accountName);
		ndrBuffer.enc_ndr_short(_secureChannelType);
		ndrBuffer.enc_ndr_string(_computerName);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		NdrBuffer derivedNrdBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			derivedNrdBuffer.enc_ndr_small(_clientCredential[i]);
		}

		ndrBuffer.enc_ndr_long(_negotiateFlags);
	}

	public int getAccountRid() {
		return _accountRid;
	}

	public int getNegotiatedFlags() {
		return _negotiateFlags;
	}

	@Override
	public int getOpnum() {
		return 26;
	}

	public byte[] getServerCredential() {
		return _serverCredential;
	}

	public int getStatus() {
		return _status;
	}

	private String _accountName;
	private int _accountRid;
	private byte[] _clientCredential;
	private String _computerName;
	private int _negotiateFlags;
	private String _primaryName;
	private short _secureChannelType;
	private byte[] _serverCredential;
	private int _status;

}