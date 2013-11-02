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

package com.liferay.portal.kernel.bi.rules;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class RulesEngineUtil {

	public static void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException {

		getRulesEngine().add(domainName, rulesResourceRetriever);
	}

	public static void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			ClassLoader... classloaders)
		throws RulesEngineException {

		getRulesEngine().add(domainName, rulesResourceRetriever, classloaders);
	}

	public static boolean containsRuleDomain(String domainName)
		throws RulesEngineException {

		return getRulesEngine().containsRuleDomain(domainName);
	}

	public static void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts)
		throws RulesEngineException {

		getRulesEngine().execute(rulesResourceRetriever, facts);
	}

	public static void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			ClassLoader... classloaders)
		throws RulesEngineException {

		getRulesEngine().execute(rulesResourceRetriever, facts, classloaders);
	}

	public static Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query)
		throws RulesEngineException {

		return getRulesEngine().execute(rulesResourceRetriever, facts, query);
	}

	public static Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query, ClassLoader... classloaders)
		throws RulesEngineException {

		return getRulesEngine().execute(
			rulesResourceRetriever, facts, query, classloaders);
	}

	public static void execute(String domainName, List<Fact<?>> facts)
		throws RulesEngineException {

		getRulesEngine().execute(domainName, facts);
	}

	public static void execute(
			String domainName, List<Fact<?>> facts, ClassLoader... classloaders)
		throws RulesEngineException {

		getRulesEngine().execute(domainName, facts, classloaders);
	}

	public static Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query)
		throws RulesEngineException {

		return getRulesEngine().execute(domainName, facts, query);
	}

	public static Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query,
			ClassLoader... classloaders)
		throws RulesEngineException {

		return getRulesEngine().execute(domainName, facts, query, classloaders);
	}

	public static RulesEngine getRulesEngine() {
		PortalRuntimePermission.checkGetBeanProperty(RulesEngineUtil.class);

		return _rulesEngine;
	}

	public static void remove(String domainName) throws RulesEngineException {
		getRulesEngine().remove(domainName);
	}

	public static void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever)
		throws RulesEngineException {

		getRulesEngine().update(domainName, rulesResourceRetriever);
	}

	public static void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			ClassLoader... classloaders)
		throws RulesEngineException {

		getRulesEngine().update(
			domainName, rulesResourceRetriever, classloaders);
	}

	public void setRulesEngine(RulesEngine rulesEngine) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_rulesEngine = rulesEngine;
	}

	private static RulesEngine _rulesEngine;

}