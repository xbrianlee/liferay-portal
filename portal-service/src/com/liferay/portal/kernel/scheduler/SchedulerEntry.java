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

package com.liferay.portal.kernel.scheduler;

import com.liferay.portal.kernel.messaging.MessageListener;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public interface SchedulerEntry extends Serializable {

	public String getDescription();

	public MessageListener getEventListener();

	public String getEventListenerClass();

	public String getPropertyKey();

	public TimeUnit getTimeUnit();

	public Trigger getTrigger() throws SchedulerException;

	public TriggerType getTriggerType();

	public String getTriggerValue();

	public void setDescription(String description);

	public void setEventListener(MessageListener eventListener);

	public void setEventListenerClass(String eventListenerClass);

	public void setPropertyKey(String propertyKey);

	public void setTimeUnit(TimeUnit timeUnit);

	public void setTriggerType(TriggerType triggerType);

	public void setTriggerValue(int triggerValue);

	public void setTriggerValue(long triggerValue);

	public void setTriggerValue(String triggerValue);

}