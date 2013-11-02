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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Region}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @generated
 */
@ProviderType
public class RegionWrapper implements Region, ModelWrapper<Region> {
	public RegionWrapper(Region region) {
		_region = region;
	}

	@Override
	public Class<?> getModelClass() {
		return Region.class;
	}

	@Override
	public String getModelClassName() {
		return Region.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("regionId", getRegionId());
		attributes.put("countryId", getCountryId());
		attributes.put("regionCode", getRegionCode());
		attributes.put("name", getName());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long regionId = (Long)attributes.get("regionId");

		if (regionId != null) {
			setRegionId(regionId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String regionCode = (String)attributes.get("regionCode");

		if (regionCode != null) {
			setRegionCode(regionCode);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this region.
	*
	* @return the primary key of this region
	*/
	@Override
	public long getPrimaryKey() {
		return _region.getPrimaryKey();
	}

	/**
	* Sets the primary key of this region.
	*
	* @param primaryKey the primary key of this region
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_region.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the region ID of this region.
	*
	* @return the region ID of this region
	*/
	@Override
	public long getRegionId() {
		return _region.getRegionId();
	}

	/**
	* Sets the region ID of this region.
	*
	* @param regionId the region ID of this region
	*/
	@Override
	public void setRegionId(long regionId) {
		_region.setRegionId(regionId);
	}

	/**
	* Returns the country ID of this region.
	*
	* @return the country ID of this region
	*/
	@Override
	public long getCountryId() {
		return _region.getCountryId();
	}

	/**
	* Sets the country ID of this region.
	*
	* @param countryId the country ID of this region
	*/
	@Override
	public void setCountryId(long countryId) {
		_region.setCountryId(countryId);
	}

	/**
	* Returns the region code of this region.
	*
	* @return the region code of this region
	*/
	@Override
	public java.lang.String getRegionCode() {
		return _region.getRegionCode();
	}

	/**
	* Sets the region code of this region.
	*
	* @param regionCode the region code of this region
	*/
	@Override
	public void setRegionCode(java.lang.String regionCode) {
		_region.setRegionCode(regionCode);
	}

	/**
	* Returns the name of this region.
	*
	* @return the name of this region
	*/
	@Override
	public java.lang.String getName() {
		return _region.getName();
	}

	/**
	* Sets the name of this region.
	*
	* @param name the name of this region
	*/
	@Override
	public void setName(java.lang.String name) {
		_region.setName(name);
	}

	/**
	* Returns the active of this region.
	*
	* @return the active of this region
	*/
	@Override
	public boolean getActive() {
		return _region.getActive();
	}

	/**
	* Returns <code>true</code> if this region is active.
	*
	* @return <code>true</code> if this region is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _region.isActive();
	}

	/**
	* Sets whether this region is active.
	*
	* @param active the active of this region
	*/
	@Override
	public void setActive(boolean active) {
		_region.setActive(active);
	}

	@Override
	public boolean isNew() {
		return _region.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_region.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _region.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_region.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _region.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _region.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_region.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _region.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_region.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_region.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_region.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RegionWrapper((Region)_region.clone());
	}

	@Override
	public int compareTo(com.liferay.portal.model.Region region) {
		return _region.compareTo(region);
	}

	@Override
	public int hashCode() {
		return _region.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Region> toCacheModel() {
		return _region.toCacheModel();
	}

	@Override
	public com.liferay.portal.model.Region toEscapedModel() {
		return new RegionWrapper(_region.toEscapedModel());
	}

	@Override
	public com.liferay.portal.model.Region toUnescapedModel() {
		return new RegionWrapper(_region.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _region.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _region.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegionWrapper)) {
			return false;
		}

		RegionWrapper regionWrapper = (RegionWrapper)obj;

		if (Validator.equals(_region, regionWrapper._region)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Region getWrappedRegion() {
		return _region;
	}

	@Override
	public Region getWrappedModel() {
		return _region;
	}

	@Override
	public void resetOriginalValues() {
		_region.resetOriginalValues();
	}

	private Region _region;
}