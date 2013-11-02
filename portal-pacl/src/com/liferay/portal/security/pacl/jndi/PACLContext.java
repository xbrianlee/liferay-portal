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

package com.liferay.portal.security.pacl.jndi;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.pacl.PACLPolicy;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLContext implements Context {

	public PACLContext(Context context, PACLPolicy paclPolicy) {
		_context = context;
		_paclPolicy = paclPolicy;
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal)
		throws NamingException {

		return _context.addToEnvironment(propName, propVal);
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {
		checkPACLPolicy(name);

		_context.bind(name, obj);
	}

	@Override
	public void bind(String name, Object obj) throws NamingException {
		checkPACLPolicy(name);

		_context.bind(name, obj);
	}

	@Override
	public void close() throws NamingException {
		_context.close();
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		checkPACLPolicy(name);

		return _context.composeName(name, prefix);
	}

	@Override
	public String composeName(String name, String prefix)
		throws NamingException {

		checkPACLPolicy(name);

		return _context.composeName(name, prefix);
	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		checkPACLPolicy(name);

		return _context.createSubcontext(name);
	}

	@Override
	public Context createSubcontext(String name) throws NamingException {
		checkPACLPolicy(name);

		return _context.createSubcontext(name);
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		checkPACLPolicy(name);

		_context.destroySubcontext(name);
	}

	@Override
	public void destroySubcontext(String name) throws NamingException {
		checkPACLPolicy(name);

		_context.destroySubcontext(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Context)) {
			return false;
		}

		Context context = (Context)obj;

		if (Validator.equals(_context, context)) {
			return true;
		}

		return false;
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		return _context.getEnvironment();
	}

	@Override
	public String getNameInNamespace() throws NamingException {
		return _context.getNameInNamespace();
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		checkPACLPolicy(name);

		return _context.getNameParser(name);
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		checkPACLPolicy(name);

		return _context.getNameParser(name);
	}

	@Override
	public int hashCode() {
		return _context.hashCode();
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name)
		throws NamingException {

		checkPACLPolicy(name);

		return _context.list(name);
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name)
		throws NamingException {

		checkPACLPolicy(name);

		return _context.list(name);
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name)
		throws NamingException {

		checkPACLPolicy(name);

		return _context.listBindings(name);
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name)
		throws NamingException {

		checkPACLPolicy(name);

		return _context.listBindings(name);
	}

	@Override
	public Object lookup(Name name) throws NamingException {
		checkPACLPolicy(name);

		return _context.lookup(name);
	}

	@Override
	public Object lookup(String name) throws NamingException {
		checkPACLPolicy(name);

		return _context.lookup(name);
	}

	@Override
	public Object lookupLink(Name name) throws NamingException {
		checkPACLPolicy(name);

		return _context.lookupLink(name);
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		checkPACLPolicy(name);

		return _context.lookupLink(name);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		checkPACLPolicy(name);

		_context.rebind(name, obj);
	}

	@Override
	public void rebind(String name, Object obj) throws NamingException {
		checkPACLPolicy(name);

		_context.rebind(name, obj);
	}

	@Override
	public Object removeFromEnvironment(String propName)
		throws NamingException {

		return _context.removeFromEnvironment(propName);
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		checkPACLPolicy(oldName);
		checkPACLPolicy(newName);

		_context.rename(oldName, newName);
	}

	@Override
	public void rename(String oldName, String newName) throws NamingException {
		checkPACLPolicy(oldName);
		checkPACLPolicy(newName);

		_context.rename(oldName, newName);
	}

	@Override
	public void unbind(Name name) throws NamingException {
		checkPACLPolicy(name);

		_context.unbind(name);
	}

	@Override
	public void unbind(String name) throws NamingException {
		checkPACLPolicy(name);

		_context.unbind(name);
	}

	protected void checkPACLPolicy(Name name) {
		checkPACLPolicy(name.toString());
	}

	protected void checkPACLPolicy(String name) {
		if (!_paclPolicy.hasJNDI(name)) {
			throw new SecurityException(
				"Attempted to use unapproved JNDI " + name);
		}
	}

	private Context _context;
	private PACLPolicy _paclPolicy;

}