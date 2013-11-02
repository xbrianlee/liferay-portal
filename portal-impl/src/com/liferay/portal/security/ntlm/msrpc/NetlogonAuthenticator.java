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

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;

/**
 * @author Marcellus Tavares
 */
public class NetlogonAuthenticator extends NdrObject {

	public NetlogonAuthenticator() {
		_credential = new byte[8];
	}

	public NetlogonAuthenticator(byte[] credential, int timestamp) {
		_credential = credential;
		_timestamp = timestamp;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		_timestamp = ndrBuffer.dec_ndr_long();

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_credential[i] = (byte)ndrBuffer.dec_ndr_small();
		}
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer.enc_ndr_long(_timestamp);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_credential[i]);
		}
	}

	private byte[] _credential;
	private int _timestamp;

}