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

package com.liferay.portlet.mobiledevicerules.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the MDRRuleGroup service. Represents a row in the &quot;MDRRuleGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupModel
 * @see com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupImpl
 * @see com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupModelImpl
 * @generated
 */
@ProviderType
public interface MDRRuleGroup extends MDRRuleGroupModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRule> getRules()
		throws com.liferay.portal.kernel.exception.SystemException;
}