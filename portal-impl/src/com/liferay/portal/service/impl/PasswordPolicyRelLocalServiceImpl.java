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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.service.base.PasswordPolicyRelLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

/**
 * @author Scott Lee
 * @author Shuyang Zhou
 */
public class PasswordPolicyRelLocalServiceImpl
	extends PasswordPolicyRelLocalServiceBaseImpl {

	@Override
	public PasswordPolicyRel addPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);

		if (passwordPolicyRel != null) {
			if (passwordPolicyRel.getPasswordPolicyId() == passwordPolicyId) {
				return null;
			}

			passwordPolicyRelPersistence.remove(passwordPolicyRel);
		}

		long passwordPolicyRelId = counterLocalService.increment();

		passwordPolicyRel = passwordPolicyRelPersistence.create(
			passwordPolicyRelId);

		passwordPolicyRel.setPasswordPolicyId(passwordPolicyId);
		passwordPolicyRel.setClassNameId(classNameId);
		passwordPolicyRel.setClassPK(classPK);

		passwordPolicyRelPersistence.update(passwordPolicyRel);

		return passwordPolicyRel;
	}

	@Override
	public void addPasswordPolicyRels(
			long passwordPolicyId, String className, long[] classPKs)
		throws SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			addPasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	@Override
	public void deletePasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);

		if ((passwordPolicyRel != null) &&
			(passwordPolicyRel.getPasswordPolicyId() == passwordPolicyId)) {

			passwordPolicyRelPersistence.remove(passwordPolicyRel);
		}
	}

	@Override
	public void deletePasswordPolicyRel(String className, long classPK)
		throws SystemException {

		try {
			long classNameId = PortalUtil.getClassNameId(className);

			PasswordPolicyRel passwordPolicyRel =
				passwordPolicyRelPersistence.findByC_C(classNameId, classPK);

			deletePasswordPolicyRel(passwordPolicyRel);
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}
	}

	@Override
	public void deletePasswordPolicyRels(long passwordPolicyId)
		throws SystemException {

		List<PasswordPolicyRel> passwordPolicyRels =
			passwordPolicyRelPersistence.findByPasswordPolicyId(
				passwordPolicyId);

		for (PasswordPolicyRel passwordPolicyRel : passwordPolicyRels) {
			deletePasswordPolicyRel(passwordPolicyRel);
		}
	}

	@Override
	public void deletePasswordPolicyRels(
			long passwordPolicyId, String className, long[] classPKs)
		throws SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			deletePasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	@Override
	public PasswordPolicyRel fetchPasswordPolicyRel(
			String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);
	}

	@Override
	public PasswordPolicyRel getPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);

		if ((passwordPolicyRel != null) &&
			(passwordPolicyRel.getPasswordPolicyId() == passwordPolicyId)) {

			return passwordPolicyRel;
		}

		StringBundler sb = new StringBundler(8);

		sb.append("No PasswordPolicyRel exists with the key {");
		sb.append("passwordPolicyId=");
		sb.append(passwordPolicyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPasswordPolicyRelException(sb.toString());
	}

	@Override
	public PasswordPolicyRel getPasswordPolicyRel(
			String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return passwordPolicyRelPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public boolean hasPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);

		if ((passwordPolicyRel != null) &&
			(passwordPolicyRel.getPasswordPolicyId() == passwordPolicyId)) {

			return true;
		}
		else {
			return false;
		}
	}

}