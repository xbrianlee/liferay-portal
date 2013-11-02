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

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link ClassNameService}.
 *
 * @author Brian Wing Shun Chan
 * @see ClassNameService
 * @generated
 */
@ProviderType
public class ClassNameServiceWrapper implements ClassNameService,
	ServiceWrapper<ClassNameService> {
	public ClassNameServiceWrapper(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _classNameService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_classNameService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.model.ClassName fetchClassName(
		java.lang.String value)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNameService.fetchClassName(value);
	}

	@Override
	public long fetchClassNameId(java.lang.Class<?> clazz) {
		return _classNameService.fetchClassNameId(clazz);
	}

	@Override
	public long fetchClassNameId(java.lang.String value) {
		return _classNameService.fetchClassNameId(value);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ClassNameService getWrappedClassNameService() {
		return _classNameService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedClassNameService(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	@Override
	public ClassNameService getWrappedService() {
		return _classNameService;
	}

	@Override
	public void setWrappedService(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	private ClassNameService _classNameService;
}