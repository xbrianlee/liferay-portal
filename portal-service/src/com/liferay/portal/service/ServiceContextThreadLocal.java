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

package com.liferay.portal.service;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.LinkedList;

/**
 * @author Michael C. Han
 */
public class ServiceContextThreadLocal {

	public static ServiceContext getServiceContext() {
		LinkedList<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		if (serviceContextStack.isEmpty()) {
			return null;
		}

		return serviceContextStack.peek();
	}

	public static ServiceContext popServiceContext() {
		LinkedList<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		if (serviceContextStack.isEmpty()) {
			return null;
		}

		return serviceContextStack.pop();
	}

	public static void pushServiceContext(ServiceContext serviceContext) {
		LinkedList<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		serviceContextStack.push(serviceContext);
	}

	private static ThreadLocal<LinkedList<ServiceContext>>
		_serviceContextThreadLocal =
			new AutoResetThreadLocal<LinkedList<ServiceContext>>(
				ServiceContextThreadLocal.class + "._serviceContextThreadLocal",
				new LinkedList<ServiceContext>()) {

					@Override
					protected LinkedList<ServiceContext> copy(
						LinkedList<ServiceContext> serviceContexts) {

						LinkedList<ServiceContext> cloneServiceContexts =
							new LinkedList<ServiceContext>();

						for (ServiceContext serviceContext : serviceContexts) {
							ServiceContext cloneServiceContext =
								(ServiceContext)serviceContext.clone();

							cloneServiceContexts.add(cloneServiceContext);
						}

						return cloneServiceContexts;
					}

				};

}