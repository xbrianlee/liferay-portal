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
import com.liferay.portal.kernel.util.ClassResolverUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @author Shuyang Zhou
 */
public class AnnotatedObjectInputStream extends ObjectInputStream {

	public AnnotatedObjectInputStream(InputStream inputStream)
		throws IOException {

		super(inputStream);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException, IOException {

		String contextName = readUTF();

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(contextName);

		String className = objectStreamClass.getName();

		return ClassResolverUtil.resolve(className, classLoader);
	}

}