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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceClassVisitor;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceClassVisitorFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class JSONWebServiceClassVisitorFactoryImpl
	implements JSONWebServiceClassVisitorFactory {

	@Override
	public JSONWebServiceClassVisitor create(InputStream inputStream)
		throws IOException {

		return new JSONWebServiceClassVisitorImpl(inputStream);
	}

}