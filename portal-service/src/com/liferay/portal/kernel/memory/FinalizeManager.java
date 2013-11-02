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

package com.liferay.portal.kernel.memory;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class FinalizeManager {

	public static final boolean THREAD_ENABLED = Boolean.getBoolean(
		FinalizeManager.class.getName() + ".thread.enabled");

	public static <T> Reference<T> register(
		T realReference, FinalizeAction finalizeAction) {

		Reference<T> reference = new EqualityWeakReference<T>(
			realReference, _referenceQueue);

		_referenceActionMap.put(reference, finalizeAction);

		if (!THREAD_ENABLED) {
			_pollingCleanup();
		}

		return reference;
	}

	private static void _pollingCleanup() {
		Reference<? extends Object> reference = null;

		while ((reference = _referenceQueue.poll()) != null) {
			FinalizeAction finalizeAction = _referenceActionMap.remove(
				reference);

			finalizeAction.doFinalize();
		}
	}

	private static Map<Reference<?>, FinalizeAction> _referenceActionMap =
		new ConcurrentHashMap<Reference<?>, FinalizeAction>();
	private static ReferenceQueue<Object> _referenceQueue =
		new ReferenceQueue<Object>();

	private static class FinalizeThread extends Thread {

		public FinalizeThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (true) {
				try {
					Reference<? extends Object> reference =
						_referenceQueue.remove();

					FinalizeAction finalizeAction = _referenceActionMap.remove(
						reference);

					finalizeAction.doFinalize();
				}
				catch (InterruptedException ie) {
				}
			}
		}
	}

	static {
		if (THREAD_ENABLED) {
			Thread thread = new FinalizeThread("Finalize Thread");

			thread.setContextClassLoader(
				FinalizeManager.class.getClassLoader());

			thread.setDaemon(true);

			thread.start();
		}
	}

}