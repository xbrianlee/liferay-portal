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

package com.liferay.portlet.wiki.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WikiPageResource}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResource
 * @generated
 */
@ProviderType
public class WikiPageResourceWrapper implements WikiPageResource,
	ModelWrapper<WikiPageResource> {
	public WikiPageResourceWrapper(WikiPageResource wikiPageResource) {
		_wikiPageResource = wikiPageResource;
	}

	@Override
	public Class<?> getModelClass() {
		return WikiPageResource.class;
	}

	@Override
	public String getModelClassName() {
		return WikiPageResource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("resourcePrimKey", getResourcePrimKey());
		attributes.put("nodeId", getNodeId());
		attributes.put("title", getTitle());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long resourcePrimKey = (Long)attributes.get("resourcePrimKey");

		if (resourcePrimKey != null) {
			setResourcePrimKey(resourcePrimKey);
		}

		Long nodeId = (Long)attributes.get("nodeId");

		if (nodeId != null) {
			setNodeId(nodeId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}
	}

	/**
	* Returns the primary key of this wiki page resource.
	*
	* @return the primary key of this wiki page resource
	*/
	@Override
	public long getPrimaryKey() {
		return _wikiPageResource.getPrimaryKey();
	}

	/**
	* Sets the primary key of this wiki page resource.
	*
	* @param primaryKey the primary key of this wiki page resource
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wikiPageResource.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this wiki page resource.
	*
	* @return the uuid of this wiki page resource
	*/
	@Override
	public java.lang.String getUuid() {
		return _wikiPageResource.getUuid();
	}

	/**
	* Sets the uuid of this wiki page resource.
	*
	* @param uuid the uuid of this wiki page resource
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_wikiPageResource.setUuid(uuid);
	}

	/**
	* Returns the resource prim key of this wiki page resource.
	*
	* @return the resource prim key of this wiki page resource
	*/
	@Override
	public long getResourcePrimKey() {
		return _wikiPageResource.getResourcePrimKey();
	}

	/**
	* Sets the resource prim key of this wiki page resource.
	*
	* @param resourcePrimKey the resource prim key of this wiki page resource
	*/
	@Override
	public void setResourcePrimKey(long resourcePrimKey) {
		_wikiPageResource.setResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns the node ID of this wiki page resource.
	*
	* @return the node ID of this wiki page resource
	*/
	@Override
	public long getNodeId() {
		return _wikiPageResource.getNodeId();
	}

	/**
	* Sets the node ID of this wiki page resource.
	*
	* @param nodeId the node ID of this wiki page resource
	*/
	@Override
	public void setNodeId(long nodeId) {
		_wikiPageResource.setNodeId(nodeId);
	}

	/**
	* Returns the title of this wiki page resource.
	*
	* @return the title of this wiki page resource
	*/
	@Override
	public java.lang.String getTitle() {
		return _wikiPageResource.getTitle();
	}

	/**
	* Sets the title of this wiki page resource.
	*
	* @param title the title of this wiki page resource
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_wikiPageResource.setTitle(title);
	}

	@Override
	public boolean isNew() {
		return _wikiPageResource.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_wikiPageResource.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _wikiPageResource.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wikiPageResource.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _wikiPageResource.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _wikiPageResource.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wikiPageResource.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wikiPageResource.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_wikiPageResource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_wikiPageResource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wikiPageResource.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WikiPageResourceWrapper((WikiPageResource)_wikiPageResource.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portlet.wiki.model.WikiPageResource wikiPageResource) {
		return _wikiPageResource.compareTo(wikiPageResource);
	}

	@Override
	public int hashCode() {
		return _wikiPageResource.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portlet.wiki.model.WikiPageResource> toCacheModel() {
		return _wikiPageResource.toCacheModel();
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPageResource toEscapedModel() {
		return new WikiPageResourceWrapper(_wikiPageResource.toEscapedModel());
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPageResource toUnescapedModel() {
		return new WikiPageResourceWrapper(_wikiPageResource.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wikiPageResource.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wikiPageResource.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wikiPageResource.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WikiPageResourceWrapper)) {
			return false;
		}

		WikiPageResourceWrapper wikiPageResourceWrapper = (WikiPageResourceWrapper)obj;

		if (Validator.equals(_wikiPageResource,
					wikiPageResourceWrapper._wikiPageResource)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WikiPageResource getWrappedWikiPageResource() {
		return _wikiPageResource;
	}

	@Override
	public WikiPageResource getWrappedModel() {
		return _wikiPageResource;
	}

	@Override
	public void resetOriginalValues() {
		_wikiPageResource.resetOriginalValues();
	}

	private WikiPageResource _wikiPageResource;
}