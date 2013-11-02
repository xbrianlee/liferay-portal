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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.SocketUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 * @author Shuyang Zhou
 */
public abstract class ClusterBase {

	public void afterPropertiesSet() {
		if (!isEnabled()) {
			return;
		}

		if (!_initialized) {
			initSystemProperties();

			try {
				initBindAddress();
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Failed to initialize outgoing IP address", e);
				}
			}

			_initialized = true;
		}

		try {
			initChannels();
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to initialize channels", e);
			}

			throw new IllegalStateException(e);
		}
	}

	public abstract void destroy();

	public boolean isEnabled() {
		return PropsValues.CLUSTER_LINK_ENABLED;
	}

	protected JChannel createJChannel(
			String properties, Receiver receiver, String clusterName)
		throws Exception {

		JChannel jChannel = new JChannel(properties);

		jChannel.setReceiver(receiver);

		jChannel.connect(clusterName);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Create a new channel with properties " +
					jChannel.getProperties());
		}

		return jChannel;
	}

	protected List<Address> getAddresses(JChannel channel) {
		BaseReceiver baseReceiver = (BaseReceiver)channel.getReceiver();

		View view = baseReceiver.getView();

		List<org.jgroups.Address> jGroupsAddresses = view.getMembers();

		if (jGroupsAddresses == null) {
			return Collections.emptyList();
		}

		List<Address> addresses = new ArrayList<Address>(
			jGroupsAddresses.size());

		for (org.jgroups.Address jgroupsAddress : jGroupsAddresses) {
			addresses.add(new AddressImpl(jgroupsAddress));
		}

		return addresses;
	}

	protected void initBindAddress() throws Exception {
		String autodetectAddress = PropsValues.CLUSTER_LINK_AUTODETECT_ADDRESS;

		if (Validator.isNull(autodetectAddress)) {
			bindInetAddress = InetAddressUtil.getLocalInetAddress();

			return;
		}

		String host = autodetectAddress;
		int port = 80;

		int index = autodetectAddress.indexOf(CharPool.COLON);

		if (index != -1) {
			host = autodetectAddress.substring(0, index);
			port = GetterUtil.getInteger(
				autodetectAddress.substring(index + 1), port);
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Autodetecting JGroups outgoing IP address and interface for " +
					host + ":" + port);
		}

		SocketUtil.BindInfo bindInfo = SocketUtil.getBindInfo(host, port);

		bindInetAddress = bindInfo.getInetAddress();
		NetworkInterface networkInterface = bindInfo.getNetworkInterface();

		System.setProperty(
			"jgroups.bind_addr", bindInetAddress.getHostAddress());
		System.setProperty(
			"jgroups.bind_interface", networkInterface.getName());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Setting JGroups outgoing IP address to " +
					bindInetAddress.getHostAddress() + " and interface to " +
						networkInterface.getName());
		}
	}

	protected abstract void initChannels() throws Exception;

	protected void initSystemProperties() {
		for (String systemProperty :
				PropsValues.CLUSTER_LINK_CHANNEL_SYSTEM_PROPERTIES) {

			int index = systemProperty.indexOf(CharPool.COLON);

			if (index == -1) {
				continue;
			}

			String key = systemProperty.substring(0, index);
			String value = systemProperty.substring(index + 1);

			System.setProperty(key, value);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Setting system property {key=" + key + ", value=" + value +
						"}");
			}
		}
	}

	protected static InetAddress bindInetAddress;

	private static Log _log = LogFactoryUtil.getLog(ClusterBase.class);

	private static boolean _initialized;

}