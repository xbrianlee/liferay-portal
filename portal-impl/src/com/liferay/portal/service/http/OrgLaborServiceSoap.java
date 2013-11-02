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
import com.liferay.portal.service.OrgLaborServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portal.service.OrgLaborServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.model.OrgLaborSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.model.OrgLabor}, that is translated to a
 * {@link com.liferay.portal.model.OrgLaborSoap}. Methods that SOAP cannot
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
 * @see OrgLaborServiceHttp
 * @see com.liferay.portal.model.OrgLaborSoap
 * @see com.liferay.portal.service.OrgLaborServiceUtil
 * @generated
 */
@ProviderType
public class OrgLaborServiceSoap {
	public static com.liferay.portal.model.OrgLaborSoap addOrgLabor(
		long organizationId, int typeId, int sunOpen, int sunClose,
		int monOpen, int monClose, int tueOpen, int tueClose, int wedOpen,
		int wedClose, int thuOpen, int thuClose, int friOpen, int friClose,
		int satOpen, int satClose) throws RemoteException {
		try {
			com.liferay.portal.model.OrgLabor returnValue = OrgLaborServiceUtil.addOrgLabor(organizationId,
					typeId, sunOpen, sunClose, monOpen, monClose, tueOpen,
					tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen,
					friClose, satOpen, satClose);

			return com.liferay.portal.model.OrgLaborSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteOrgLabor(long orgLaborId)
		throws RemoteException {
		try {
			OrgLaborServiceUtil.deleteOrgLabor(orgLaborId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.OrgLaborSoap getOrgLabor(
		long orgLaborId) throws RemoteException {
		try {
			com.liferay.portal.model.OrgLabor returnValue = OrgLaborServiceUtil.getOrgLabor(orgLaborId);

			return com.liferay.portal.model.OrgLaborSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.OrgLaborSoap[] getOrgLabors(
		long organizationId) throws RemoteException {
		try {
			java.util.List<com.liferay.portal.model.OrgLabor> returnValue = OrgLaborServiceUtil.getOrgLabors(organizationId);

			return com.liferay.portal.model.OrgLaborSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.OrgLaborSoap updateOrgLabor(
		long orgLaborId, int typeId, int sunOpen, int sunClose, int monOpen,
		int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
		int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
		int satClose) throws RemoteException {
		try {
			com.liferay.portal.model.OrgLabor returnValue = OrgLaborServiceUtil.updateOrgLabor(orgLaborId,
					typeId, sunOpen, sunClose, monOpen, monClose, tueOpen,
					tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen,
					friClose, satOpen, satClose);

			return com.liferay.portal.model.OrgLaborSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OrgLaborServiceSoap.class);
}