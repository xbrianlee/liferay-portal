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

package com.liferay.portal.kernel.increment;

/**
 * @author Zsolt Berentey
 */
public class NumberIncrement implements Increment<Number> {

	public NumberIncrement(Number value) {
		_value = value;
	}

	@Override
	public void decrease(Number delta) {
		_value = subtract(delta);
	}

	@Override
	public Increment<Number> decreaseForNew(Number delta) {
		return new NumberIncrement(subtract(delta));
	}

	@Override
	public Number getValue() {
		return _value;
	}

	@Override
	public void increase(Number delta) {
		_value = add(delta);
	}

	@Override
	public Increment<Number> increaseForNew(Number delta) {
		return new NumberIncrement(add(delta));
	}

	@Override
	public void setValue(Number value) {
		_value = value;
	}

	protected Number add(Number delta) {
		if (delta instanceof Double) {
			return addAsDouble(delta);
		}
		else if (delta instanceof Integer) {
			return addAsInteger(delta);
		}
		else if (delta instanceof Long) {
			return addAsLong(delta);
		}

		return _value;
	}

	protected Number addAsDouble(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.doubleValue() + delta.doubleValue();
	}

	protected Number addAsInteger(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.intValue() + delta.intValue();
	}

	protected Number addAsLong(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.longValue() + delta.longValue();
	}

	protected Number subtract(Number delta) {
		if (delta instanceof Double) {
			return subtractAsDouble(delta);
		}
		else if (delta instanceof Integer) {
			return subtractAsInteger(delta);
		}
		else if (delta instanceof Long) {
			return subtractAsLong(delta);
		}

		return _value;
	}

	protected Number subtractAsDouble(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.doubleValue() - delta.doubleValue();
	}

	protected Number subtractAsInteger(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.intValue() - delta.intValue();
	}

	protected Number subtractAsLong(Number delta) {
		if (delta == null) {
			return _value;
		}

		return _value.longValue() - delta.longValue();
	}

	private Number _value = 0;

}