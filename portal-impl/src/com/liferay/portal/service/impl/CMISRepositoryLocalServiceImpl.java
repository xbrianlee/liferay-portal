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

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryHandler;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.repository.cmis.CMISRepository;
import com.liferay.portal.repository.proxy.BaseRepositoryProxyBean;
import com.liferay.portal.service.base.CMISRepositoryLocalServiceBaseImpl;

import org.apache.chemistry.opencmis.client.api.Document;

/**
 * @author Alexander Chow
 */
public class CMISRepositoryLocalServiceImpl
	extends CMISRepositoryLocalServiceBaseImpl {

	@Override
	public Object getSession(long repositoryId)
		throws PortalException, SystemException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		return cmisRepository.getSession();
	}

	@Override
	public FileEntry toFileEntry(long repositoryId, Object object)
		throws PortalException, SystemException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		Document document = (Document)object;

		return cmisRepository.toFileEntry(document);
	}

	@Override
	public FileVersion toFileVersion(long repositoryId, Object object)
		throws PortalException, SystemException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		Document document = (Document)object;

		return cmisRepository.toFileVersion(document);
	}

	@Override
	public Folder toFolder(long repositoryId, Object object)
		throws PortalException, SystemException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
			(org.apache.chemistry.opencmis.client.api.Folder)object;

		return cmisRepository.toFolder(cmisFolder);
	}

	protected CMISRepository getCmisRepository(long repositoryId)
		throws PortalException, SystemException {

		Repository repositoryImpl = repositoryLocalService.getRepositoryImpl(
			repositoryId);

		CMISRepositoryHandler cmisRepositoryHandler = null;

		if (repositoryImpl instanceof CMISRepositoryHandler) {
			cmisRepositoryHandler = (CMISRepositoryHandler)repositoryImpl;
		}
		else if (repositoryImpl instanceof BaseRepositoryProxyBean) {
			BaseRepositoryProxyBean baseRepositoryProxyBean =
				(BaseRepositoryProxyBean)repositoryImpl;

			ClassLoaderBeanHandler classLoaderBeanHandler =
				(ClassLoaderBeanHandler)ProxyUtil.getInvocationHandler(
					baseRepositoryProxyBean.getProxyBean());

			Object bean = classLoaderBeanHandler.getBean();

			if (bean instanceof CMISRepositoryHandler) {
				cmisRepositoryHandler = (CMISRepositoryHandler)bean;
			}
		}

		CMISRepository cmisRepository =
			(CMISRepository)cmisRepositoryHandler.getCmisRepository();

		return cmisRepository;
	}

}