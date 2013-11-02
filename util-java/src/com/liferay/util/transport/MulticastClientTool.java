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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * A client that listens for multicast messages at a designated port. You may
 * use this to for potential multicast issues when tuning distributed caches.
 * </p>
 *
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class MulticastClientTool {

	public static void main(String[] args) {
		try {
			new MulticastClientTool(args);
		}
		catch (Exception e) {
			e.printStackTrace();

			StringBuilder sb = new StringBuilder(3);

			sb.append("Usage: java -classpath util-java.jar ");
			sb.append(MulticastClientTool.class.getName());
			sb.append("[-g] [-s] -h [multicastAddress] -p [port]");

			System.err.println(sb.toString());

			System.exit(1);
		}
	}

	private MulticastClientTool(String[] args) throws Exception {
		Map<String, Object> argsMap = _getArgsMap(args);

		Integer port = (Integer)argsMap.get("port");
		String host = (String)argsMap.get("host");
		Boolean gzipData = (Boolean)argsMap.get("gzip");
		Boolean shortData = (Boolean)argsMap.get("short");

		DatagramHandler datagramHandler = new MulticastDatagramHandler(
			gzipData.booleanValue(), shortData.booleanValue());

		MulticastTransport multicastTransport = new MulticastTransport(
			datagramHandler, host, port);

		if (shortData.booleanValue()) {
			System.out.println("Truncating to 96 bytes.");
		}

		System.out.println("Started up and waiting...");

		multicastTransport.connect();

		synchronized (multicastTransport) {
			multicastTransport.wait();
		}
	}

	private Map<String, Object> _getArgsMap(String[] args) throws Exception {
		Map<String, Object> argsMap = new HashMap<String, Object>();

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-g")) {
				argsMap.put("gzip", Boolean.TRUE);
			}
			else if (args[i].equals("-s")) {
				argsMap.put("short", Boolean.TRUE);
			}
			else if (args[i].equals("-h")) {
				argsMap.put("host", args[i + 1]);

				i++;
			}
			else if (args[i].equals("-p")) {
				argsMap.put("port", new Integer(args[i + 1]));

				i++;
			}
		}

		if (!argsMap.containsKey("gzip")) {
			argsMap.put("gzip", Boolean.FALSE);
		}

		if (!argsMap.containsKey("short")) {
			argsMap.put("short", Boolean.FALSE);
		}

		return argsMap;
	}

}