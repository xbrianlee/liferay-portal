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

package com.liferay.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserNotificationDeliverySoap implements Serializable {
	public static UserNotificationDeliverySoap toSoapModel(
		UserNotificationDelivery model) {
		UserNotificationDeliverySoap soapModel = new UserNotificationDeliverySoap();

		soapModel.setUserNotificationDeliveryId(model.getUserNotificationDeliveryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPortletId(model.getPortletId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setDeliveryType(model.getDeliveryType());
		soapModel.setDeliver(model.getDeliver());

		return soapModel;
	}

	public static UserNotificationDeliverySoap[] toSoapModels(
		UserNotificationDelivery[] models) {
		UserNotificationDeliverySoap[] soapModels = new UserNotificationDeliverySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationDeliverySoap[][] toSoapModels(
		UserNotificationDelivery[][] models) {
		UserNotificationDeliverySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserNotificationDeliverySoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserNotificationDeliverySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationDeliverySoap[] toSoapModels(
		List<UserNotificationDelivery> models) {
		List<UserNotificationDeliverySoap> soapModels = new ArrayList<UserNotificationDeliverySoap>(models.size());

		for (UserNotificationDelivery model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserNotificationDeliverySoap[soapModels.size()]);
	}

	public UserNotificationDeliverySoap() {
	}

	public long getPrimaryKey() {
		return _userNotificationDeliveryId;
	}

	public void setPrimaryKey(long pk) {
		setUserNotificationDeliveryId(pk);
	}

	public long getUserNotificationDeliveryId() {
		return _userNotificationDeliveryId;
	}

	public void setUserNotificationDeliveryId(long userNotificationDeliveryId) {
		_userNotificationDeliveryId = userNotificationDeliveryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public int getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(int notificationType) {
		_notificationType = notificationType;
	}

	public int getDeliveryType() {
		return _deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		_deliveryType = deliveryType;
	}

	public boolean getDeliver() {
		return _deliver;
	}

	public boolean isDeliver() {
		return _deliver;
	}

	public void setDeliver(boolean deliver) {
		_deliver = deliver;
	}

	private long _userNotificationDeliveryId;
	private long _companyId;
	private long _userId;
	private String _portletId;
	private long _classNameId;
	private int _notificationType;
	private int _deliveryType;
	private boolean _deliver;
}