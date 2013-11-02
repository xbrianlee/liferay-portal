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

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

/**
 * @author Michael C. Han
 */
public class StaticMarkerBinder implements MarkerFactoryBinder {

	public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();

	@Override
	public IMarkerFactory getMarkerFactory() {
		return _iMarkerFactory;
	}

	@Override
	public String getMarkerFactoryClassStr() {
		return BasicMarkerFactory.class.getName();
	}

	private StaticMarkerBinder() {
	}

	private IMarkerFactory _iMarkerFactory = new BasicMarkerFactory();

}