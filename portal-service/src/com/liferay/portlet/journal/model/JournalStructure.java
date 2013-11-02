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

package com.liferay.portlet.journal.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public interface JournalStructure
	extends Cloneable, Comparable<JournalStructure>, Serializable {

	public long getCompanyId();

	public Date getCreateDate();

	public String getDescription();

	public String getDescription(Locale locale);

	public String getDescription(Locale locale, boolean useDefault);

	public String getDescription(String languageId);

	public String getDescription(String languageId, boolean useDefault);

	public Map<Locale, String> getDescriptionMap();

	public ExpandoBridge getExpandoBridge();

	public long getGroupId();

	public long getId();

	public String getMergedXsd();

	public Map<String, Object> getModelAttributes();

	public Class<?> getModelClass();

	public String getModelClassName();

	public Date getModifiedDate();

	public String getName();

	public String getName(Locale locale);

	public String getName(Locale locale, boolean useDefault);

	public String getName(String languageId);

	public String getName(String languageId, boolean useDefault);

	public Map<Locale, String> getNameMap();

	public String getParentStructureId();

	public long getPrimaryKey();

	public Serializable getPrimaryKeyObj();

	public String getStructureId();

	public long getUserId();

	public String getUserName();

	public String getUserUuid() throws SystemException;

	public String getUuid();

	public String getXsd();

	@Override
	public int hashCode();

	public boolean isNew();

	public void setCompanyId(long companyId);

	public void setCreateDate(Date createDate);

	public void setDescription(String description);

	public void setDescription(String description, Locale locale);

	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(JournalStructure journalStructure);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public void setGroupId(long groupId);

	public void setId(long id);

	public void setModelAttributes(Map<String, Object> attributes);

	public void setModifiedDate(Date modifiedDate);

	public void setName(String name);

	public void setName(String name, Locale locale);

	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameMap(Map<Locale, String> nameMap);

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	public void setNew(boolean n);

	public void setParentStructureId(String parentStructureId);

	public void setPrimaryKey(long primaryKey);

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public void setStructureId(String structureId);

	public void setUserId(long userId);

	public void setUserName(String userName);

	public void setUserUuid(String userUuid);

	public void setUuid(String uuid);

	public void setXsd(String xsd);

}