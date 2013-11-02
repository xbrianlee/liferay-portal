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

import com.liferay.portal.kernel.util.GetterUtil;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * <p>
 * A server that will send out heart beat messages until you kill it. This
 * enables you to try and debug multicast issues.
 * </p>
 *
 * @author Michael C. Han
 */
public class MulticastServerTool {

	public static void main(String[] args) {
		try {
			int port = GetterUtil.getInteger(args[1]);
			long interval = GetterUtil.getLong(args[2]);

			DatagramHandler handler = new DatagramHandler() {

				@Override
				public void process(DatagramPacket packet) {
					String s = new String(
						packet.getData(), 0, packet.getLength());

					System.out.println(s);
				}

				@Override
				public void errorReceived(Throwable t) {
					t.printStackTrace();
				}

			};

			MulticastTransport transport = new MulticastTransport(
				handler, args[0], port);

			transport.connect();

			String msg =
				InetAddress.getLocalHost().getHostName() + ":" + port +
					" heartbeat " ;

			int i = 0;

			while (true) {
				transport.sendMessage(msg + i);

				i++;

				Thread.sleep(interval);
			}
		}
		catch (Exception e) {
			e.printStackTrace();

			System.err.println(
				"Usage: java MulticastServerTool multicastAddress port " +
					"interval");

			System.exit(1);
		}
	}

}