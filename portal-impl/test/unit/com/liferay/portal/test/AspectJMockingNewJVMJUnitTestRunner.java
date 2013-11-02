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
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.test.NewJVMJUnitTestRunner;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.SerializableUtil;

import java.io.File;
import java.io.Serializable;

import java.lang.reflect.Method;

import java.net.URL;

import java.util.Collections;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * @author Shuyang Zhou
 */
public class AspectJMockingNewJVMJUnitTestRunner extends NewJVMJUnitTestRunner {

	public AspectJMockingNewJVMJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	protected List<String> createArguments(FrameworkMethod frameworkMethod) {
		List<String> arguments = super.createArguments(frameworkMethod);

		AdviseWith adviseWith = frameworkMethod.getAnnotation(AdviseWith.class);

		if (adviseWith == null) {
			return arguments;
		}

		Class<?>[] adviceClasses = adviseWith.adviceClasses();

		if (ArrayUtil.isEmpty(adviceClasses)) {
			return Collections.emptyList();
		}

		StringBundler sb = new StringBundler(adviceClasses.length * 2 + 1);

		sb.append("-DaspectClasses=");

		for (Class<?> adviceClass : adviceClasses) {
			Aspect aspect = adviceClass.getAnnotation(Aspect.class);

			if (aspect == null) {
				throw new IllegalArgumentException(
					"Class " + adviceClass.getName() + " is not an aspect");
			}

			sb.append(adviceClass.getName());
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		arguments.add(sb.toString());

		return arguments;
	}

	@Override
	protected ProcessCallable<Serializable> processProcessCallable(
		ProcessCallable<Serializable> processCallable, MethodKey methodKey) {

		String dumpDirName = System.getProperty("junit.aspectj.dump");

		Class<?> clazz = methodKey.getDeclaringClass();

		String className = clazz.getName();

		File dumpDir = new File(
			dumpDirName,
			className.concat(StringPool.PERIOD).concat(
				methodKey.getMethodName()));

		return new SwitchClassLoaderProcessCallable(processCallable, dumpDir);
	}

	private static class SwitchClassLoaderProcessCallable
		implements ProcessCallable<Serializable> {

		public SwitchClassLoaderProcessCallable(
			ProcessCallable<Serializable> processCallable, File dumpDir) {

			_dumpDir = dumpDir;

			_encodedProcessCallable = SerializableUtil.serialize(
				processCallable);

			_toString = processCallable.toString();
		}

		@Override
		public Serializable call() throws ProcessException {
			attachProcess("Attached " + toString());

			String[] aspectClassNames = StringUtil.split(
				System.getProperty("aspectClasses"));

			Class<?>[] aspectClasses = new Class<?>[aspectClassNames.length];

			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			String jvmClassPath = ClassPathUtil.getJVMClassPath(true);

			try {
				URL[] urls = ClassPathUtil.getClassPathURLs(jvmClassPath);

				for (int i = 0; i < aspectClassNames.length; i++) {
					aspectClasses[i] = contextClassLoader.loadClass(
						aspectClassNames[i]);
				}

				WeavingClassLoader weavingClassLoader = new WeavingClassLoader(
					urls, aspectClasses, _dumpDir);

				Object originalProcessCallable = SerializableUtil.deserialize(
					_encodedProcessCallable, weavingClassLoader);

				currentThread.setContextClassLoader(weavingClassLoader);

				Method method = ReflectionUtil.getDeclaredMethod(
					originalProcessCallable.getClass(), "call");

				return (Serializable)method.invoke(originalProcessCallable);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}
			finally {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}

		@Override
		public String toString() {
			return _toString;
		}

		private static final long serialVersionUID = 1L;

		private File _dumpDir;
		private byte[] _encodedProcessCallable;
		private String _toString;
	}

}