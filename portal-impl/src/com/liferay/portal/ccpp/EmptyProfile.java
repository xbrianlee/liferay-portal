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

package com.liferay.portal.ccpp;

import java.util.Set;

import javax.ccpp.Attribute;
import javax.ccpp.Component;
import javax.ccpp.Profile;
import javax.ccpp.ProfileDescription;

/**
 * @author Jorge Ferrer
 */
public class EmptyProfile implements Profile {

	@Override
	public Attribute getAttribute(String name) {
		return null;
	}

	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}

	@Override
	public Component getComponent(String localtype) {
		return null;
	}

	@Override
	public Set<Component> getComponents() {
		return null;
	}

	@Override
	public ProfileDescription getDescription() {
		return null;
	}

}