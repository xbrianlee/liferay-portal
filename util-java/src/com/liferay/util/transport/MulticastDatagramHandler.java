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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.InputStream;

import java.net.DatagramPacket;

import java.util.zip.GZIPInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class MulticastDatagramHandler implements DatagramHandler {

	public MulticastDatagramHandler(boolean gzipData, boolean shortData) {
		_gzipData = gzipData;
		_shortData = shortData;
	}

	@Override
	public void errorReceived(Throwable t) {
		_log.error(t, t);
	}

	@Override
	public void process(DatagramPacket packet) {
		byte[] bytes = packet.getData();

		if (_gzipData) {
			try {
				bytes = getUnzippedBytes(bytes);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_shortData) {
			byte[] temp = new byte[96];

			System.arraycopy(bytes, 0, temp, 0, 96);

			bytes = temp;
		}

		StringBundler sb = new StringBundler(4);

		sb.append("[");
		sb.append(packet.getSocketAddress());
		sb.append("] ");
		sb.append(new String(bytes));

		if (_log.isInfoEnabled()) {
			_log.info(sb);
		}
	}

	protected byte[] getUnzippedBytes(byte[] bytes) throws Exception {
		InputStream is = new GZIPInputStream(
			new UnsyncByteArrayInputStream(bytes));
		UnsyncByteArrayOutputStream ubaos = new UnsyncByteArrayOutputStream(
			bytes.length);

		byte[] buffer = new byte[1500];

		int c = 0;

		while (true) {
			if (c == -1) {
				break;
			}

			c = is.read(buffer, 0, 1500);

			if (c != -1) {
				ubaos.write(buffer, 0, c);
			}
		}

		is.close();

		ubaos.flush();
		ubaos.close();

		return ubaos.toByteArray();
	}

	private static Log _log = LogFactory.getLog(MulticastDatagramHandler.class);

	private boolean _gzipData;
	private boolean _shortData;

}