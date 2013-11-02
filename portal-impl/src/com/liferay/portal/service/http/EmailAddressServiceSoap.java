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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.EmailAddressServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portal.service.EmailAddressServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.model.EmailAddressSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.model.EmailAddress}, that is translated to a
 * {@link com.liferay.portal.model.EmailAddressSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddressServiceHttp
 * @see com.liferay.portal.model.EmailAddressSoap
 * @see com.liferay.portal.service.EmailAddressServiceUtil
 * @generated
 */
@ProviderType
public class EmailAddressServiceSoap {
	/**
	* @deprecated As of 6.2.0, replaced by {@link #addEmailAddress( String,
	long, String, int, boolean, ServiceContext)}
	*/
	public static com.liferay.portal.model.EmailAddressSoap addEmailAddress(
		java.lang.String className, long classPK, java.lang.String address,
		int typeId, boolean primary) throws RemoteException {
		try {
			com.liferay.portal.model.EmailAddress returnValue = EmailAddressServiceUtil.addEmailAddress(className,
					classPK, address, typeId, primary);

			return com.liferay.portal.model.EmailAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.EmailAddressSoap addEmailAddress(
		java.lang.String className, long classPK, java.lang.String address,
		int typeId, boolean primary,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.model.EmailAddress returnValue = EmailAddressServiceUtil.addEmailAddress(className,
					classPK, address, typeId, primary, serviceContext);

			return com.liferay.portal.model.EmailAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteEmailAddress(long emailAddressId)
		throws RemoteException {
		try {
			EmailAddressServiceUtil.deleteEmailAddress(emailAddressId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.EmailAddressSoap getEmailAddress(
		long emailAddressId) throws RemoteException {
		try {
			com.liferay.portal.model.EmailAddress returnValue = EmailAddressServiceUtil.getEmailAddress(emailAddressId);

			return com.liferay.portal.model.EmailAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.EmailAddressSoap[] getEmailAddresses(
		java.lang.String className, long classPK) throws RemoteException {
		try {
			java.util.List<com.liferay.portal.model.EmailAddress> returnValue = EmailAddressServiceUtil.getEmailAddresses(className,
					classPK);

			return com.liferay.portal.model.EmailAddressSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.EmailAddressSoap updateEmailAddress(
		long emailAddressId, java.lang.String address, int typeId,
		boolean primary) throws RemoteException {
		try {
			com.liferay.portal.model.EmailAddress returnValue = EmailAddressServiceUtil.updateEmailAddress(emailAddressId,
					address, typeId, primary);

			return com.liferay.portal.model.EmailAddressSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EmailAddressServiceSoap.class);
}