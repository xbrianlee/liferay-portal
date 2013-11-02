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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SystemEvent service. Represents a row in the &quot;SystemEvent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SystemEventModel
 * @see com.liferay.portal.model.impl.SystemEventImpl
 * @see com.liferay.portal.model.impl.SystemEventModelImpl
 * @generated
 */
@ProviderType
public interface SystemEvent extends SystemEventModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.SystemEventImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SystemEvent, Long> SYSTEM_EVENT_ID_ACCESSOR = new Accessor<SystemEvent, Long>() {
			@Override
			public Long get(SystemEvent systemEvent) {
				return systemEvent.getSystemEventId();
			}
		};

	public java.lang.String getReferrerClassName();

	public void setReferrerClassName(java.lang.String referrerClassName);
}