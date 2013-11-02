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

/**
 * @author Raymond Augé
 */
public class PortalFilePermission {

	public static void checkCopy(String source, String destination) {
		_pacl.checkCopy(source, destination);
	}

	public static void checkDelete(String path) {
		_pacl.checkDelete(path);
	}

	public static void checkMove(String source, String destination) {
		_pacl.checkMove(source, destination);
	}

	public static void checkRead(String path) {
		_pacl.checkRead(path);
	}

	public static void checkWrite(String path) {
		_pacl.checkWrite(path);
	}

	public static interface PACL {

		public void checkCopy(String source, String destination);

		public void checkDelete(String path);

		public void checkMove(String source, String destination);

		public void checkRead(String path);

		public void checkWrite(String path);

	}

	private static PACL _pacl = new NoPACL();

	private static class NoPACL implements PACL {

		@Override
		public void checkCopy(String source, String destination) {
		}

		@Override
		public void checkDelete(String path) {
		}

		@Override
		public void checkMove(String source, String destination) {
		}

		@Override
		public void checkRead(String path) {
		}

		@Override
		public void checkWrite(String path) {
		}

	}

}