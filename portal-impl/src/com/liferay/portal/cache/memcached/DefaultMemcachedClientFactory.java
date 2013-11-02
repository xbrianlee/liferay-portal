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

package com.liferay.portal.cache.memcached;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.net.InetSocketAddress;

import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.MemcachedClientIF;

/**
 * @author Michael C. Han
 */
public class DefaultMemcachedClientFactory implements MemcachedClientFactory {

	@Override
	public void clear() {
	}

	@Override
	public void close() {
	}

	@Override
	public MemcachedClientIF getMemcachedClient() throws Exception {
		return new MemcachedClient(_connectionFactory, _inetSocketAddresses);
	}

	@Override
	public int getNumActive() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getNumIdle() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void invalidateMemcachedClient(MemcachedClientIF memcachedClient) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void returnMemcachedObject(MemcachedClientIF memcachedClient) {
		throw new UnsupportedOperationException();
	}

	public void setAddresses(List<String> addresses) {
		for (String address : addresses) {
			String[] hostAndPort = StringUtil.split(address, CharPool.COLON);

			String hostName = hostAndPort[0];

			int port = _DEFAULT_MEMCACHED_PORT;

			if (hostAndPort.length == 2) {
				port = GetterUtil.getInteger(hostAndPort[1]);
			}

			InetSocketAddress inetSocketAddress = new InetSocketAddress(
				hostName, port);

			_inetSocketAddresses.add(inetSocketAddress);
		}
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		_connectionFactory = connectionFactory;
	}

	private static final int _DEFAULT_MEMCACHED_PORT = 11211;

	private ConnectionFactory _connectionFactory;
	private List<InetSocketAddress> _inetSocketAddresses =
		new ArrayList<InetSocketAddress>();

}