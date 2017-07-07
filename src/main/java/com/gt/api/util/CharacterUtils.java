package com.gt.api.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 字符转换，例如空值验证，String 转Int之类的
 * @author Administrator
 *
 */
public class CharacterUtils {
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		boolean b = false;
		try {
			if (obj == null || "".equals(obj)) {
				b = true;
			} else {
				b = false;
			}
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 判断对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		boolean b = false;
		try {
			if (obj == null || "".equals(obj)) {
				b = false;
			} else {
				b = true;
			}
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 转Integer
	 * 
	 * @param obj
	 */
	public static Integer toInteger(Object obj) {
		try {
			if (!isEmpty(obj)) {
				return Integer.parseInt(obj.toString());
			} else {
				throw new Exception("对象为空，转换失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 转String
	 * 
	 * @param obj
	 */
	public static String toString(Object obj) {
		try {
			if (!isEmpty(obj)) {
				return obj.toString();
			} else {
				throw new Exception("对象为空，转换失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 转Double
	 * 
	 * @param obj
	 */
	public static Double toDouble(Object obj) {
		try {
			if (!isEmpty(obj)) {
				return Double.parseDouble(obj.toString());
			} else {
				throw new Exception("对象为空，转换失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 校验是否是double数据
	 * 
	 */
	public static boolean isDouble(Object obj) {
		try {
			Double.parseDouble(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 是否为正整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	/**
	 * Object转成String
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStr(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * 格式化字符串
	 * 
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		String str = null;
		str = String.format(format, args);
		return str;
	}

	/**
	 * 保留2位小数（四舍五入）
	 * 
	 * @param d
	 * @return
	 */
	public static Double getDecimal_2(Double d) {
		BigDecimal bg = new BigDecimal(d);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	/**
	 * 转Integer
	 * 
	 * @param obj
	 */
	public static Integer parseInt(Object obj) throws Exception {
		if (!isEmpty(obj)) {
			return Integer.parseInt(obj.toString());
		} else {
			throw new Exception("对象为空，转换失败！");
		}
	}

	/**
	 * 转Double
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Double parseDouble(Object obj) throws Exception {
		if (!isEmpty(obj)) {
			return Double.parseDouble(obj.toString());
		} else {
			throw new Exception("对象为空，转换失败！");
		}
	}

}
