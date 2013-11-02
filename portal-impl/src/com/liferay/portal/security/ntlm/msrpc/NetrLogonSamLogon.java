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
import jcifs.dcerpc.ndr.NdrException;

/**
 * @author Marcellus Tavares
 */
public class NetrLogonSamLogon extends DcerpcMessage {

	public NetrLogonSamLogon(
		String logonServer, String computerName,
		NetlogonAuthenticator netlogonAuthenticator,
		NetlogonAuthenticator returnNetlogonAuthenticator, int logonLevel,
		NetlogonNetworkInfo netlogonNetworkInfo, int validationLevel,
		NetlogonValidationSamInfo netlogonValidationSamInfo,
		int authoritative) {

		_logonServer = logonServer;
		_computerName = computerName;
		_authenticator = netlogonAuthenticator;
		_returnAuthenticator = returnNetlogonAuthenticator;
		_logonLevel = (short)logonLevel;
		_logonInformation = netlogonNetworkInfo;
		_validationLevel = (short)validationLevel;
		_validationInformation = netlogonValidationSamInfo;
		_authoritative = (byte)authoritative;

		ptype = 0;
		flags = DCERPC_FIRST_FRAG | DCERPC_LAST_FRAG;
	}

	@Override
	public void decode_out(NdrBuffer ndrBuffer) throws NdrException {
		int returnAuthenticator = ndrBuffer.dec_ndr_long();

		if (returnAuthenticator > 0) {
			_returnAuthenticator.decode(ndrBuffer);
		}

		ndrBuffer.dec_ndr_short();

		int validationInformation = ndrBuffer.dec_ndr_long();

		if (validationInformation > 0) {
			ndrBuffer = ndrBuffer.deferred;
			_validationInformation.decode(ndrBuffer);
		}

		_authoritative = (byte)ndrBuffer.dec_ndr_small();
		_status = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode_in(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_referent(_logonServer, 1);
		ndrBuffer.enc_ndr_string(_logonServer);

		ndrBuffer.enc_ndr_referent(_computerName, 1);
		ndrBuffer.enc_ndr_string(_computerName);

		ndrBuffer.enc_ndr_referent(_authenticator, 1);

		_authenticator.encode(ndrBuffer);

		ndrBuffer.enc_ndr_referent(_returnAuthenticator, 1);

		_returnAuthenticator.encode(ndrBuffer);

		ndrBuffer.enc_ndr_short(_logonLevel);
		ndrBuffer.enc_ndr_short(_logonLevel);

		ndrBuffer.enc_ndr_referent(_logonInformation, 1);

		_logonInformation.encode(ndrBuffer);

		ndrBuffer.enc_ndr_short(_validationLevel);
	}

	public NetlogonValidationSamInfo getNetlogonValidationSamInfo() {
		return _validationInformation;
	}

	@Override
	public int getOpnum() {
		return 2;
	}

	public int getStatus() {
		return _status;
	}

	private NetlogonAuthenticator _authenticator;

	@SuppressWarnings("unused")
	private byte _authoritative;

	private String _computerName;
	private NetlogonNetworkInfo _logonInformation;
	private short _logonLevel;
	private String _logonServer;
	private NetlogonAuthenticator _returnAuthenticator;
	private int _status;
	private NetlogonValidationSamInfo _validationInformation;
	private short _validationLevel;

}