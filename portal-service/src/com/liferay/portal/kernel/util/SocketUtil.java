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

package com.liferay.portal.kernel.util;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import java.nio.channels.ServerSocketChannel;

/**
 * @author Shuyang Zhou
 */
public class SocketUtil {

	public static ServerSocketChannel createServerSocketChannel(
			InetAddress bindingInetAddress, int startPort,
			ServerSocketConfigurator serverSocketConfigurator)
		throws IOException {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		int port = startPort;

		while (true) {
			try {
				ServerSocket serverSocket = serverSocketChannel.socket();

				if (serverSocketConfigurator != null) {
					serverSocketConfigurator.configure(serverSocket);
				}

				serverSocket.bind(
					new InetSocketAddress(bindingInetAddress, port));

				return serverSocketChannel;
			}
			catch (IOException ioe) {
				port++;
			}
		}
	}

	public static BindInfo getBindInfo(String host, int port)
		throws IOException {

		Socket socket = null;

		try {
			socket = new Socket(host, port);

			InetAddress inetAddress = socket.getLocalAddress();
			NetworkInterface networkInterface =
				NetworkInterface.getByInetAddress(inetAddress);

			BindInfo bindInfo = new BindInfo();

			bindInfo.setInetAddress(inetAddress);
			bindInfo.setNetworkInterface(networkInterface);

			return bindInfo;
		}
		finally {
			if (socket != null) {
				try {
					socket.close();
				}
				catch (IOException ioe) {
				}
			}
		}
	}

	public static class BindInfo {

		public InetAddress getInetAddress() {
			return _inetAddress;
		}

		public NetworkInterface getNetworkInterface() {
			return _networkInterface;
		}

		public void setInetAddress(InetAddress inetAddress) {
			_inetAddress = inetAddress;
		}

		public void setNetworkInterface(NetworkInterface networkInterface) {
			_networkInterface = networkInterface;
		}

		private InetAddress _inetAddress;
		private NetworkInterface _networkInterface;

	}

	public static interface ServerSocketConfigurator {

		public void configure(ServerSocket serverSocket) throws SocketException;

	}

}