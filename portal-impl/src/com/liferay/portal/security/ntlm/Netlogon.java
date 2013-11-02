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

package com.liferay.portal.security.ntlm;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.ntlm.msrpc.NetlogonAuthenticator;
import com.liferay.portal.security.ntlm.msrpc.NetlogonIdentityInfo;
import com.liferay.portal.security.ntlm.msrpc.NetlogonNetworkInfo;
import com.liferay.portal.security.ntlm.msrpc.NetlogonValidationSamInfo;
import com.liferay.portal.security.ntlm.msrpc.NetrLogonSamLogon;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;

import jcifs.dcerpc.DcerpcBinding;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.UnicodeString;

import jcifs.smb.SmbException;

/**
 * @author Marcellus Tavares
 * @author Michael C. Han
 */
public class Netlogon {

	public NtlmUserAccount logon(
			String domain, String userName, String workstation,
			byte[] serverChallenge, byte[] ntResponse, byte[] lmResponse)
		throws NtlmLogonException {

		NetlogonConnection netlogonConnection = new NetlogonConnection();

		try {
			netlogonConnection.connect(
				_domainController, _domainControllerName, _ntlmServiceAccount);

			NetlogonAuthenticator netlogonAuthenticator =
				netlogonConnection.computeNetlogonAuthenticator();

			NetlogonIdentityInfo netlogonIdentityInfo =
				new NetlogonIdentityInfo(
					domain, 0x00000820, 0, 0, userName, workstation);

			NetlogonNetworkInfo netlogonNetworkInfo = new NetlogonNetworkInfo(
				netlogonIdentityInfo, serverChallenge, ntResponse, lmResponse);

			NetrLogonSamLogon netrLogonSamLogon = new NetrLogonSamLogon(
				_domainControllerName, _ntlmServiceAccount.getComputerName(),
				netlogonAuthenticator, new NetlogonAuthenticator(), 2,
				netlogonNetworkInfo, 2, new NetlogonValidationSamInfo(), 0);

			DcerpcHandle dcerpcHandle = netlogonConnection.getDcerpcHandle();

			dcerpcHandle.sendrecv(netrLogonSamLogon);

			if (netrLogonSamLogon.getStatus() == 0) {
				NetlogonValidationSamInfo netlogonValidationSamInfo =
					netrLogonSamLogon.getNetlogonValidationSamInfo();

				UnicodeString name = new UnicodeString(
					netlogonValidationSamInfo.getEffectiveName(), false);

				return new NtlmUserAccount(name.toString());
			}

			SmbException smbe = new SmbException(
				netrLogonSamLogon.getStatus(), false);

			throw new NtlmLogonException(
				"Unable to authenticate user: " + smbe.getMessage());
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new NtlmLogonException(
				"Unable to authenticate due to invalid encryption algorithm",
				nsae);
		}
		catch (IOException ioe) {
			throw new NtlmLogonException(
				"Unable to authenticate due to communication failure with " +
					"server",
				ioe);
		}
		finally {
			try {
				netlogonConnection.disconnect();
			}
			catch (Exception e) {
				_log.error("Unable to disconnect Netlogon connection", e);
			}
		}
	}

	public void setConfiguration(
		String domainController, String domainControllerName,
		NtlmServiceAccount ntlmServiceAccount) {

		_domainController = domainController;
		_domainControllerName = domainControllerName;
		_ntlmServiceAccount = ntlmServiceAccount;
	}

	private static Log _log = LogFactoryUtil.getLog(Netlogon.class);

	private String _domainController;
	private String _domainControllerName;
	private NtlmServiceAccount _ntlmServiceAccount;

	static {
		DcerpcBinding.addInterface(
			"netlogon", "12345678-1234-abcd-ef00-01234567cffb:1.0");
	}

}