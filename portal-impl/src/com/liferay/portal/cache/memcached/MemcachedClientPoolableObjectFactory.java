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

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * @author Michael C. Han
 */
public class MemcachedClientPoolableObjectFactory
	implements PoolableObjectFactory {

	@Override
	public void activateObject(Object obj) throws Exception {
	}

	@Override
	public void destroyObject(Object obj) {
		MemcachedClientIF memcachedClient = (MemcachedClientIF)obj;

		memcachedClient.shutdown();
	}

	@Override
	public Object makeObject() throws Exception {
		return new MemcachedClient(_connectionFactory, _inetSocketAddresses);
	}

	@Override
	public void passivateObject(Object obj) throws Exception {
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

	@Override
	public boolean validateObject(Object obj) {
		return true;
	}

	private static final int _DEFAULT_MEMCACHED_PORT = 11211;

	private ConnectionFactory _connectionFactory;
	private List<InetSocketAddress> _inetSocketAddresses =
		new ArrayList<InetSocketAddress>();

}