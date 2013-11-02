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

package com.liferay.portlet.ratings.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the RatingsEntry service. Represents a row in the &quot;RatingsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryModel
 * @see com.liferay.portlet.ratings.model.impl.RatingsEntryImpl
 * @see com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl
 * @generated
 */
@ProviderType
public interface RatingsEntry extends RatingsEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.ratings.model.impl.RatingsEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
}