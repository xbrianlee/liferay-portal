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

package com.liferay.portal.kernel.audit;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Michael C. Han
 */
public class AuditRequestThreadLocal {

	public static AuditRequestThreadLocal getAuditThreadLocal() {
		AuditRequestThreadLocal auditRequestThreadLocal =
			_auditRequestThreadLocal.get();

		if (auditRequestThreadLocal == null) {
			auditRequestThreadLocal = new AuditRequestThreadLocal();

			_auditRequestThreadLocal.set(auditRequestThreadLocal);
		}

		return auditRequestThreadLocal;
	}

	public static void removeAuditThreadLocal() {
		_auditRequestThreadLocal.remove();
	}

	public String getClientHost() {
		return _clientHost;
	}

	public String getClientIP() {
		return _clientIP;
	}

	public String getQueryString() {
		return _queryString;
	}

	public long getRealUserId() {
		return _realUserId;
	}

	public String getRequestURL() {
		return _requestURL;
	}

	public String getServerName() {
		return _serverName;
	}

	public int getServerPort() {
		return _serverPort;
	}

	public String getSessionID() {
		return _sessionID;
	}

	public void setClientHost(String clientHost) {
		_clientHost = clientHost;
	}

	public void setClientIP(String clientIP) {
		_clientIP = clientIP;
	}

	public void setQueryString(String queryString) {
		_queryString = queryString;
	}

	public void setRealUserId(long realUserId) {
		_realUserId = realUserId;
	}

	public void setRequestURL(String requestURL) {
		_requestURL = requestURL;
	}

	public void setServerName(String serverName) {
		_serverName = serverName;
	}

	public void setServerPort(int serverPort) {
		_serverPort = serverPort;
	}

	public void setSessionID(String sessionID) {
		_sessionID = sessionID;
	}

	private static ThreadLocal<AuditRequestThreadLocal>
		_auditRequestThreadLocal =
			new AutoResetThreadLocal<AuditRequestThreadLocal>(
				AuditRequestThreadLocal.class + "._auditRequestThreadLocal");

	private String _clientHost;
	private String _clientIP;
	private String _queryString;
	private long _realUserId;
	private String _requestURL;
	private String _serverName;
	private int _serverPort;
	private String _sessionID;

}