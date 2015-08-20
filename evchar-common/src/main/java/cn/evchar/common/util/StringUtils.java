package cn.evchar.common.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 判断字符串你否为空
	 */
	public static boolean isEmpty(String... strs) {
		if (strs == null || strs.length == 0) {
			return true;
		}
		for (String str : strs) {
			if (str == null || str.length() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将null变为empty
	 */
	public static Object null2Str(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	/**
	 * 随机生成N位字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 计算规则：中文按双字节，中文按单字节，结果为总字节数。
	 * 
	 * @param str
	 * @return
	 */
	public static int length(String str) {
		if(StringUtils.isBlank(str)) {
			return 0;
		}
		String calstr = str.replaceAll("[^\\x00-\\xff]", "__");
		int size = calstr.length();
		if(size%2 == 0) {
			return size/2;
		} else {
			return size/2 + 1;
		}
	}
	
	public static String toString(Object... items) {
		StringBuilder strBuilder = new StringBuilder("[");
		for(Object item : items) {
			if(item instanceof Object[]) {
				strBuilder.append(toString((Object[])item));
			} else {
				strBuilder.append(item);
			}
			strBuilder.append(",");
		}
		if(strBuilder.indexOf(",") > 0) {
			strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
		}
		strBuilder.append("]");
		return strBuilder.toString();
	}
	
	public static List<Long> splitToList(String str, String sep) {
		List<Long> ids = new LinkedList<Long>();
		if (!isEmpty(str)) {
			String[] tokens = str.split(sep);
			for (String token : tokens) {
				ids.add(Long.parseLong(token));
			}
		}
		return ids;
	}
	
	public static String escapeChar(String s, String schar) {
		if (s == null) return null;
		return s.replaceAll(schar, "");
	}
	public static String escapeJavaChars(String s) {
		s = escapeChar(s, "\n");
		s = escapeChar(s, "\r");
		s = escapeChar(s, "\t");
		return s;
	}
	
	public static String escapeHtmlTags(String s) {
		if (s != null) {
			s = s.replaceAll("</?[^>]+>", "");
			s = s.replaceAll("\\s*|\t", "");
//			s = s.replaceAll("(\\r?\\n(\\s*r?n)+)", "\r\n");
		}
		return s;
	}
	
	public static String shorten(String s, int length) {
		if (s != null && s.length() > length) {
			String shortStr = s.substring(0, length);
			return shortStr + "...";
		}
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.length("测试Aa1_"));
	}
}
