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

package com.liferay.portal.kernel.poller.comet;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public interface CometHandler {

	public CometHandler clone();

	public void destroy() throws CometException;

	public CometSession getCometSession();

	public CometState getCometState();

	public void init(CometSession cometSession) throws CometException;

	public void receiveData(char[] data) throws CometException;

	public void receiveData(String data) throws CometException;

}