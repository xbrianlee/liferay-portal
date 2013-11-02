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

import java.util.List;
import java.util.Vector;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.1.0, replaced by {@link PortalLifecycleUtil}
 */
public class PortalInitableUtil {

	public static synchronized void flushInitables() {
		if (_portalInitables != null) {
			for (PortalInitable portalInitable : _portalInitables) {
				portalInitable.portalInit();
			}

			_portalInitables = null;
		}
	}

	public static synchronized void init(PortalInitable portalInitable) {
		if (_portalInitables == null) {
			portalInitable.portalInit();
		}
		else {
			_portalInitables.add(portalInitable);
		}
	}

	private static List<PortalInitable> _portalInitables =
		new Vector<PortalInitable>();

}