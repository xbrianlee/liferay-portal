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

import com.liferay.portal.kernel.messaging.proxy.ExecutingClassLoaders;
import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Vihang Pathak
 */
public interface RulesEngine {

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void add(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			@ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public boolean containsRuleDomain(String domainName)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			@ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public Map<String, ?> execute(
			RulesResourceRetriever rulesResourceRetriever, List<Fact<?>> facts,
			Query query, @ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void execute(
			String domainName, List<Fact<?>> facts,
			@ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public Map<String, ?> execute(
			String domainName, List<Fact<?>> facts, Query query,
			@ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void remove(String domainName) throws RulesEngineException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void update(
			String domainName, RulesResourceRetriever rulesResourceRetriever,
			@ExecutingClassLoaders ClassLoader... classloaders)
		throws RulesEngineException;

}