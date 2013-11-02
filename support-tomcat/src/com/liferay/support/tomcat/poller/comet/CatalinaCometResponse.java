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

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.poller.comet.CometException;
import com.liferay.portal.kernel.poller.comet.CometResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class CatalinaCometResponse implements CometResponse {

	public CatalinaCometResponse(CometEvent cometEvent) {
		_response = cometEvent.getHttpServletResponse();
	}

	@Override
	public void close() {
		synchronized (this) {
			_open = false;
		}
	}

	@Override
	public boolean isOpen() {
		return _open;
	}

	@Override
	public void writeData(byte[] data) throws CometException {
		synchronized (this) {
			if (!_open) {
				throw new CometException("Stream is closed");
			}

			try {
				OutputStream outputStream = _response.getOutputStream();

				outputStream.write(data);

				outputStream.flush();
			}
			catch (IOException ioe) {
				throw new CometException(ioe);
			}
		}
	}

	@Override
	public void writeData(String data) throws CometException {
		synchronized (this) {
			if (!_open) {
				throw new CometException("Writer is closed");
			}

			try {
				Writer writer = _response.getWriter();

				writer.write(data);

				writer.flush();
			}
			catch (IOException ioe) {
				throw new CometException(ioe);
			}
		}
	}

	private boolean _open = true;
	private HttpServletResponse _response;

}