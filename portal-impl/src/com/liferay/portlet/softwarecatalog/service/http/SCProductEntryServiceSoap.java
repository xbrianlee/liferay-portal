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

package com.liferay.portlet.softwarecatalog.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.softwarecatalog.service.SCProductEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portlet.softwarecatalog.service.SCProductEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portlet.softwarecatalog.model.SCProductEntry}, that is translated to a
 * {@link com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap}. Methods that SOAP cannot
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
 * @see SCProductEntryServiceHttp
 * @see com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap
 * @see com.liferay.portlet.softwarecatalog.service.SCProductEntryServiceUtil
 * @generated
 */
@ProviderType
public class SCProductEntryServiceSoap {
	public static com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap addProductEntry(
		java.lang.String name, java.lang.String type, java.lang.String tags,
		java.lang.String shortDescription, java.lang.String longDescription,
		java.lang.String pageURL, java.lang.String author,
		java.lang.String repoGroupId, java.lang.String repoArtifactId,
		long[] licenseIds, java.util.List<byte[]> thumbnails,
		java.util.List<byte[]> fullImages,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.softwarecatalog.model.SCProductEntry returnValue =
				SCProductEntryServiceUtil.addProductEntry(name, type, tags,
					shortDescription, longDescription, pageURL, author,
					repoGroupId, repoArtifactId, licenseIds, thumbnails,
					fullImages, serviceContext);

			return com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteProductEntry(long productEntryId)
		throws RemoteException {
		try {
			SCProductEntryServiceUtil.deleteProductEntry(productEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap getProductEntry(
		long productEntryId) throws RemoteException {
		try {
			com.liferay.portlet.softwarecatalog.model.SCProductEntry returnValue =
				SCProductEntryServiceUtil.getProductEntry(productEntryId);

			return com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap updateProductEntry(
		long productEntryId, java.lang.String name, java.lang.String type,
		java.lang.String tags, java.lang.String shortDescription,
		java.lang.String longDescription, java.lang.String pageURL,
		java.lang.String author, java.lang.String repoGroupId,
		java.lang.String repoArtifactId, long[] licenseIds,
		java.util.List<byte[]> thumbnails, java.util.List<byte[]> fullImages)
		throws RemoteException {
		try {
			com.liferay.portlet.softwarecatalog.model.SCProductEntry returnValue =
				SCProductEntryServiceUtil.updateProductEntry(productEntryId,
					name, type, tags, shortDescription, longDescription,
					pageURL, author, repoGroupId, repoArtifactId, licenseIds,
					thumbnails, fullImages);

			return com.liferay.portlet.softwarecatalog.model.SCProductEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SCProductEntryServiceSoap.class);
}