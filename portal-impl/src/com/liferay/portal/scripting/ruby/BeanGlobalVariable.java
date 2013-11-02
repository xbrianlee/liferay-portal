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

package com.liferay.portal.scripting.ruby;

import org.jruby.Ruby;
import org.jruby.javasupport.Java;
import org.jruby.javasupport.JavaObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.IAccessor;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * @author Alberto Montero
 */
class BeanGlobalVariable implements IAccessor {

	public BeanGlobalVariable(Ruby ruby, Object bean, Class<?> type) {
		_ruby = ruby;
		_type = type;

		_bean = JavaUtil.convertJavaToRuby(_ruby, bean, _type);

		if (_bean instanceof JavaObject) {
			_bean = Java.wrap(_ruby, _bean);
		}
	}

	@Override
	public IRubyObject getValue() {
		return _bean;
	}

	@Override
	public IRubyObject setValue(IRubyObject bean) {
		_bean = bean;

		return bean;
	}

	private IRubyObject _bean;
	private Ruby _ruby;
	private Class<?> _type;

}