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

package org.slf4j.impl;

import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.spi.MDCAdapter;

/**
 * @author Michael C. Han
 */
public class StaticMDCBinder {

	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

	public MDCAdapter getMDCA() {
		return new BasicMDCAdapter();
	}

	public String getMDCAdapterClassStr() {
		return BasicMDCAdapter.class.getName();
	}

	private StaticMDCBinder() {
	}

}