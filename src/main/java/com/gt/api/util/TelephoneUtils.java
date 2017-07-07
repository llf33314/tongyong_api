package com.gt.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电话相关信息，验证等等
 * @author Administrator
 *
 */
public class TelephoneUtils {
	/**
	 * 是否是合法手机号码  true 是正确电话号码，false不和标准
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^(13[0123456789][0-9]{8}|15[0123456789][0-9]{8}|17[0123456789][0-9]{8}|18[0123456789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 是否是合法固话，true是合法的，false是不合法的
	 * @param Fixed
	 * @return
	 */
	public static boolean isFixedNO(String Fixed) {
		Pattern p = Pattern
				.compile("^([0-9]{3,4})?[0-9]{7,8}$");
		Matcher m = p.matcher(Fixed);
		return m.matches();
	}
	/**
	 * 是否是合法电话，可能是固话也可能是手机电话， TRUE是合法的，false是不合法的
	 * @param telephone
	 * @return
	 */
	public static boolean isTelephoneNO(String telephone){
		if(isMobileNO(telephone)||isFixedNO(telephone)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断手机营业商
	 * 
	 * @param phone_number
	 * @return flag 运营商 3：电信 1 移动 2 联通 0 未知号码
	 */
	public static int matchesPhoneNumber(String phone_number) {
		String cm = "^((13[4-9])|(147)|(15[0-2,7-9])|(18[2-3,7-8]))\\d{8}$";
		String cu = "^((13[0-2])|(145)|(15[5-6])|(186))\\d{8}$";
		String ct = "^((133)|(153)|(18[0,1,9]))\\d{8}$";

		int flag = 0;
		if (phone_number.matches(cm)) {
			flag = 1;
		} else if (phone_number.matches(cu)) {
			flag = 2;
		} else if (phone_number.matches(ct)) {
			flag = 3;
		} else {
			flag = 4;
		}
		return flag;
	}
}
