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

package com.liferay.portal.kernel.security.pacl.permission;

import java.security.BasicPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalMessageBusPermission extends BasicPermission {

	public static void checkListen(String destinationName) {
		_pacl.checkListen(destinationName);
	}

	public static void checkSend(String destinationName) {
		_pacl.checkSend(destinationName);
	}

	public PortalMessageBusPermission(String name, String destinationName) {
		super(name);

		_destinationName = destinationName;
	}

	@Override
	public String getActions() {
		return _destinationName;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public static interface PACL {

		public void checkListen(String destinationName);

		public void checkSend(String destinationName);

	}

	private static PACL _pacl = new NoPACL();

	private String _destinationName;

	private static class NoPACL implements PACL {

		@Override
		public void checkListen(String destinationName) {
		}

		@Override
		public void checkSend(String destinationName) {
		}

	}

}