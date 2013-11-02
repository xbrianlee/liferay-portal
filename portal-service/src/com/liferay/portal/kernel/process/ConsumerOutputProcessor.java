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

package com.liferay.portal.kernel.process;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuyang Zhou
 */
public class ConsumerOutputProcessor implements OutputProcessor<Void, Void> {

	@Override
	public Void processStdErr(InputStream stdErrInputStream)
		throws ProcessException {

		_consume(stdErrInputStream);

		return null;
	}

	@Override
	public Void processStdOut(InputStream stdOutInputStream)
		throws ProcessException {

		_consume(stdOutInputStream);

		return null;
	}

	private void _consume(InputStream inputStream) throws ProcessException {
		byte[] buffer = new byte[1024];

		try {
			while (inputStream.read(buffer) != -1);
		}
		catch (IOException ioe) {
			throw new ProcessException(ioe);
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException ioe) {
				throw new ProcessException(ioe);
			}
		}
	}

}