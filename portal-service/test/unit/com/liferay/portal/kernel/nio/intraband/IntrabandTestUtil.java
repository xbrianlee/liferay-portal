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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SocketUtil;
import com.liferay.portal.kernel.util.SocketUtil.ServerSocketConfigurator;

import java.io.IOException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;

import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.logging.LogRecord;

import org.junit.Assert;

/**
 * @author Shuyang Zhou
 */
public class IntrabandTestUtil {

	public static void assertMessageStartWith(
		LogRecord logRecord, String messagePrefix) {

		String message = logRecord.getMessage();

		Assert.assertTrue(message.startsWith(messagePrefix));
	}

	public static <T> T createProxy(Class<?>... interfaces) {
		return (T)ProxyUtil.newProxyInstance(
			IntrabandTestUtil.class.getClassLoader(), interfaces,
			new InvocationHandler() {

				@Override
				public Object invoke(
					Object proxy, Method method, Object[] args) {

					throw new UnsupportedOperationException();
				}

			});
	}

	public static SocketChannel[] createSocketChannelPeers()
		throws IOException {

		ServerSocketChannel serverSocketChannel =
			SocketUtil.createServerSocketChannel(
				InetAddress.getLocalHost(), 15238, _serverSocketConfigurator);

		ServerSocket serverSocket = serverSocketChannel.socket();

		SocketChannel clientPeerSocketChannel = SocketChannel.open(
			new InetSocketAddress(
				InetAddress.getLocalHost(), serverSocket.getLocalPort()));

		SocketChannel serverPeerSocketChannel = serverSocketChannel.accept();

		serverSocketChannel.close();

		SocketChannel[] socketChannels = new SocketChannel[2];

		socketChannels[0] = serverPeerSocketChannel;
		socketChannels[1] = clientPeerSocketChannel;

		return socketChannels;
	}

	public static Datagram readDatagramFully(
			ScatteringByteChannel scatteringByteChannel)
		throws IOException {

		Datagram datagram = DatagramHelper.createReceiveDatagram();

		while (!DatagramHelper.readFrom(datagram, scatteringByteChannel));

		return datagram;
	}

	private static ServerSocketConfigurator _serverSocketConfigurator =
		new ServerSocketConfigurator() {

		@Override
		public void configure(ServerSocket serverSocket)
			throws SocketException {

			serverSocket.setReuseAddress(true);
		}

	};

}