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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.util.ClassLoaderPool;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Shuyang Zhou
 */
public class AnnotatedObjectOutputStream extends ObjectOutputStream {

	public AnnotatedObjectOutputStream(OutputStream outputStream)
		throws IOException {

		super(outputStream);
	}

	@Override
	protected void annotateClass(Class<?> clazz) throws IOException {
		ClassLoader classLoader = clazz.getClassLoader();

		String contextName = ClassLoaderPool.getContextName(classLoader);

		writeUTF(contextName);
	}

}