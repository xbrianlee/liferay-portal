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

package com.liferay.portlet.messageboards.security.permission;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.BasePermissionPropagator;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.persistence.MBMessageActionableDynamicQuery;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

/**
 * @author Kenneth Chang
 * @author Hugo Huijser
 */
public class MBPermissionPropagatorImpl extends BasePermissionPropagator {

	@Override
	public void propagateRolePermissions(
			ActionRequest actionRequest, String className, String primKey,
			long[] roleIds)
		throws PortalException, SystemException {

		if (className.equals(MBCategory.class.getName())) {
			propagateCategoryRolePermissions(
				actionRequest, className, primKey, roleIds);
		}
		else if (className.equals(MBMessage.class.getName())) {
			long messageId = GetterUtil.getLong(primKey);

			MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

			if (message.isRoot()) {
				propagateThreadRolePermissions(
					actionRequest, className, messageId, message.getThreadId(),
					roleIds);
			}
		}
		else if (className.equals("com.liferay.portlet.messageboards")) {
			propagateMBRolePermissions(
				actionRequest, className, primKey, roleIds);
		}
	}

	protected void propagateCategoryRolePermissions(
			ActionRequest actionRequest, String className, long primaryKey,
			long categoryId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			propagateRolePermissions(
				actionRequest, roleId, className, primaryKey,
				MBCategory.class.getName(), categoryId);
		}
	}

	protected void propagateCategoryRolePermissions(
			final ActionRequest actionRequest, final String className,
			String primKey, final long[] roleIds)
		throws PortalException, SystemException {

		final long categoryId = GetterUtil.getLong(primKey);

		MBCategory category = MBCategoryLocalServiceUtil.getCategory(
			categoryId);

		List<Object> categoriesAndThreads =
			MBCategoryLocalServiceUtil.getCategoriesAndThreads(
				category.getGroupId(), categoryId);

		for (Object categoryOrThread : categoriesAndThreads) {
			if (categoryOrThread instanceof MBThread) {
				MBThread thread = (MBThread)categoryOrThread;

				List<MBMessage> messages =
					MBMessageLocalServiceUtil.getThreadMessages(
						thread.getThreadId(), WorkflowConstants.STATUS_ANY);

				for (MBMessage message : messages) {
					propagateMessageRolePermissions(
						actionRequest, className, categoryId,
						message.getMessageId(), roleIds);
				}
			}
			else {
				category = (MBCategory)categoryOrThread;

				List<Long> categoryIds = new ArrayList<Long>();

				categoryIds.add(category.getCategoryId());

				categoryIds = MBCategoryLocalServiceUtil.getSubcategoryIds(
					categoryIds, category.getGroupId(),
					category.getCategoryId());

				for (final long addCategoryId : categoryIds) {
					propagateCategoryRolePermissions(
						actionRequest, className, categoryId, addCategoryId,
						roleIds);

					ActionableDynamicQuery actionableDynamicQuery =
						new MBMessageActionableDynamicQuery() {

						@Override
						protected void addCriteria(DynamicQuery dynamicQuery) {
							Property categoryIdProperty =
								PropertyFactoryUtil.forName("categoryId");

							dynamicQuery.add(
								categoryIdProperty.eq(addCategoryId));
						}

						@Override
						protected void performAction(Object object)
							throws PortalException, SystemException {

							MBMessage message = (MBMessage)object;

							propagateMessageRolePermissions(
								actionRequest, className, categoryId,
								message.getMessageId(), roleIds);
						}

					};

					actionableDynamicQuery.setGroupId(category.getGroupId());

					actionableDynamicQuery.performActions();
				}
			}
		}
	}

	protected void propagateMBRolePermissions(
			final ActionRequest actionRequest, final String className,
			String primKey, final long[] roleIds)
		throws PortalException, SystemException {

		final long groupId = GetterUtil.getLong(primKey);

		List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(
			groupId);

		for (MBCategory category : categories) {
			propagateCategoryRolePermissions(
				actionRequest, className, groupId, category.getCategoryId(),
				roleIds);
		}

		ActionableDynamicQuery actionableDynamicQuery =
			new MBMessageActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				MBMessage message = (MBMessage)object;

				propagateMessageRolePermissions(
					actionRequest, className, groupId, message.getMessageId(),
					roleIds);
			}

		};

		actionableDynamicQuery.setGroupId(groupId);

		actionableDynamicQuery.performActions();
	}

	protected void propagateMessageRolePermissions(
			ActionRequest actionRequest, String className, long primaryKey,
			long messageId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			propagateRolePermissions(
				actionRequest, roleId, className, primaryKey,
				MBMessage.class.getName(), messageId);
		}
	}

	protected void propagateThreadRolePermissions(
			ActionRequest actionRequest, String className, long messageId,
			long threadId, long[] roleIds)
		throws PortalException, SystemException {

		List<MBMessage> messages = MBMessageLocalServiceUtil.getThreadMessages(
			threadId, WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {
			propagateMessageRolePermissions(
				actionRequest, className, messageId, message.getMessageId(),
				roleIds);
		}
	}

}