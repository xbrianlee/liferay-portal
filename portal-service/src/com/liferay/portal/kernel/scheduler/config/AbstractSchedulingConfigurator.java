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

package com.liferay.portal.kernel.scheduler.config;

import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.StorageType;

import java.util.Collections;
import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public abstract class AbstractSchedulingConfigurator
	implements SchedulingConfigurator {

	public void afterPropertiesSet() {
		configure();
	}

	public void setExceptionsMaxSize(int exceptionsMaxSize) {
		this.exceptionsMaxSize = exceptionsMaxSize;
	}

	public void setSchedulerEntries(List<SchedulerEntry> schedulerEntries) {
		this.schedulerEntries = schedulerEntries;
	}

	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}

	protected int exceptionsMaxSize = 0;
	protected List<SchedulerEntry> schedulerEntries = Collections.emptyList();
	protected StorageType storageType = StorageType.MEMORY_CLUSTERED;

}