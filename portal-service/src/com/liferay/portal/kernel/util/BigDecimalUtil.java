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

package com.liferay.portal.kernel.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class BigDecimalUtil {

	public static double add(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.add(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double divide(
		Number x, Number y, int scale, RoundingMode roundingMode) {

		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.divide(
			yBigDecimal, scale, roundingMode);

		return resultBigDecimal.doubleValue();
	}

	public static double multiply(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.multiply(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double scale(Number x, int scale, RoundingMode roundingMode) {
		if (x == null) {
			x = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());

		xBigDecimal = xBigDecimal.setScale(scale, roundingMode);

		return xBigDecimal.doubleValue();
	}

	public static double subtract(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.subtract(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

}