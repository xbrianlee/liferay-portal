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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionDefinition;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.interceptor.NoRollbackRuleAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class TransactionAttributeBuilder {

	public static TransactionAttribute build(
		boolean enabled, Isolation isolation, Propagation propagation,
		boolean readOnly, int timeout, Class<?>[] rollbackForClasses,
		String[] rollbackForClassNames, Class<?>[] noRollbackForClasses,
		String[] noRollbackForClassNames) {

		if (!enabled) {
			return null;
		}

		RuleBasedTransactionAttribute ruleBasedTransactionAttribute =
			new RuleBasedTransactionAttribute();

		if (isolation.value() == TransactionDefinition.ISOLATION_COUNTER) {
			ruleBasedTransactionAttribute.setIsolationLevel(
				PropsValues.TRANSACTION_ISOLATION_COUNTER);
		}
		else if (isolation.value() == TransactionDefinition.ISOLATION_PORTAL) {
			ruleBasedTransactionAttribute.setIsolationLevel(
				PropsValues.TRANSACTION_ISOLATION_PORTAL);
		}
		else {
			ruleBasedTransactionAttribute.setIsolationLevel(isolation.value());
		}

		ruleBasedTransactionAttribute.setPropagationBehavior(
			propagation.value());
		ruleBasedTransactionAttribute.setReadOnly(readOnly);
		ruleBasedTransactionAttribute.setTimeout(timeout);

		List<RollbackRuleAttribute> rollbackRuleAttributes =
			new ArrayList<RollbackRuleAttribute>();

		for (int i = 0; i < rollbackForClasses.length; i++) {
			RollbackRuleAttribute rollbackRuleAttribute =
				new RollbackRuleAttribute(rollbackForClasses[i]);

			rollbackRuleAttributes.add(rollbackRuleAttribute);
		}

		for (int i = 0; i < rollbackForClassNames.length; i++) {
			RollbackRuleAttribute rollbackRuleAttribute =
				new RollbackRuleAttribute(rollbackForClassNames[i]);

			rollbackRuleAttributes.add(rollbackRuleAttribute);
		}

		for (int i = 0; i < noRollbackForClasses.length; ++i) {
			NoRollbackRuleAttribute noRollbackRuleAttribute =
				new NoRollbackRuleAttribute(noRollbackForClasses[i]);

			rollbackRuleAttributes.add(noRollbackRuleAttribute);
		}

		for (int i = 0; i < noRollbackForClassNames.length; ++i) {
			NoRollbackRuleAttribute noRollbackRuleAttribute =
				new NoRollbackRuleAttribute(noRollbackForClassNames[i]);

			rollbackRuleAttributes.add(noRollbackRuleAttribute);
		}

		List<RollbackRuleAttribute> ruleBasedRollbackRuleAttributes =
			ruleBasedTransactionAttribute.getRollbackRules();

		ruleBasedRollbackRuleAttributes.addAll(rollbackRuleAttributes);

		return ruleBasedTransactionAttribute;
	}

	public static TransactionAttribute build(
		Propagation propagation, Class<?>[] rollbackForClasses,
		Class<?>... noRollbackForClasses) {

		return build(
			true, Isolation.PORTAL, propagation, false, -1, rollbackForClasses,
			new String[0], noRollbackForClasses, new String[0]);
	}

	public static TransactionAttribute build(Transactional transactional) {
		if (transactional == null) {
			return null;
		}

		return build(
			transactional.enabled(), transactional.isolation(),
			transactional.propagation(), transactional.readOnly(),
			transactional.timeout(), transactional.rollbackFor(),
			transactional.rollbackForClassName(), transactional.noRollbackFor(),
			transactional.noRollbackForClassName());
	}

}