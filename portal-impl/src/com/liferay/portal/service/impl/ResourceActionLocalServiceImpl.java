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

import com.liferay.portal.NoSuchResourceActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.base.ResourceActionLocalServiceBaseImpl;
import com.liferay.portal.util.comparator.ResourceActionBitwiseValueComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ResourceActionLocalServiceImpl
	extends ResourceActionLocalServiceBaseImpl {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void checkResourceActions() throws SystemException {
		List<ResourceAction> resourceActions =
			resourceActionPersistence.findAll();

		for (ResourceAction resourceAction : resourceActions) {
			String key = encodeKey(
				resourceAction.getName(), resourceAction.getActionId());

			_resourceActions.put(key, resourceAction);
		}
	}

	@Override
	public void checkResourceActions(String name, List<String> actionIds)
		throws SystemException {

		checkResourceActions(name, actionIds, false);
	}

	@Override
	public void checkResourceActions(
			String name, List<String> actionIds, boolean addDefaultActions)
		throws SystemException {

		long lastBitwiseValue = -1;
		List<ResourceAction> newResourceActions = null;

		for (String actionId : actionIds) {
			String key = encodeKey(name, actionId);

			ResourceAction resourceAction = _resourceActions.get(key);

			if (resourceAction != null) {
				continue;
			}

			resourceAction = resourceActionPersistence.fetchByN_A(
				name, actionId);

			if (resourceAction == null) {
				long bitwiseValue = 1;

				if (!actionId.equals(ActionKeys.VIEW)) {
					if (lastBitwiseValue < 0) {
						ResourceAction lastResourceAction =
							resourceActionPersistence.fetchByName_First(
								name,
								new ResourceActionBitwiseValueComparator());

						if (lastResourceAction != null) {
							lastBitwiseValue =
								lastResourceAction.getBitwiseValue();
						}
						else {
							lastBitwiseValue = 1;
						}
					}

					lastBitwiseValue = lastBitwiseValue << 1;

					bitwiseValue = lastBitwiseValue;
				}

				long resourceActionId = counterLocalService.increment(
					ResourceAction.class.getName());

				resourceAction = resourceActionPersistence.create(
					resourceActionId);

				resourceAction.setName(name);
				resourceAction.setActionId(actionId);
				resourceAction.setBitwiseValue(bitwiseValue);

				resourceActionPersistence.update(resourceAction);

				if (newResourceActions == null) {
					newResourceActions = new ArrayList<ResourceAction>();
				}

				newResourceActions.add(resourceAction);
			}

			_resourceActions.put(key, resourceAction);
		}

		if (!addDefaultActions || (newResourceActions == null)) {
			return;
		}

		List<String> groupDefaultActions =
			ResourceActionsUtil.getModelResourceGroupDefaultActions(name);

		List<String> guestDefaultActions =
			ResourceActionsUtil.getModelResourceGuestDefaultActions(name);

		long guestBitwiseValue = 0;
		long ownerBitwiseValue = 0;
		long siteMemberBitwiseValue = 0;

		for (ResourceAction resourceAction : newResourceActions) {
			String actionId = resourceAction.getActionId();

			if (guestDefaultActions.contains(actionId)) {
				guestBitwiseValue |= resourceAction.getBitwiseValue();
			}

			ownerBitwiseValue |= resourceAction.getBitwiseValue();

			if (groupDefaultActions.contains(actionId)) {
				siteMemberBitwiseValue |= resourceAction.getBitwiseValue();
			}
		}

		if (guestBitwiseValue > 0) {
			resourcePermissionLocalService.addResourcePermissions(
				name, RoleConstants.GUEST, ResourceConstants.SCOPE_INDIVIDUAL,
				guestBitwiseValue);
		}

		if (ownerBitwiseValue > 0) {
			resourcePermissionLocalService.addResourcePermissions(
				name, RoleConstants.OWNER, ResourceConstants.SCOPE_INDIVIDUAL,
				ownerBitwiseValue);
		}

		if (siteMemberBitwiseValue > 0) {
			resourcePermissionLocalService.addResourcePermissions(
				name, RoleConstants.SITE_MEMBER,
				ResourceConstants.SCOPE_INDIVIDUAL, siteMemberBitwiseValue);
		}
	}

	@Override
	@Skip
	public ResourceAction fetchResourceAction(String name, String actionId) {
		String key = encodeKey(name, actionId);

		return _resourceActions.get(key);
	}

	@Override
	@Skip
	public ResourceAction getResourceAction(String name, String actionId)
		throws PortalException {

		String key = encodeKey(name, actionId);

		ResourceAction resourceAction = _resourceActions.get(key);

		if (resourceAction == null) {
			throw new NoSuchResourceActionException(key);
		}

		return resourceAction;
	}

	@Override
	public List<ResourceAction> getResourceActions(String name)
		throws SystemException {

		return resourceActionPersistence.findByName(name);
	}

	protected String encodeKey(String name, String actionId) {
		return name.concat(StringPool.POUND).concat(actionId);
	}

	private static Map<String, ResourceAction> _resourceActions =
		new ConcurrentHashMap<String, ResourceAction>();

}