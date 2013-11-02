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
import com.liferay.portal.service.ResourceBlockServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portal.service.ResourceBlockServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.model.ResourceBlockSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.model.ResourceBlock}, that is translated to a
 * {@link com.liferay.portal.model.ResourceBlockSoap}. Methods that SOAP cannot
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
 * @see ResourceBlockServiceHttp
 * @see com.liferay.portal.model.ResourceBlockSoap
 * @see com.liferay.portal.service.ResourceBlockServiceUtil
 * @generated
 */
@ProviderType
public class ResourceBlockServiceSoap {
	public static void addCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.addCompanyScopePermission(scopeGroupId,
				companyId, name, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.addGroupScopePermission(scopeGroupId,
				companyId, groupId, name, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.addIndividualScopePermission(companyId,
				groupId, name, primKey, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeAllGroupScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.removeAllGroupScopePermissions(scopeGroupId,
				companyId, name, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.removeCompanyScopePermission(scopeGroupId,
				companyId, name, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.removeGroupScopePermission(scopeGroupId,
				companyId, groupId, name, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId) throws RemoteException {
		try {
			ResourceBlockServiceUtil.removeIndividualScopePermission(companyId,
				groupId, name, primKey, roleId, actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void setCompanyScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds) throws RemoteException {
		try {
			ResourceBlockServiceUtil.setCompanyScopePermissions(scopeGroupId,
				companyId, name, roleId, actionIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void setGroupScopePermissions(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds) throws RemoteException {
		try {
			ResourceBlockServiceUtil.setGroupScopePermissions(scopeGroupId,
				companyId, groupId, name, roleId, actionIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void setIndividualScopePermissions(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.util.List<java.lang.String> actionIds) throws RemoteException {
		try {
			ResourceBlockServiceUtil.setIndividualScopePermissions(companyId,
				groupId, name, primKey, roleId, actionIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ResourceBlockServiceSoap.class);
}