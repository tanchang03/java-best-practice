package com.sds.practice.helper;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("rawtypes")
public class StringUtil extends StringUtils {
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);


	/**
	 * 根据模式regex，对字符串s，进行匹配
	 * 
	 * @param s
	 *            字符串
	 * @param regex
	 *            模式
	 * @return boolean true=符合，false=不符合
	 */
	public static boolean checkString(String s, String regex) {
		boolean tag = false;
		if (s != null && !"".equals(s)) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(s);
			tag = m.matches();
		}
		return tag;
	}



	/**
	 * 判断字符串是否是数字
	 * 
	 * @param s
	 * @return 是数字＝true;不是数字＝false
	 */
	public static boolean isNumeric(String s) {
		boolean flag = true;
		if (s != null && !s.equals("")) {
			char[] numbers = s.toCharArray();
			for (int i = 0; i < numbers.length; i++) {
				if (!Character.isDigit(numbers[i])) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 字符串转化为长整行数字
	 * 
	 * @param s
	 * @return
	 */
	public static long strToLong(String s) {
		long temp = 0;
		if (isNumeric(s)) {
			temp = Long.parseLong(s);
		}
		return temp;
	}
	
	

	/**
	 * 格式化输入文本，使输入和输出表现一样
	 * 
	 * @param input
	 *            输入
	 * @return buf
	 */
	public static String formatHTML(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		// 建立一个Stringbuffer 来处理输入的数据
		StringBuffer buf = new StringBuffer(input.length() + 6);
		char ch = ' ';
		// 处理非法字符串
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<'){
				buf.append("&lt;");
				continue;
			}
			if (ch == '>'){
				buf.append("&gt;");
				continue;
			}
			
			if (ch == '\n'){
				buf.append("<br>");
				continue;
			}
			if (ch == '\''){
				buf.append("&acute;");
				continue;
			}
			if (ch == '"'){
				buf.append("&quot;");
				continue;
			}
			
			if (ch == ' '){
				buf.append("&nbsp;");
				continue;
			}
			buf.append(ch);
		}
		return buf.toString();
	}

	/**
	 * 分割字符串
	 * 
	 * @param input
	 *            要分割的字符串
	 * @return
	 */
	public static String formatBr(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		// 建立一个Stringbuffer 来处理输入的数据
		StringBuffer bf = new StringBuffer("");
		String from = "<>";
		StringTokenizer st = new StringTokenizer(input, from, true);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (tmp != null && tmp.equals("<")) {
				String tmp2 = st.nextToken().toLowerCase();
				if (tmp2.equals("br")) {
					st.nextToken();
					bf = bf.append("");
				}
			} else {
				bf.append(tmp);
			}
		}
		return bf.toString();
	}

	/*
	 * Function name: dealNull Description: \u8655理空字符串 Input: String str
	 * Output: 不等於null的String
	 */
	public static String dealNull(Object str) {
		String returnstr = null;
		if (str == null) {
			returnstr = "";
		} else {
			returnstr = str.toString();
		}
		return returnstr;
	}




	/**
	 * 将指定字符串中的指定字符替换成同其对应的Ascii码
	 * eg. '#'->'$35$'	其中35为#的Ascii码
	 * @param sString	源字符串
	 * @param sChar	特殊字符
	 * @return
	 */
	public static String encodeSpecialChar(String sString,char sChar){
		String sResult=sString;
		int iCode=sChar;
		sResult=StringUtils.replace(sResult,""+sChar,"$"+iCode+"$");
		return sResult;
	}
	public static String encodeSpecialChar(String sString,String sChar){
		return encodeSpecialChar(sString,sChar.charAt(0));
	}
	public static String decodeSpecialChar(String sString,String sChar){
		return decodeSpecialChar(sString,sChar.charAt(0));
	}
	/**
	 * 解析通过encodeSpecialChar方法编码过的特殊字符
	 * @param sString	源字符串
	 * @param sChar	特殊字符
	 * @return
	 */
	public static String decodeSpecialChar(String sString,char sChar){
		int iCode=sChar;
		String sResult=sString;
		sResult=StringUtils.replace(sResult,"$"+iCode+"$",""+sChar);
		return sResult;
	}
	/**
	 * 将制定的特殊符号编码
	 * @str 需要编码的字符串
	 * @splitStr 逗号分隔特殊字符   "',%,&,*,/,\"
	 */
	public static String encodeChars(String str,String splitStr){
		String[] splitArray = splitStr.split(",");
		for(int i=0;i<splitArray.length;i++){
			String specialChar = splitArray[i];
			str = encodeSpecialChar(str,specialChar);
		}
		return str;
	}
	/**
	 * 将制定的特殊符号编码
	 * @str 逗号分隔特殊字符   "',%,&,*,/,\"
	 */
	public static String  decodeChars(String str,String splitStr){
		String[] splitArray = splitStr.split(",");
		for(int i=0;i<splitArray.length;i++){
			String specialChar = splitArray[i];
			str = decodeSpecialChar(str,specialChar);
		}
		return str;
	}
	
	
	public static  String encodeChar(String str){
		str = encodeSpecialChar(str,'\'');
		str = encodeSpecialChar(str,';');
		str = encodeSpecialChar(str,'&');
		str = encodeSpecialChar(str,'"');
		return str;
	}

	public static  String  decodeChar(String str){
		str = decodeSpecialChar(str,'\'');
		str = decodeSpecialChar(str,';');
		str = decodeSpecialChar(str,'&');
		str = decodeSpecialChar(str,'"');
		return str;
	}

	/**
	 * 去掉字符串最后面字符
	 *@author  MrBao
	 *@date 	  2009-7-14
	 *@param sourceString
	 *@param cutString
	 *@return
	 *@return String
	 *@remark
	 */
	public static String delEndString(String sourceString,String cutString){
		String resultStr = null;
		if(isNotEmpty(sourceString) && isNotEmpty(cutString)){
			if(sourceString.endsWith(cutString)){
				resultStr = sourceString.substring(0,sourceString.length()- cutString.length());
			}else{
				resultStr = sourceString;
			}
		}
		return resultStr;
	}
	
	/**
	 * 将用逗号分隔的ids  1,2  转换成in括号内用的格式  '1','2'
	 *@author  MrBao 
	 *@date 	  2009-7-29
	 *@param ids
	 *@return
	 *@return String
	 *@remark
	 */
	public static String idsToWhereIn(String ids){
		
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isNotEmpty(ids)){
			String [] arrId = ids.split(",");
			for(String strId : arrId){
				sb.append("'" + strId + "',");
			}
		}
		if(sb.toString().length() >0 && sb.toString().endsWith(",")){
			ids = StringUtil.delEndString(sb.toString(), ",");
		}
		return ids;
	}


	/**
	 * 计算规则字符串的值
	 * 使用eviMap中存在的占位符的值替换指定字符串表达式中的占位符并返回替换后的字符串
	 * 
	 *  String exp = "/userfiles/image/${date(yyyyMM)}/${date(dd)}/${filename}";
		Map ev = new HashMap();
		ev.put("date", new Date());
		ev.put("filename", 1234);
		String ret = StringUtil.evalExp(exp,ev);
		System.out.println(ret);
		输出结果：/userfiles/image/201003/18/1234
	 * @param exp
	 * @param eviMap
	 * @return
	 */
	
	public static String evalExp(String exp,Map eviMap){
		// 匹配所有的dv规则的正则表达式
		String patt = "\\$\\{[^}]+\\}";
		Pattern p3 = Pattern.compile(patt);
		Matcher m3 = p3.matcher(exp);
		while (m3.find()) {
			String rule = m3.group();
			Pattern pattern = Pattern.compile("[\\{ \\( \\) \\}]+");
			String[] strs = pattern.split(rule);
			String var = strs[1];//变量名称
			Object val = eviMap.get(var);
			String value = "";
			if(val!=null){
				if(val instanceof Date){
					String fm = (strs.length>=3?strs[2]:"yyyyMMdd");
					value = DateUtils.formatDate((Date) val, fm);
				}else{
					value = val.toString();
				} 
			}
			exp = StringUtil.replace(exp, rule, value);
		}
		return exp;
	}


	/**
	 * 标准MD5加密 32位
	 * @author T61P
	 * @param str
	 * @return
	 */
	public static String md5_32(String str){  
	        MessageDigest messageDigest = null;  
	        try {  
	            messageDigest = MessageDigest.getInstance("MD5");  
	            messageDigest.reset();  
	            messageDigest.update(str.getBytes("UTF-8"));  
	        } catch (NoSuchAlgorithmException e) {  
	            System.out.println("NoSuchAlgorithmException caught!");  
	            System.exit(-1);  
	        } catch (UnsupportedEncodingException e) {
				logger.error("转码异常",e);
	        }
	        byte[] byteArray = messageDigest.digest();  	  
	        StringBuffer md5StrBuff = new StringBuffer();  	  
	        for (int i = 0; i < byteArray.length; i++)  {  
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	        }  
	        return md5StrBuff.toString();  
	}

	
	/**
	 * 生成随机数字
	 * @param length	随机数字的长度
	 */
	public static String randomNumber(int length) {
		String[] digits = {"1","2","3","4","5","6","7","8","9","0"}; 
		Random rnum = new Random(new Date().getTime()); 

		for(int i = 0; i < digits.length; i++) 
		{ 
		int index = Math.abs(rnum.nextInt()) % 10; 
		String tmpDigit = digits[index]; 
		digits[index] = digits[i]; 
		digits[i] = tmpDigit; 
		} 

		String returnStr = digits[0]; 
		for (int i = 1; i < length; i++) 
		{ 
		returnStr = digits[i] + returnStr; 
		} 
		return returnStr; 
	}
	

	/**
	 * 中文Unicode码转换
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {
		 
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
	    Matcher matcher = pattern.matcher(str);
	    char ch;
	    while (matcher.find()) {
	        ch = (char) Integer.parseInt(matcher.group(2), 16);
	        str = str.replace(matcher.group(1), ch + "");    
	    }
	    return str;
	}
	/**
	 * 根据正则表达式中匹配到符合规则的字符串
	 * @param regex
	 * @param text
	 * @return
	 */
	public static String match(String regex, String text) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }
}
