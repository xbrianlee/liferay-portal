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

package com.liferay.portal.kernel.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Brian Wing Shun Chan
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Transactional {

	public boolean enabled() default true;

	public Isolation isolation() default Isolation.DEFAULT;

	public Class<? extends Throwable>[] noRollbackFor() default {};

	public String[] noRollbackForClassName() default {};

	public Propagation propagation() default Propagation.REQUIRED;

	public boolean readOnly() default false;

	public Class<? extends Throwable>[] rollbackFor() default {};

	public String[] rollbackForClassName() default {};

	public int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

}