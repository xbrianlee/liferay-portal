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

package com.liferay.portal.test;

import com.liferay.portal.aspectj.WeavingClassLoader;
import com.liferay.portal.kernel.process.ClassPathUtil;
import com.liferay.portal.kernel.test.NewClassLoaderJUnitTestRunner;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.lang.reflect.Method;

import java.net.MalformedURLException;
import java.net.URL;

import org.aspectj.lang.annotation.Aspect;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * @author Shuyang Zhou
 */
public class AspectJMockingNewClassLoaderJUnitTestRunner
	extends NewClassLoaderJUnitTestRunner {

	public AspectJMockingNewClassLoaderJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	protected ClassLoader createClassLoader(FrameworkMethod frameworkMethod) {
		AdviseWith adviseWith = frameworkMethod.getAnnotation(AdviseWith.class);

		if (adviseWith == null) {
			return super.createClassLoader(frameworkMethod);
		}

		Class<?>[] adviceClasses = adviseWith.adviceClasses();

		if (ArrayUtil.isEmpty(adviceClasses)) {
			return super.createClassLoader(frameworkMethod);
		}

		for (Class<?> adviceClass : adviceClasses) {
			Aspect aspect = adviceClass.getAnnotation(Aspect.class);

			if (aspect == null) {
				throw new IllegalArgumentException(
					"Class " + adviceClass.getName() + " is not an aspect");
			}
		}

		String jvmClassPath = ClassPathUtil.getJVMClassPath(true);

		URL[] urls = null;

		try {
			urls = ClassPathUtil.getClassPathURLs(jvmClassPath);
		}
		catch (MalformedURLException murle) {
			throw new RuntimeException(murle);
		}

		String dumpDirName = System.getProperty("junit.aspectj.dump");

		Method method = frameworkMethod.getMethod();

		Class<?> clazz = method.getDeclaringClass();

		String className = clazz.getName();

		File dumpDir = new File(
			dumpDirName,
			className.concat(StringPool.PERIOD).concat(method.getName()));

		return new WeavingClassLoader(urls, adviceClasses, dumpDir);
	}

}