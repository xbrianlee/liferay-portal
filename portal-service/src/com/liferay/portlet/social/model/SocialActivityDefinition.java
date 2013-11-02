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

package com.liferay.portlet.social.model;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityDefinition implements Serializable {

	public void addCounter(
		SocialActivityCounterDefinition activityCounterDefinition) {

		_activityCounterDefinitions.put(
			activityCounterDefinition.getName(), activityCounterDefinition);
	}

	@Override
	public SocialActivityDefinition clone() {
		SocialActivityDefinition activityDefinition =
			new SocialActivityDefinition();

		for (SocialActivityCounterDefinition activityCounterDefinition :
				_activityCounterDefinitions.values()) {

			activityDefinition.addCounter(activityCounterDefinition.clone());
		}

		List<SocialAchievement> achievements =
			activityDefinition.getAchievements();

		achievements.addAll(_achievements);

		activityDefinition.setActivityProcessor(_activityProcessor);
		activityDefinition.setActivityType(_activityType);
		activityDefinition.setCountersEnabled(_countersEnabled);
		activityDefinition.setLanguageKey(_languageKey);
		activityDefinition.setLogActivity(_logActivity);
		activityDefinition.setModelName(_modelName);

		return activityDefinition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SocialActivityDefinition)) {
			return false;
		}

		SocialActivityDefinition activityDefinition =
			(SocialActivityDefinition)obj;

		if ((activityDefinition != null) &&
			Validator.equals(
				_achievements, activityDefinition.getAchievements()) &&
			Validator.equals(
				_activityCounterDefinitions,
				activityDefinition._activityCounterDefinitions) &&
			Validator.equals(
				_activityProcessor,
				activityDefinition.getActivityProcessor()) &&
			Validator.equals(
				_activityType, activityDefinition.getActivityType()) &&
			Validator.equals(
				_countersEnabled, activityDefinition.isCountersEnabled()) &&
			Validator.equals(
				_languageKey, activityDefinition.getLanguageKey()) &&
			Validator.equals(
				_logActivity, activityDefinition.isLogActivity()) &&
			Validator.equals(_modelName, activityDefinition.getModelName())) {

			return true;
		}

		return false;
	}

	public List<SocialAchievement> getAchievements() {
		return _achievements;
	}

	public SocialActivityCounterDefinition getActivityCounterDefinition(
		String name) {

		return _activityCounterDefinitions.get(name);
	}

	public Collection<SocialActivityCounterDefinition>
		getActivityCounterDefinitions() {

		return _activityCounterDefinitions.values();
	}

	public SocialActivityProcessor getActivityProcessor() {
		return _activityProcessor;
	}

	public int getActivityType() {
		return _activityType;
	}

	public String getLanguageKey() {
		return _languageKey;
	}

	public String getModelName() {
		return _modelName;
	}

	public String getName(Locale locale) {
		return LanguageUtil.get(
			locale, "social.activity." + _modelName + "." + _languageKey);
	}

	public boolean isCountersEnabled() {
		return _countersEnabled;
	}

	public boolean isLogActivity() {
		return _logActivity;
	}

	public void setActivityProcessor(
		SocialActivityProcessor activityProcessor) {

		_activityProcessor = activityProcessor;
	}

	public void setActivityType(int activityKey) {
		_activityType = activityKey;
	}

	public void setCounters(
		List<SocialActivityCounterDefinition> activityCounterDefinitions) {

		_activityCounterDefinitions.clear();

		for (SocialActivityCounterDefinition activityCounterDefinition :
				activityCounterDefinitions) {

			_activityCounterDefinitions.put(
				activityCounterDefinition.getName(), activityCounterDefinition);
		}
	}

	public void setCountersEnabled(boolean enabled) {
		_countersEnabled = enabled;
	}

	public void setLanguageKey(String languageKey) {
		_languageKey = languageKey;
	}

	public void setLogActivity(boolean logActivity) {
		_logActivity = logActivity;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	private List<SocialAchievement> _achievements =
		new ArrayList<SocialAchievement>();
	private Map<String, SocialActivityCounterDefinition>
		_activityCounterDefinitions =
			new HashMap<String, SocialActivityCounterDefinition>();
	private SocialActivityProcessor _activityProcessor;
	private int _activityType;
	private boolean _countersEnabled = true;
	private String _languageKey;
	private boolean _logActivity;
	private String _modelName;

}