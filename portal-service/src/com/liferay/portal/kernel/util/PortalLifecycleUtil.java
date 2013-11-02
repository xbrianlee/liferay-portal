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

package com.liferay.portal.kernel.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLifecycleUtil {

	public static void flushDestroys() {
		_inFlushDestroys = true;

		for (int i = _portalLifecyclesDestroy.size() - 1; i >= 0; i--) {
			PortalLifecycle portalLifecycle = _portalLifecyclesDestroy.get(i);

			portalLifecycle.portalDestroy();
		}

		_portalLifecyclesDestroy.clear();

		_inFlushDestroys = false;
	}

	@SuppressWarnings("deprecation")
	public static void flushInits() {
		if (_portalLifecyclesInit != null) {
			List<PortalLifecycle> portalLifecyclesInit = _portalLifecyclesInit;

			_portalLifecyclesInit = null;

			for (PortalLifecycle portalLifecycle : portalLifecyclesInit) {
				portalLifecycle.portalInit();
			}
		}

		PortalInitableUtil.flushInitables();
	}

	public static void register(PortalLifecycle portalLifecycle) {
		register(portalLifecycle, PortalLifecycle.METHOD_ALL);
	}

	public static void register(PortalLifecycle portalLifecycle, int method) {
		if ((method == PortalLifecycle.METHOD_ALL) ||
			(method == PortalLifecycle.METHOD_INIT)) {

			if (_portalLifecyclesInit == null) {
				portalLifecycle.portalInit();
			}
			else {
				_portalLifecyclesInit.add(portalLifecycle);
			}
		}

		if ((method == PortalLifecycle.METHOD_ALL) ||
			(method == PortalLifecycle.METHOD_DESTROY)) {

			_portalLifecyclesDestroy.add(portalLifecycle);
		}
	}

	public static void removeDestroy(PortalLifecycle portalLifecycle) {
		if (!_inFlushDestroys) {
			_portalLifecyclesDestroy.remove(portalLifecycle);
		}
	}

	public static void reset() {
		_inFlushDestroys = false;

		if (_portalLifecyclesInit == null) {
			_portalLifecyclesInit = Collections.synchronizedList(
				new ArrayList<PortalLifecycle>());
		}
		else {
			_portalLifecyclesInit.clear();
		}

		_portalLifecyclesDestroy.clear();
	}

	private static boolean _inFlushDestroys;
	private static List<PortalLifecycle> _portalLifecyclesDestroy =
		Collections.synchronizedList(new ArrayList<PortalLifecycle>());
	private static List<PortalLifecycle> _portalLifecyclesInit =
		Collections.synchronizedList(new ArrayList<PortalLifecycle>());

}