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

package com.liferay.util.transport;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * The MulticastTransport will send strings across a specified multicast
 * address. It will also listen for messages and hand them to the appropriate
 * DatagramHandler.
 * </p>
 *
 * @author Michael C. Han
 */
public class MulticastTransport extends Thread implements Transport {

	public MulticastTransport(DatagramHandler handler, String host, int port) {
		super("MulticastListener-" + host + port);

		setDaemon(true);
		_handler = handler;
		_host = host;
		_port = port;
	}

	@Override
	public synchronized void connect() throws IOException {
		if (_socket == null) {
			_socket = new MulticastSocket(_port);
		}
		else if (_socket.isConnected() && _socket.isBound()) {
			return;
		}

		_address = InetAddress.getByName(_host);

		_socket.joinGroup(_address);

		_connected = true;

		start();
	}

	@Override
	public synchronized void disconnect() {

		// Interrupt all processing

		if (_address != null) {
			try {
				_socket.leaveGroup(_address);
				_address = null;
			}
			catch (IOException ioe) {
				_log.error("Unable to leave group", ioe);
			}
		}

		_connected = false;

		interrupt();

		_socket.close();
	}

	@Override
	public boolean isConnected() {
		return _connected;
	}

	@Override
	public void run() {
		try {
			while (_connected) {
				_socket.receive(_inboundPacket);
				_handler.process(_inboundPacket);
			}
		}
		catch (IOException ioe) {
			_log.error("Unable to process ", ioe);

			_socket.disconnect();

			_connected = false;

			_handler.errorReceived(ioe);
		}
	}

	public synchronized void sendMessage(byte[] bytes) throws IOException {
		_outboundPacket.setData(bytes);
		_outboundPacket.setAddress(_address);
		_outboundPacket.setPort(_port);

		_socket.send(_outboundPacket);
	}

	@Override
	public synchronized void sendMessage(String message) throws IOException {
		sendMessage(message.getBytes());
	}

	private static Log _log = LogFactory.getLog(MulticastTransport.class);

	private InetAddress _address;
	private boolean _connected;
	private DatagramHandler _handler;
	private String _host;
	private byte[] _inboundBuffer = new byte[4096];
	private DatagramPacket _inboundPacket = new DatagramPacket(
		_inboundBuffer, _inboundBuffer.length);
	private byte[] _outboundBuffer = new byte[4096];
	private DatagramPacket _outboundPacket = new DatagramPacket(
		_outboundBuffer, _outboundBuffer.length);
	private int _port;
	private MulticastSocket _socket;

}