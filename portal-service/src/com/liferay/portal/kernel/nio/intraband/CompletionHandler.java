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

import java.io.IOException;

/**
 * @author Shuyang Zhou
 */
public interface CompletionHandler<A> {

	public void delivered(A attachment);

	public void failed(A attachment, IOException ioe);

	public void replied(A attachment, Datagram datagram);

	public void submitted(A attachment);

	public void timedOut(A attachment);

	public static enum CompletionType {

		SUBMITTED, DELIVERED, REPLIED

	}

}