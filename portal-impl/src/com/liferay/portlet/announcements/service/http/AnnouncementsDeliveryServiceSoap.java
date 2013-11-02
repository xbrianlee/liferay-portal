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

package com.liferay.portlet.announcements.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.announcements.service.AnnouncementsDeliveryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portlet.announcements.service.AnnouncementsDeliveryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portlet.announcements.model.AnnouncementsDeliverySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portlet.announcements.model.AnnouncementsDelivery}, that is translated to a
 * {@link com.liferay.portlet.announcements.model.AnnouncementsDeliverySoap}. Methods that SOAP cannot
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
 * @see AnnouncementsDeliveryServiceHttp
 * @see com.liferay.portlet.announcements.model.AnnouncementsDeliverySoap
 * @see com.liferay.portlet.announcements.service.AnnouncementsDeliveryServiceUtil
 * @generated
 */
@ProviderType
public class AnnouncementsDeliveryServiceSoap {
	public static com.liferay.portlet.announcements.model.AnnouncementsDeliverySoap updateDelivery(
		long userId, java.lang.String type, boolean email, boolean sms,
		boolean website) throws RemoteException {
		try {
			com.liferay.portlet.announcements.model.AnnouncementsDelivery returnValue =
				AnnouncementsDeliveryServiceUtil.updateDelivery(userId, type,
					email, sms, website);

			return com.liferay.portlet.announcements.model.AnnouncementsDeliverySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AnnouncementsDeliveryServiceSoap.class);
}