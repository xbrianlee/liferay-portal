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

package com.liferay.portal.kernel.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Shuyang Zhou
 */
public class AnnotationLocator {

	public static List<Annotation> locate(Class<?> targetClass) {
		Queue<Class<?>> queue = new LinkedList<Class<?>>();

		queue.offer(targetClass);

		ArrayList<Annotation> annotationsList = new ArrayList<Annotation>();

		Class<?> clazz = null;

		while ((clazz = queue.poll()) != null) {
			Annotation[] annotations = clazz.getAnnotations();

			_mergeAnnotations(annotations, annotationsList);

			_queueSuperTypes(queue, clazz);
		}

		annotationsList.trimToSize();

		return annotationsList;
	}

	public static <T extends Annotation> T locate(
		Class<?> targetClass, Class<T> annotationClass) {

		Queue<Class<?>> queue = new LinkedList<Class<?>>();

		queue.offer(targetClass);

		Class<?> clazz = null;

		while ((clazz = queue.poll()) != null) {
			T annotation = clazz.getAnnotation(annotationClass);

			if (annotation == null) {
				_queueSuperTypes(queue, clazz);
			}
			else {
				return annotation;
			}
		}

		return null;
	}

	public static List<Annotation> locate(Method method, Class<?> targetClass) {
		Queue<Class<?>> queue = new LinkedList<Class<?>>();

		if (targetClass == null) {
			queue.offer(method.getDeclaringClass());
		}
		else {
			queue.offer(targetClass);
		}

		ArrayList<Annotation> annotationsList = new ArrayList<Annotation>();

		Class<?> clazz = null;

		while ((clazz = queue.poll()) != null) {
			try {
				Method specificMethod = clazz.getDeclaredMethod(
					method.getName(), method.getParameterTypes());

				Annotation[] annotations = specificMethod.getAnnotations();

				_mergeAnnotations(annotations, annotationsList);
			}
			catch (Exception e) {
			}

			try {

				// Ensure the class has a publicly inherited method

				clazz.getMethod(method.getName(), method.getParameterTypes());

				Annotation[] annotations = clazz.getAnnotations();

				_mergeAnnotations(annotations, annotationsList);
			}
			catch (Exception e) {
			}

			_queueSuperTypes(queue, clazz);
		}

		annotationsList.trimToSize();

		return annotationsList;
	}

	public static <T extends Annotation> T locate(
		Method method, Class<?> targetClass, Class<T> annotationClass) {

		Queue<Class<?>> queue = new LinkedList<Class<?>>();

		if (targetClass == null) {
			queue.offer(method.getDeclaringClass());
		}
		else {
			queue.offer(targetClass);
		}

		Class<?> clazz = null;

		while ((clazz = queue.poll()) != null) {
			T annotation = null;

			try {
				Method specificMethod = clazz.getDeclaredMethod(
					method.getName(), method.getParameterTypes());

				annotation = specificMethod.getAnnotation(annotationClass);

				if (annotation != null) {
					return annotation;
				}
			}
			catch (Exception e) {
			}

			try {

				// Ensure the class has a publicly inherited method

				clazz.getMethod(method.getName(), method.getParameterTypes());

				annotation = clazz.getAnnotation(annotationClass);
			}
			catch (Exception e) {
			}

			if (annotation == null) {
				_queueSuperTypes(queue, clazz);
			}
			else {
				return annotation;
			}
		}

		return null;
	}

	private static void _mergeAnnotations(
		Annotation[] sourceAnnotations, List<Annotation> targetAnnotationList) {

		merge:
		for (Annotation sourceAnnotation : sourceAnnotations) {
			for (Annotation targetAnnotation : targetAnnotationList) {
				if (sourceAnnotation.annotationType() ==
						targetAnnotation.annotationType()) {

					continue merge;
				}
			}

			targetAnnotationList.add(sourceAnnotation);
		}
	}

	private static void _queueSuperTypes(
		Queue<Class<?>> queue, Class<?> clazz) {

		Class<?> supperClass = clazz.getSuperclass();

		if ((supperClass != null) && (supperClass != Object.class)) {
			queue.offer(supperClass);
		}

		Class<?>[] interfaceClasses = clazz.getInterfaces();

		for (Class<?> interfaceClass : interfaceClasses) {
			if (!queue.contains(interfaceClass)) {
				queue.offer(interfaceClass);
			}
		}
	}

}