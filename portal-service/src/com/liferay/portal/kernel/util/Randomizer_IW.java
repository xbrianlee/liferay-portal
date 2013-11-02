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

package com.liferay.portal.kernel.util;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated
 */
public class Randomizer_IW {

	public static Randomizer_IW getInstance() {
		return _instance;
	}

	public com.liferay.portal.kernel.util.Randomizer getWrappedInstance() {
		return Randomizer.getInstance();
	}

	private Randomizer_IW() {
	}

	private static Randomizer_IW _instance = new Randomizer_IW();

}